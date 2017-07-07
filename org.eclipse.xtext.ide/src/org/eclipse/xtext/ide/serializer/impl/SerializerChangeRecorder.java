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

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.ResourceChange;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccess;
import org.eclipse.xtext.formatting2.regionaccess.TextRegionAccessBuilder;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Manager;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.IResourceDescriptionsProvider;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.Arrays;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
public class SerializerChangeRecorder {

	public static class EObjectChange {
		private final EList<FeatureChange> changes;
		private final Set<EObjectChange> children = Sets.newLinkedHashSet();
		private final EObject eObject;

		public EObjectChange(EObject eObject) {
			this(eObject, ECollections.emptyEList());

		}

		public EObjectChange(EObject eObject, EList<FeatureChange> changes) {
			super();
			this.eObject = eObject;
			this.changes = changes;
		}

		public EList<FeatureChange> getChanges() {
			return changes;
		}

		public Set<EObjectChange> getChildren() {
			return children;
		}

		public EObject getEObject() {
			return eObject;
		}
	}

	public static class ResolvedReferenceDescription {
		private URI containerEObjectURI;
		private EReference eReference;
		private int indexInList = -1;
		private URI sourceEObjectUri;
		private EObject targetEObject;

		public ResolvedReferenceDescription(URI sourceEObjectUri, EObject targetEObject, EReference eReference,
				int indexInList, URI containerEObjectURI) {
			super();
			this.indexInList = indexInList;
			this.sourceEObjectUri = sourceEObjectUri;
			this.targetEObject = targetEObject;
			this.eReference = eReference;
			this.containerEObjectURI = containerEObjectURI;
		}

		public URI getContainerEObjectURI() {
			return containerEObjectURI;
		}

		public EReference getEReference() {
			return eReference;
		}

		public int getIndexInList() {
			return indexInList;
		}

		public URI getSourceEObjectUri() {
			return sourceEObjectUri;
		}

		public EObject getTargetEObject() {
			return targetEObject;
		}
	}

	public static class XtextResourceChange {
		private final Set<EObjectChange> contents = Sets.newLinkedHashSet();
		private List<IEObjectDescription> modifiedEObjectDescriptions;
		private Set<EObject> modifiedReferenceTargets;
		private List<IEObjectDescription> originalEObjectDescriptions;
		private List<ResolvedReferenceDescription> originalIncomingReferences;
		private ITextRegionAccess originalRegions;
		private final Resource resource;
		private ResourceChange resourceChange;

		public XtextResourceChange(Resource resource) {
			this(resource, null);
		}

		public XtextResourceChange(Resource resource, ResourceChange resourceChange) {
			super();
			this.resource = resource;
			this.resourceChange = resourceChange;
		}

		public Set<EObjectChange> getContents() {
			return contents;
		}

		public <T> T getLanguageService(Class<T> clazz) {
			if (resource instanceof XtextResource) {
				return ((XtextResource) resource).getResourceServiceProvider().get(clazz);
			}
			return IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(resource.getURI()).get(clazz);
		}

		public List<IEObjectDescription> getModifiedEObjectDescriptions() {
			return modifiedEObjectDescriptions;
		}

		public Set<EObject> getModifiedReferenceTargets() {
			return modifiedReferenceTargets;
		}

		public List<IEObjectDescription> getOriginalEObjectDescriptions() {
			return originalEObjectDescriptions;
		}

		public List<ResolvedReferenceDescription> getOriginalIncomingReferences() {
			return originalIncomingReferences;
		}

		public ITextRegionAccess getOriginalRegions() {
			return originalRegions;
		}

		public Resource getResource() {
			return resource;
		}

		public ResourceChange getResourceChange() {
			return resourceChange;
		}
	}

	private Map<Resource, XtextResourceChange> changes = Maps.newHashMap();

	private ChangeRecorder recorder = null;

	public ResourceSet getResourceSet() {
		for (XtextResourceChange c : changes.values()) {
			return c.getResource().getResourceSet();
		}
		throw new IllegalStateException();
	}

	@Inject
	private IResourceDescriptionsProvider resourceDescriptionsProvider;

	@Inject
	private Provider<TextRegionAccessBuilder> textRegionBuilderProvider;

	public void beginRecording(Resource resource) {
		EcoreUtil.resolveAll(resource);
		if (recorder == null) {
			recorder = new ChangeRecorder(resource.getResourceSet());
		} else {
			if (getResourceSet() != resource.getResourceSet()) {
				throw new IllegalStateException("Wrong resource.");
			}
		}
		XtextResourceChange change = new XtextResourceChange(resource);
		if (resource instanceof XtextResource) {
			XtextResource res = (XtextResource) resource;
			change.originalRegions = textRegionBuilderProvider.get().forNodeModel(res).create();
		}
		change.originalEObjectDescriptions = getEObjectDescriptions(change);
		change.originalIncomingReferences = getIncomingReferences(change);
		changes.put(resource, change);
	}

