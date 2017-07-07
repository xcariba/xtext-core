/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ide.serializer.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.formatting2.regionaccess.IEObjectRegion;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccess;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
public class LocalUpdatableReferencesFinder {

	public List<UpdatableReference> getUpdatableReferences(ITextRegionAccess base, Set<EObject> targets) {
		List<UpdatableReference> result = Lists.newArrayList();
		EObject root = base.regionForRootEObject().getSemanticElement();
		TreeIterator<EObject> iterator = EcoreUtil2.eAll(root);
		while (iterator.hasNext()) {
			EObject next = iterator.next();
			IEObjectRegion region = base.regionForEObject(next);
			if (region == null) {
				continue;
			}
			Map<EReference, Integer> indices = Maps.newHashMap();
			for (ISemanticRegion sem : region.getSemanticRegions()) {
				CrossReference crossReference = GrammarUtil.containingCrossReference(sem.getGrammarElement());
				if (crossReference == null) {
					continue;
				}
				EReference ref = GrammarUtil.getReference(crossReference, next.eClass());
				if (ref == null || ref.isContainment()) {
					continue;
				}
				if (ref.isMany()) {
					Integer index = indices.get(ref);
					if (index == null) {
						index = 0;
					}
					indices.put(ref, index + 1);
					Object target = ((List<?>) next.eGet(ref)).get(index);
					if (targets.contains(target)) {
						result.add(new UpdatableReference(next, ref, index, (EObject) target, crossReference, sem));
					}
				} else {
					Object target = next.eGet(ref);
					if (targets.contains(target)) {
						result.add(new UpdatableReference(next, ref, -1, (EObject) target, crossReference, sem));
					}
				}
			}
		}
		return result;
	}

}
