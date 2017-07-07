/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ide.serializer.impl;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccess;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccessDiff;
import org.eclipse.xtext.formatting2.regionaccess.ITextReplacement;
import org.eclipse.xtext.formatting2.regionaccess.TextRegionAccessBuilder;
import org.eclipse.xtext.formatting2.regionaccess.internal.StringBasedTextRegionAccessDiff;
import org.eclipse.xtext.ide.serializer.IChangeSerializer;
import org.eclipse.xtext.ide.serializer.ITextDocumentChange;
import org.eclipse.xtext.ide.serializer.impl.GlobalUpdatableReferencesFinder.FileWithUpdatableReferences;
import org.eclipse.xtext.ide.serializer.impl.SerializerChangeRecorder.XtextResourceChange;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
public class ChangeSerializer implements IChangeSerializer {

	@Inject
	private RegionDiffFormatter formatter;

	@Inject
	private GlobalUpdatableReferencesFinder globalRefs;

	@Inject
	private LocalUpdatableReferencesFinder localRefs;

	@Inject
	private SerializerChangeRecorder recorder;

	@Inject
	private ReferencesUpdater refUpdater;

	@Inject
	private PartialSerializer serializer;

	@Inject
	private Provider<TextRegionAccessBuilder> textRegionBuilderProvider;

	protected TextDocumentChange applyChange(ResourceSet rs, FileWithUpdatableReferences refs) {
		Resource resource = rs.getResource(refs.getUri(), true);
		try {
			globalRefs.applyResolvedReferences(resource, refs);
			if (resource instanceof XtextResource) {
				XtextResource xtextResource = (XtextResource) resource;
				ITextRegionAccess base = textRegionBuilderProvider.get().forNodeModel(xtextResource).create();
				List<UpdatableReference> updatable = globalRefs.getUpdatableReferences(base, refs);
				StringBasedTextRegionAccessDiff.Factory rewriter = new StringBasedTextRegionAccessDiff.Factory(base);
				ImportsUpdater updater = xtextResource.getResourceServiceProvider().get(ImportsUpdater.class);
				updater.updateImports(xtextResource, updatable);
				for (UpdatableReference upd : updatable) {
					refUpdater.updateIfNeeded(rewriter, upd);
				}
				ITextRegionAccessDiff rewritten = rewriter.create();
				List<ITextReplacement> rep = formatter.format(rewritten);
				TextDocumentChange change = new TextDocumentChange(rewritten, refs.getUri(), rep);
				return change;
			} else {
				// TODO: handle
			}
		} finally {
//			resource.unload();
//			rs.getResources().remove(resource);
		}
		return null;
	}

	protected TextDocumentChange applyChange(XtextResourceChange resourceChange) {
		ITextRegionAccess base = resourceChange.getOriginalRegions();
		ImportsUpdater importsUpdater = resourceChange.getLanguageService(ImportsUpdater.class);
		StringBasedTextRegionAccessDiff.Factory rewriter = new StringBasedTextRegionAccessDiff.Factory(base);
		Set<EObject> targets = resourceChange.getModifiedReferenceTargets();
		serializer.serializeChanges(resourceChange, rewriter);
		List<UpdatableReference> updatable = localRefs.getUpdatableReferences(rewriter.getBase(), targets);
		importsUpdater.updateImports((XtextResource) resourceChange.getResource(), updatable);
		for (UpdatableReference upd : updatable) {
			refUpdater.updateIfNeeded(rewriter, upd);
		}
		ITextRegionAccessDiff rewritten = rewriter.create();
		List<ITextReplacement> rep = formatter.format(rewritten);
		TextDocumentChange change = new TextDocumentChange(rewritten, resourceChange.getResource().getURI(), rep);
		return change;
	}

	@Override
	public void beginRecordChanges(Resource resource) {
		recorder.beginRecording(resource);
	}

	@Override
	public Collection<ITextDocumentChange> endRecordChanges() {
		List<XtextResourceChange> recording = recorder.endRecording();
		List<ITextDocumentChange> result = Lists.newArrayList();
		for (XtextResourceChange resourceChange : recording) {
			TextDocumentChange change = applyChange(resourceChange);
			result.add(change);
		}
		Collection<FileWithUpdatableReferences> refs = globalRefs.findFilesWithReferencesToUpdate(recording);
		ResourceSet resourceSet = recorder.getResourceSet();
		for (FileWithUpdatableReferences ref : refs) {
			TextDocumentChange change = applyChange(resourceSet, ref);
			result.add(change);
		}

		return result;
	}

	@Override
	public void setErrorAcceptor(Acceptor acceptor) {
		serializer.setErrorAcceptor(acceptor);
	}

}
