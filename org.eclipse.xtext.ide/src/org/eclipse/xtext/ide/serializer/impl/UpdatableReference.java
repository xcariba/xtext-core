/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ide.serializer.impl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion;

class UpdatableReference {
	private final CrossReference crossref;
	private final int index;
	private final EObject owner;
	private final EReference reference;
	private final ISemanticRegion region;
	private final EObject target;

	public UpdatableReference(EObject owner, EReference reference, int index, EObject target, CrossReference crossref,
			ISemanticRegion region) {
		super();
		this.owner = owner;
		this.reference = reference;
		this.index = index;
		this.target = target;
		this.crossref = crossref;
		this.region = region;
	}

	public CrossReference getCrossReference() {
		return crossref;
	}

	public int getIndexInList() {
		return index;
	}

	public EObject getSourceEObject() {
		return owner;
	}

	public EObject getTargetEObject() {
		return target;
	}

	public ISemanticRegion getReferenceRegion() {
		return region;
	}

	public EReference getEReference() {
		return reference;
	}

}