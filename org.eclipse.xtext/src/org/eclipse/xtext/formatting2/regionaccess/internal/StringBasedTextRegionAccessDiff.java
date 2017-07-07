/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.formatting2.regionaccess.internal;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.formatting2.regionaccess.IComment;
import org.eclipse.xtext.formatting2.regionaccess.IEObjectRegion;
import org.eclipse.xtext.formatting2.regionaccess.IHiddenRegion;
import org.eclipse.xtext.formatting2.regionaccess.IHiddenRegionPart;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion;
import org.eclipse.xtext.formatting2.regionaccess.ISequentialRegion;
import org.eclipse.xtext.formatting2.regionaccess.ISequentialRegionDiff;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccess;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccessDiff;
import org.eclipse.xtext.formatting2.regionaccess.ITextSegment;
import org.eclipse.xtext.formatting2.regionaccess.ITextSegmentDiff;
import org.eclipse.xtext.formatting2.regionaccess.IWhitespace;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.ISequenceAcceptor;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
public class StringBasedTextRegionAccessDiff extends StringBasedRegionAccess implements ITextRegionAccessDiff {

	protected static class Appender {
		protected StringBasedTextRegionAccessDiff access;
		protected ISequentialRegion last;

		public Appender(StringBasedTextRegionAccessDiff access) {
			super();
			this.access = access;
		}

		protected IHiddenRegion copyAndAppend(IHiddenRegion source) {
			StringSemanticRegion previous = (StringSemanticRegion) this.last;
			StringHiddenRegion region = new StringHiddenRegion(access);
			if (previous != null) {
				previous.setTrailingHiddenRegion(region);
				region.setPrevious(previous);
			}
			region.setUndefined(source.isUndefined());
			this.last = region;
			for (IHiddenRegionPart part : source.getParts()) {
				String text = part.getText();
				int offset = access.append(text);
				if (part instanceof IComment) {
					IComment comment = ((IComment) part);
					AbstractRule grammarElement = (AbstractRule) comment.getGrammarElement();
					StringComment newComment = new StringComment(region, grammarElement, offset, text.length());
					region.addPart(newComment);
				} else if (part instanceof IWhitespace) {
					IWhitespace ws = (IWhitespace) part;
					AbstractRule grammarElement = (AbstractRule) ws.getGrammarElement();
					StringWhitespace newWs = new StringWhitespace(region, grammarElement, offset, text.length());
					region.addPart(newWs);
				}
			}
			return region;
		}

		public ISemanticRegion copyAndAppend(ISemanticRegion source, String text) {
			EObject semanticElement = source.getSemanticElement();
			AbstractEObjectRegion region = access.regionForEObject(semanticElement);
			if (region == null) {
				IEObjectRegion region2 = source.getEObjectRegion();
				region = new StringEObjectRegion(access, region2.getGrammarElement(), semanticElement);
				access.add(region);
			}
			int offset = access.append(text);
			AbstractElement grammar = (AbstractElement) source.getGrammarElement();
			StringHiddenRegion previous = (StringHiddenRegion) this.last;
			StringSemanticRegion result = new StringSemanticRegion(access, region, grammar, offset, text.length());
			region.getSemanticRegions().add(result);
			previous.setNext(result);
			result.setLeadingHiddenRegion(previous);
			this.last = result;
			return result;
		}

		public ISequentialRegion copyAndAppend(ISequentialRegion first, ISequentialRegion last) {
			ISequentialRegion current = first;
			ISequentialRegion result = null;
			while (true) {
				if (current instanceof IHiddenRegion) {
					copyAndAppend((IHiddenRegion) current);
				} else if (current instanceof ISemanticRegion) {
					copyAndAppend((ISemanticRegion) current, current.getText());
				}
				if (result == null) {
					result = this.last;
				}
				if (current == last) {
					break;
				}
				current = current.getNextSequentialRegion();
				if (current == null) {
					throw new IllegalStateException("last didn't match");
				}
			}
			return result;
		}

		public Rewrite copyAndAppend(Rewrite rewrite) {
			ISequentialRegion copiedFirst = copyAndAppend(rewrite.substituteFirst, rewrite.substituteLast);
			ISequentialRegion copiedLast = this.last;
			return new Rewrite(access.original, rewrite.originalFirst, rewrite.originalLast, copiedFirst, copiedLast);
		}

