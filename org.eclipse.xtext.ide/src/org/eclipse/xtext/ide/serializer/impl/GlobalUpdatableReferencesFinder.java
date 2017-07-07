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
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.formatting2.regionaccess.IEObjectRegion;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccess;
import org.eclipse.xtext.ide.serializer.impl.SerializerChangeRecorder.ResolvedReferenceDescription;
import org.eclipse.xtext.ide.serializer.impl.SerializerChangeRecorder.XtextResourceChange;
import org.eclipse.xtext.resource.XtextResource;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
public class GlobalUpdatableReferencesFinder {

	public static class FileWithUpdatableReferences {
		private final URI uri;
		private final List<ResolvedReferenceDescription> referenceDescriptions = Lists.newArrayList();

		public FileWithUpdatableReferences(URI uri) {
			super();
			this.uri = uri;
		}

		public List<ResolvedReferenceDescription> getReferenceDescriptions() {
			return referenceDescriptions;
		}

		public URI getUri() {
			return uri;
		}
	}

	public Collection<FileWithUpdatableReferences> findFilesWithReferencesToUpdate(
			Collection<XtextResourceChange> changes) {
		Map<URI, FileWithUpdatableReferences> result = Maps.newLinkedHashMap();
		for (XtextResourceChange change : changes) {
			for (ResolvedReferenceDescription desc : change.getOriginalIncomingReferences()) {
				URI uri = desc.getSourceEObjectUri().trimFragment();
				FileWithUpdatableReferences file = result.get(uri);
				if (file == null) {
					file = new FileWithUpdatableReferences(uri);
					result.put(uri, file);
				}
				file.referenceDescriptions.add(desc);
			}
		}
		return result.values();
	}

	@SuppressWarnings("unchecked")
	public void applyResolvedReferences(Resource resource, FileWithUpdatableReferences references) {
		for (ResolvedReferenceDescription desc : references.getReferenceDescriptions()) {
			EObject source = resource.getEObject(desc.getSourceEObjectUri().fragment());
			EObject target = desc.getTargetEObject();
			EReference reference = desc.getEReference();
			if (reference.isMany()) {
				List<Object> list = (EList<Object>) source.eGet(reference, false);
				list.set(desc.getIndexInList(), target);
			} else {
				source.eSet(reference, target);
			}
		}
	}

	public List<UpdatableReference> getUpdatableReferences(ITextRegionAccess base,
			FileWithUpdatableReferences references) {
		XtextResource resource = base.getResource();
		List<UpdatableReference> result = Lists.newArrayList();
		for (ResolvedReferenceDescription desc : references.getReferenceDescriptions()) {
			EObject source = resource.getEObject(desc.getSourceEObjectUri().fragment());
			EReference reference = desc.getEReference();
			IEObjectRegion objectRegion = base.regionForEObject(source);
			int index = desc.getIndexInList();
			ISemanticRegion region = getRegion(objectRegion, reference, index);
			CrossReference crossref = GrammarUtil.containingCrossReference(region.getGrammarElement());
			result.add(new UpdatableReference(source, reference, index, desc.getTargetEObject(), crossref, region));
		}
		return result;
	}

	private ISemanticRegion getRegion(IEObjectRegion objectRegion, EReference reference, int index) {
		if (reference.isMany()) {
			List<ISemanticRegion> regions = objectRegion.getRegionFor().features(reference);
			return regions.get(index);
		} else {
			return objectRegion.getRegionFor().feature(reference);
		}
	}

}
