/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ide.serializer.debug;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.formatting2.debug.TextRegionsToString;
import org.eclipse.xtext.ide.serializer.ITextDocumentChange;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
public class TextDocumentChangeToString {

	private List<ITextDocumentChange> changes = Lists.newArrayList();

	public TextDocumentChangeToString add(ITextDocumentChange change) {
		changes.add(change);
		return this;
	}

	public TextDocumentChangeToString add(Collection<ITextDocumentChange> changes) {
		this.changes.addAll(changes);
		return this;
	}

	@Override
	public String toString() {
		List<String> result = Lists.newArrayList();
		for (ITextDocumentChange change : changes) {
			TextRegionsToString textRegionsToString = new TextRegionsToString();
			textRegionsToString.addAllReplacements(change.getReplacements());
			String title = getTitle(change);
			textRegionsToString.setTitle(title);
			result.add(textRegionsToString.toString());
		}
		return Joiner.on("\n").join(result);
	}

	protected String getTitle(ITextDocumentChange change) {
		URI newUri = change.getNewURI();
		URI oldURI = change.getOldURI();
		if (oldURI == null && newUri == null) {
			return "error, both URIs are null";
		}
		if (newUri == null) {
			return "deleted " + oldURI;
		}
		if (oldURI == null) {
			return "created " + newUri;
		}
		if (oldURI.equals(newUri)) {
			return oldURI.toString();
		}
		return "renamed " + oldURI + " to " + newUri;
	}

}