		public void updateEObjectRegions() {
			ISemanticRegion current = this.last.getPreviousSemanticRegion();
			while (true) {
				ISemanticRegion sem = (ISemanticRegion) current;
				EObject eobj = sem.getSemanticElement();
				IHiddenRegion nextHiddenRegion = sem.getNextHiddenRegion();
				while (eobj != null) {
					AbstractEObjectRegion eobjRegion = access.regionForEObject(eobj);
					if (eobjRegion.getNextHiddenRegion() != null) {
						break;
					}
					eobjRegion.setTrailingHiddenRegion(nextHiddenRegion);
					eobj = eobj.eContainer();
				}
				ISemanticRegion prev = sem.getPreviousSemanticRegion();
				if (prev == null) {
					break;
				} else {
					current = prev;
				}
			}
			while (true) {
				ISemanticRegion sem = (ISemanticRegion) current;
				EObject eobj = sem.getSemanticElement();
				IHiddenRegion previousHiddenRegion = sem.getPreviousHiddenRegion();
				while (eobj != null) {
					AbstractEObjectRegion eobjRegion = access.regionForEObject(eobj);
					if (eobjRegion.getPreviousHiddenRegion() != null) {
						break;
					}
					eobjRegion.setLeadingHiddenRegion(previousHiddenRegion);
					eobj = eobj.eContainer();
				}
				ISemanticRegion next = sem.getNextSemanticRegion();
				if (next == null) {
					break;
				} else {
					current = next;
				}
			}
		}

	}

	public static class Factory {

		private final ITextRegionAccess base;
		private List<RewriteAction> rewrites = Lists.newArrayList();
		private List<TextRegionAccessBuildingSequencer> sequencers = Lists.newArrayList();

		public Factory(ITextRegionAccess base) {
			super();
			this.base = base;
		}

		public StringBasedTextRegionAccessDiff create() {
			flushSequencers();
			Collections.sort(rewrites);
			IEObjectRegion root = base.regionForRootEObject();
			ISequentialRegion last = root.getPreviousHiddenRegion();
			StringBasedTextRegionAccessDiff result = new StringBasedTextRegionAccessDiff(base);
			Appender appender = new Appender(result);
			for (RewriteAction next : rewrites) {
				if (!last.equals(next.originalFirst)) {
					appender.copyAndAppend(last, next.originalFirst.getPreviousSequentialRegion());
				}
				Rewrite rewrite = next.apply(appender);
				result.append(rewrite);
				last = next.originalLast.getNextSequentialRegion();
			}
			if (last != null) {
				appender.copyAndAppend(last, root.getNextSequentialRegion());
			}
			AbstractEObjectRegion newRoot = result.regionForEObject(root.getSemanticElement());
			result.setRootEObject(newRoot);
			appender.updateEObjectRegions();
			return result;
		}

		protected void flushSequencers() {
			for (TextRegionAccessBuildingSequencer rewrite : sequencers) {
				StringBasedRegionAccess access = rewrite.getRegionAccess();
				EObject root = access.regionForRootEObject().getSemanticElement();
				IEObjectRegion rootInOriginal = base.regionForEObject(root);
				IHiddenRegion originalFirst = rootInOriginal.getPreviousHiddenRegion();
				ISemanticRegion originalLast = rootInOriginal.getNextHiddenRegion().getPreviousSemanticRegion();
				rewrites.add(new RegionAccessRewriteAction(originalFirst, originalLast, access));
			}
			sequencers.clear();
		}

		public ITextRegionAccess getBase() {
			return base;
		}

		public boolean isModified(ITextSegment region) {
			if (region.getTextRegionAccess() != base) {
				throw new IllegalStateException("text region is from unknown region access.");
			}
			int offset = region.getOffset();
			int endOffset = region.getEndOffset();
			for (RewriteAction action : rewrites) {
				int rwOffset = action.originalFirst.getOffset();
				int rwEndOffset = action.originalLast.getEndOffset();
				if (rwOffset <= offset && offset < rwEndOffset) {
					return true;
				}
				if (rwOffset < endOffset && endOffset <= rwEndOffset) {
					return true;
				}
			}
			return false;
		}

		public void replace(IHiddenRegion originalFirst, ISemanticRegion originalLast, ITextRegionAccess acc) {
			rewrites.add(new RegionAccessRewriteAction(originalFirst, originalLast, acc));
		}

		public void replace(ISemanticRegion region, String newText) {
			rewrites.add(new TextRewriteAction(region, newText));
		}

