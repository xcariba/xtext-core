/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ide.serializer.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion;
import org.eclipse.xtext.formatting2.regionaccess.internal.StringBasedTextRegionAccessDiff;
import org.eclipse.xtext.formatting2.regionaccess.internal.StringBasedTextRegionAccessDiff.Factory;
import org.eclipse.xtext.ide.serializer.impl.SerializerChangeRecorder.EObjectChange;
import org.eclipse.xtext.ide.serializer.impl.SerializerChangeRecorder.XtextResourceChange;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.ISequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.ISyntacticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.TokenStreamSequenceAdapter;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic;
import org.eclipse.xtext.serializer.sequencer.IContextFinder;
import org.eclipse.xtext.serializer.sequencer.IHiddenTokenSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ISyntacticSequencer;
import org.eclipse.xtext.util.EmfFormatter;
import org.eclipse.xtext.xtext.RuleNames;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
public class PartialSerializer {

	@Inject
	private IValueConverterService converter;

	@Inject
	private RuleNames ruleNames;

	interface SerializationStrategy {
		void serialize(StringBasedTextRegionAccessDiff.Factory result);
	}

	protected class SerializeAttributeStrategy implements SerializationStrategy {
		private final FeatureChange change;
		private final EObject root;

		public SerializeAttributeStrategy(EObject root, FeatureChange change) {
			super();
			this.change = change;
			this.root = root;
		}

		@Override
		public void serialize(Factory result) {
			EStructuralFeature feature = change.getFeature();
			ISemanticRegion region = result.getBase().regionForEObject(root).getRegionFor().feature(feature);
			AbstractRule rule = ((RuleCall) region.getGrammarElement()).getRule();
			String ruleName = ruleNames.getQualifiedName(rule);
			Object value = root.eGet(feature);
			String newText = converter.toString(value, ruleName);
			result.replace(region, newText);
		}

	}

	protected class SerializeRecursiveStrategy implements SerializationStrategy {
		private final ISerializationContext context;
		private final EObject root;

		public SerializeRecursiveStrategy(EObject root, ISerializationContext context) {
			super();
			this.context = context;
			this.root = root;
		}

		@Override
		public void serialize(Factory result) {
			ISemanticSequencer semantic = semanticSequencerProvider.get();
			ISyntacticSequencer syntactic = syntacticSequencerProvider.get();
			IHiddenTokenSequencer hidden = hiddenTokenSequencerProvider.get();
			semantic.init((ISemanticSequenceAcceptor) syntactic, errorAcceptor);
			syntactic.init(context, root, (ISyntacticSequenceAcceptor) hidden, errorAcceptor);
			ISequenceAcceptor acceptor = result.replaceSequence(context, root);
			hidden.init(context, root, acceptor, errorAcceptor);
			if (acceptor instanceof TokenStreamSequenceAdapter)
				((TokenStreamSequenceAdapter) acceptor).init(context);
			semantic.createSequence(context, root);
		}
	}

	@Inject
	private IContextFinder contextFinder;

	private ISerializationDiagnostic.Acceptor errorAcceptor = ISerializationDiagnostic.EXCEPTION_THROWING_ACCEPTOR;

	@Inject
	private Provider<IHiddenTokenSequencer> hiddenTokenSequencerProvider;

	@Inject
	private Provider<ISemanticSequencer> semanticSequencerProvider;

	@Inject
	private Provider<ISyntacticSequencer> syntacticSequencerProvider;

	protected List<EObjectChange> collectRootChanges(Collection<EObjectChange> roots) {
		List<EObjectChange> result = Lists.newArrayList();
		LinkedList<EObjectChange> stack = new LinkedList<>();
		stack.addAll(roots);
		while (!stack.isEmpty()) {
			EObjectChange candidate = stack.pop();
			if (candidate.getChanges().isEmpty()) {
				stack.addAll(candidate.getChildren());
			} else {
				result.add(candidate);
			}
		}
		return result;
	}

	public ISerializationDiagnostic.Acceptor getErrorAcceptor() {
		return errorAcceptor;
	}

	protected ISerializationContext getIContext(EObject semanticObject) {
		Iterator<ISerializationContext> contexts = contextFinder.findByContentsAndContainer(semanticObject, null)
				.iterator();
		if (!contexts.hasNext())
			throw new RuntimeException("No Context for " + EmfFormatter.objPath(semanticObject) + " could be found");
		return contexts.next();
	}

	public void serializeChanges(XtextResourceChange changes, StringBasedTextRegionAccessDiff.Factory result) {
		List<EObjectChange> rootChanges = collectRootChanges(changes.getContents());
		List<SerializationStrategy> strategies = Lists.newArrayList();
		while (!rootChanges.isEmpty()) {
			List<EObjectChange> nextRoots = Lists.newArrayList();
			for (EObjectChange change : rootChanges) {
				List<SerializationStrategy> features = trySerializeIndividualFeatures(change);
				if (features != null) {
					strategies.addAll(features);
					nextRoots.addAll(change.getChildren());
				} else {
					EObject obj = change.getEObject();
					ISerializationContext context = getIContext(obj);
					strategies.add(new SerializeRecursiveStrategy(obj, context));
				}
			}
			rootChanges = collectRootChanges(nextRoots);
		}
		for (SerializationStrategy strategy : strategies) {
			strategy.serialize(result);
		}
	}

	public void setErrorAcceptor(ISerializationDiagnostic.Acceptor errorAcceptor) {
		this.errorAcceptor = errorAcceptor;
	}

	protected List<SerializationStrategy> trySerializeIndividualFeatures(EObjectChange change) {
		List<SerializationStrategy> result = Lists.newArrayList();
		for (FeatureChange featureChange : change.getChanges()) {
			EStructuralFeature feature = featureChange.getFeature();
			if (feature instanceof EReference || feature.isMany()) {
				return null;
			}
			result.add(new SerializeAttributeStrategy(change.getEObject(), featureChange));
		}
		return result;
	}

}
