/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ide.serializer.impl;

import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion;
import org.eclipse.xtext.formatting2.regionaccess.internal.StringBasedTextRegionAccessDiff;
import org.eclipse.xtext.linking.impl.LinkingHelper;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;

import com.google.inject.Inject;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
public class ReferencesUpdater {

	@Inject
	private LinkingHelper linkingHelper;

	@Inject
	private IQualifiedNameConverter nameConverter;

	@Inject
	private IScopeProvider scopeProvider;

	@Inject
	private IValueConverterService valueConverter;

	protected String findValidName(UpdatableReference updatable, IScope scope) {
		Iterable<IEObjectDescription> elements = scope.getElements(updatable.getTargetEObject());
		String ruleName = linkingHelper.getRuleNameFrom(updatable.getCrossReference());
		for (IEObjectDescription desc : elements) {
			try {
				String unconverted = nameConverter.toString(desc.getName());
				String string = valueConverter.toString(unconverted, ruleName);
				return string;
			} catch (ValueConverterException e) {
				// do nothing
			}
		}
		return null;
	}

	protected void updateIfNeeded(StringBasedTextRegionAccessDiff.Factory rewriter, UpdatableReference updatable) {
		if (rewriter.isModified(updatable.getReferenceRegion())) {
			return;
		}
		IScope scope = scopeProvider.getScope(updatable.getSourceEObject(), updatable.getEReference());
		ISemanticRegion region = updatable.getReferenceRegion();
		QualifiedName oldName = nameConverter.toQualifiedName(region.getText());
		IEObjectDescription oldDesc = scope.getSingleElement(oldName);
		if (oldDesc != null && oldDesc.getEObjectOrProxy() == updatable.getTargetEObject()) {
			return;
		}
		String newName = findValidName(updatable, scope);
		rewriter.replace(region, newName);
	}

}