		public ISequenceAcceptor replaceSequence(ISerializationContext ctx, EObject root) {
			TextRegionAccessBuildingSequencer sequenceAcceptor = new TextRegionAccessBuildingSequencer();
			sequencers.add(sequenceAcceptor);
			return sequenceAcceptor.withRoot(ctx, root);
		}

	}

	protected static class RegionAccessRewriteAction extends RewriteAction {
		private final ITextRegionAccess textRegionAccess;

		public RegionAccessRewriteAction(ISequentialRegion originalFirst, ISequentialRegion originalLast,
				ITextRegionAccess textRegionAccess) {
			super(originalFirst, originalLast);
			this.textRegionAccess = textRegionAccess;
		}

		@Override
		public Rewrite apply(Appender appender) {
			IEObjectRegion substituteRoot = textRegionAccess.regionForRootEObject();
			IHiddenRegion substituteFirst = substituteRoot.getPreviousHiddenRegion();
			ISemanticRegion substituteLast = substituteRoot.getNextHiddenRegion().getPreviousSemanticRegion();
			Rewrite rw = new Rewrite(textRegionAccess, originalFirst, originalLast, substituteFirst, substituteLast);
			Rewrite result = appender.copyAndAppend(rw);
			return result;
		}
	}

	protected static class Rewrite implements Comparable<Rewrite>, ISequentialRegionDiff {
		private final ITextRegionAccess originalAccess;
		private final ISequentialRegion originalFirst;
		private final ISequentialRegion originalLast;
		private ISequentialRegion substituteFirst;
		private ISequentialRegion substituteLast;

		public Rewrite(ITextRegionAccess originalAccess, ISequentialRegion originalFirst,
				ISequentialRegion originalLast) {
			this(originalAccess, originalFirst, originalLast, null, null);
		}

		public Rewrite(ITextRegionAccess originalAccess, ISequentialRegion originalFirst,
				ISequentialRegion originalLast, ISequentialRegion substituteFirst, ISequentialRegion substituteLast) {
			super();
			this.originalAccess = originalAccess;
			this.originalFirst = originalFirst;
			this.originalLast = originalLast;
			this.substituteFirst = substituteFirst;
			this.substituteLast = substituteLast;
		}

		@Override
		public int compareTo(Rewrite o) {
			return originalFirst.compareTo(o.originalFirst);
		}

		@Override
		public ISequentialRegion getModifiedFirstRegion() {
			return this.substituteFirst;
		}

		@Override
		public ISequentialRegion getModifiedLastRegion() {
			return this.substituteLast;
		}

		@Override
		public ISequentialRegion getOriginalFirstRegion() {
			return this.originalFirst;
		}

		@Override
		public ISequentialRegion getOriginalLastRegion() {
			return this.originalLast;
		}

		@Override
		public ITextRegionAccess getOriginalTextRegionAccess() {
			return originalAccess;
		}
	}

	public abstract static class RewriteAction implements Comparable<RewriteAction> {
		protected final ISequentialRegion originalFirst;
		protected final ISequentialRegion originalLast;

		public RewriteAction(ISequentialRegion originalFirst, ISequentialRegion originalLast) {
			super();
			this.originalFirst = originalFirst;
			this.originalLast = originalLast;
		}

		public abstract Rewrite apply(Appender appender);

		@Override
		public int compareTo(RewriteAction o) {
			return originalFirst.compareTo(o.originalFirst);
		}
	}

	public static class TextRewriteAction extends RewriteAction {

		private String text;

		public TextRewriteAction(ISemanticRegion region, String text) {
			super(region, region);
			Preconditions.checkNotNull(region);
			Preconditions.checkNotNull(text);
			this.text = text;
		}

		@Override
		public Rewrite apply(Appender appender) {
			ISemanticRegion region = appender.copyAndAppend((ISemanticRegion) originalFirst, text);
			return new Rewrite(appender.access.original, originalFirst, originalLast, region, region);
		}

	}

	private final List<ITextSegmentDiff> diffs = Lists.newArrayList();
	private final ITextRegionAccess original;

	protected StringBasedTextRegionAccessDiff(ITextRegionAccess original) {
		super(original.getResource());
		this.original = original;
	}

	protected void append(Rewrite rewrite) {
		this.diffs.add(rewrite);
	}

	@Override
	public List<ITextSegmentDiff> getDifferences() {
		return diffs;
	}

	@Override
	public ITextRegionAccess getOriginalTextRegionAccess() {
		return original;
	}

}
