/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.formatting2.regionaccess;

public interface ISequentialRegionDiff extends ITextSegmentDiff {

	// TODO: the 'origial' regions should be the regions 'before' and 'after'
	// the change. This is needed to express insertion.

	ISequentialRegion getOriginalFirstRegion();

	ISequentialRegion getOriginalLastRegion();

	ISequentialRegion getModifiedFirstRegion();

	ISequentialRegion getModifiedLastRegion();
}