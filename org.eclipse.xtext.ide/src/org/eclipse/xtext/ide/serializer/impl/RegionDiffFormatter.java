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

import org.eclipse.xtext.formatting2.FormatterRequest;
import org.eclipse.xtext.formatting2.IFormatter2;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccess;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccessDiff;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionRewriter;
import org.eclipse.xtext.formatting2.regionaccess.ITextReplacement;
import org.eclipse.xtext.formatting2.regionaccess.ITextSegment;
import org.eclipse.xtext.formatting2.regionaccess.ITextSegmentDiff;
import org.eclipse.xtext.util.ITextRegion;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
public class RegionDiffFormatter {

	@Inject
	private IFormatter2 formatter;
	@Inject
	private Provider<FormatterRequest> formatterRequest;

	public List<ITextReplacement> format(ITextRegionAccess regions) {
		if (regions instanceof ITextRegionAccessDiff) {
			ITextRegionAccessDiff diff = (ITextRegionAccessDiff) regions;
			FormatterRequest request = createFormatterRequest(diff);
			List<ITextReplacement> replacements = formatter.format(request);
			List<ITextReplacement> merged = mergeReplacements(diff, replacements);
			return merged;
		} else {
			FormatterRequest request = createFormatterRequest(regions);
			List<ITextReplacement> replacements = formatter.format(request);
			return replacements;
		}
	}

	protected FormatterRequest createFormatterRequest(ITextRegionAccess rewritten) {
		FormatterRequest request = formatterRequest.get();
		request.setTextRegionAccess(rewritten);
		return request;
	}

	protected FormatterRequest createFormatterRequest(ITextRegionAccessDiff rewritten) {
		FormatterRequest request = formatterRequest.get();
		request.setTextRegionAccess(rewritten);
		request.setRegions(collectRegionsToFormat(rewritten));
		return request;
	}

	protected Collection<ITextRegion> collectRegionsToFormat(ITextRegionAccessDiff regions) {
		List<ITextRegion> result = Lists.newArrayList();
		for (ITextSegmentDiff diff : regions.getDifferences()) {
			int offset = diff.getModifiedFirstRegion().getOffset();
			int length = diff.getModifiedLastRegion().getEndOffset() - offset;
			ITextSegment region = regions.regionForOffset(offset, length);
			result.add(region);
		}
		return result;
	}

	protected List<ITextReplacement> mergeReplacements(ITextRegionAccessDiff regions, List<ITextReplacement> rep) {
		ITextRegionRewriter rewriter = regions.getOriginalTextRegionAccess().getRewriter();
		List<ITextReplacement> result = Lists.newArrayList();
		for (ITextSegmentDiff r : regions.getDifferences()) {
			int originalStart = r.getOriginalFirstRegion().getOffset();
			int originalLength = r.getOriginalLastRegion().getEndOffset() - originalStart;
			int modifiedStart = r.getModifiedFirstRegion().getOffset();
			int modifiedLength = r.getModifiedLastRegion().getEndOffset() - modifiedStart;
			ITextSegment modifiedRegion = regions.regionForOffset(modifiedStart, modifiedLength);
			List<ITextReplacement> local = Lists.newArrayList();
			for (ITextReplacement re : rep) {
				if (modifiedRegion.contains(re)) {
					local.add(re);
				}
			}
			String newText;
			if (local.isEmpty()) {
				newText = modifiedRegion.getText();
			} else {
				newText = regions.getRewriter().renderToString(modifiedRegion, local);
			}
			ITextReplacement replacement = rewriter.createReplacement(originalStart, originalLength, newText);
			result.add(replacement);
		}
		return result;
	}

}