	protected IEObjectDescription copyEObjectDesc(IEObjectDescription from) {
		Map<String, String> userData = null;
		for (final String key : from.getUserDataKeys()) {
			if (userData == null) {
				userData = Maps.newHashMapWithExpectedSize(2);
			}
			userData.put(key, from.getUserData(key));
		}
		return EObjectDescription.create(from.getName(), from.getEObjectOrProxy(), userData);
	}

	protected List<XtextResourceChange> createChangeTree(ChangeDescription desc) {
		Map<EObject, EObjectChange> objectChanges = Maps.newLinkedHashMap();
		for (ResourceChange ch : desc.getResourceChanges()) {
			Resource res = ch.getResource();
			XtextResourceChange change = changes.get(res);
			if (change == null) {
				throw new IllegalStateException("Changes in a non-recorded resource: " + res.getURI());
			}
		}
		for (Map.Entry<EObject, EList<FeatureChange>> e : desc.getObjectChanges()) {
			EObject object = e.getKey();
			EList<FeatureChange> featureChanges = e.getValue();
			objectChanges.put(object, new EObjectChange(object, featureChanges));
		}
		for (EObjectChange change : Lists.newArrayList(objectChanges.values())) {
			EObjectChange current = change;
			while (true) {
				EObject container = current.eObject.eContainer();
				if (container != null) {
					EObjectChange next = objectChanges.get(container);
					if (next == null) {
						next = new EObjectChange(container);
						next.children.add(current);
						objectChanges.put(container, next);
					} else {
						if (!next.children.add(current)) {
							break;
						}
					}
					current = next;
				} else {
					Resource resource = current.eObject.eResource();
					XtextResourceChange resourceChange = changes.get(resource);
					if (resourceChange == null) {
						throw new IllegalStateException("Changes in a non-recorded resource: " + resource.getURI());
					}
					resourceChange.contents.add(current);
					break;
				}
			}
		}

		return ImmutableList.copyOf(changes.values());
	}

	public List<XtextResourceChange> endRecording() {
		ChangeDescription recording = recorder.endRecording();
		for (XtextResourceChange change : changes.values()) {
			change.modifiedEObjectDescriptions = getEObjectDescriptions(change);
			change.modifiedReferenceTargets = getChangedTargets(change);
		}
		return createChangeTree(recording);
	}

	protected boolean equals(IEObjectDescription oldObj, IEObjectDescription newObj) {
		if (oldObj == newObj)
			return true;
		if (oldObj == null || newObj == null)
			return false;
		if (oldObj.getName() != null && !oldObj.getName().equals(newObj.getName()))
			return false;
		String[] oldKeys = oldObj.getUserDataKeys();
		String[] newKeys = newObj.getUserDataKeys();
		if (oldKeys.length != newKeys.length)
			return false;
		for (String key : oldKeys) {
			if (!Arrays.contains(newKeys, key))
				return false;
			String oldValue = oldObj.getUserData(key);
			String newValue = newObj.getUserData(key);
			if (!Objects.equal(oldValue, newValue))
				return false;
		}
		return true;
	}

	protected Set<EObject> getChangedTargets(XtextResourceChange change) {
		Map<EObject, IEObjectDescription> originals = Maps.newHashMap();
		for (IEObjectDescription desc : change.getOriginalEObjectDescriptions()) {
			originals.put(desc.getEObjectOrProxy(), desc);
		}
		Set<EObject> result = Sets.newLinkedHashSet();
		for (IEObjectDescription desc : change.getModifiedEObjectDescriptions()) {
			EObject object = desc.getEObjectOrProxy();
			if (result.contains(object)) {
				continue;
			}
			IEObjectDescription original = originals.get(object);
			if (!equals(desc, original)) {
				TreeIterator<EObject> all = EcoreUtil2.eAll(object);
				while (all.hasNext()) {
					result.add(all.next());
				}
			}
		}
		return result;
	}

	protected List<IEObjectDescription> getEObjectDescriptions(XtextResourceChange change) {
		Manager manager = change.getLanguageService(IResourceDescription.Manager.class);
		IResourceDescription description = manager.getResourceDescription(change.getResource());
		return Lists.newArrayList(Iterables.transform(description.getExportedObjects(), this::copyEObjectDesc));
	}

	protected List<ResolvedReferenceDescription> getIncomingReferences(XtextResourceChange change) {
		List<ResolvedReferenceDescription> result = Lists.newArrayList();
		Resource resource = change.resource;
		ResourceSet resourceSet = resource.getResourceSet();
		IResourceDescriptions descriptions = resourceDescriptionsProvider.getResourceDescriptions(resourceSet);
		URI uri = resource.getURI();
		for (IResourceDescription desc : descriptions.getAllResourceDescriptions()) {
			for (IReferenceDescription ref : desc.getReferenceDescriptions()) {
				URI targetURI = ref.getTargetEObjectUri();
				if (uri.equals(targetURI.trimFragment())) {
					EObject target = resource.getEObject(targetURI.fragment());
					result.add(new ResolvedReferenceDescription(ref.getSourceEObjectUri(), target, ref.getEReference(),
							ref.getIndexInList(), ref.getContainerEObjectURI()));
				}
			}
		}
		return result;
	}

}
