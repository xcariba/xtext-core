/**
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.ide.tests.serializer;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.formatting2.debug.TextRegionAccessToString;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccess;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccessDiff;
import org.eclipse.xtext.ide.serializer.ITextDocumentChange;
import org.eclipse.xtext.ide.serializer.debug.TextDocumentChangeToString;
import org.eclipse.xtext.ide.serializer.impl.TextDocumentChange;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.testing.util.InMemoryURIHandler;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.Assert;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
@SuppressWarnings("all")
public class ChangeSerializerTestHelper {
  @Inject
  private IResourceDescription.Manager rdManager;
  
  public void operator_tripleEquals(final Collection<ITextDocumentChange> actual, final CharSequence expected) {
    final String actualString = new TextDocumentChangeToString().add(actual).toString();
    Assert.assertEquals(expected.toString().trim(), actualString.trim());
  }
  
  public void operator_tripleEquals(final ITextRegionAccess actual, final CharSequence expected) {
    final String actualString = new TextRegionAccessToString().withRegionAccess(actual).hideColumnExplanation().toString();
    Assert.assertEquals(expected.toString().trim(), actualString.trim());
  }
  
  public void operator_add(final InMemoryURIHandler handler, final Pair<String, String> file) {
    final InMemoryURIHandler.InMemFile f = handler.getInMemoryFile(URI.createURI(file.getKey()));
    f.setContents(file.getValue().getBytes());
    f.setExists(true);
  }
  
  public ResourceSet createResourceSet(final InMemoryURIHandler fs) {
    final XtextResourceSet idx = new XtextResourceSet();
    idx.getURIConverter().getURIHandlers().add(0, fs);
    Collection<InMemoryURIHandler.InMemFile> _values = fs.getFiles().values();
    for (final InMemoryURIHandler.InMemFile f : _values) {
      idx.getResource(f.getUri(), true);
    }
    EcoreUtil.resolveAll(idx);
    final Function1<Resource, IResourceDescription> _function = (Resource it) -> {
      return this.rdManager.getResourceDescription(it);
    };
    final List<IResourceDescription> rsd = ListExtensions.<Resource, IResourceDescription>map(idx.getResources(), _function);
    final ResourceDescriptionsData data = new ResourceDescriptionsData(rsd);
    final XtextResourceSet r = new XtextResourceSet();
    r.getLoadOptions().put(ResourceDescriptionsProvider.LIVE_SCOPE, Boolean.TRUE);
    r.getURIConverter().getURIHandlers().add(0, fs);
    ResourceDescriptionsData.ResourceSetAdapter.installResourceDescriptionsData(r, data);
    return r;
  }
  
  public <T extends Object> T contents(final ResourceSet rs, final String fileName, final Class<T> type) {
    return IterableExtensions.<T>head(Iterables.<T>filter(rs.getResource(URI.createURI(fileName), true).getContents(), type));
  }
  
  public ITextRegionAccess getTextRegionAccess(final Collection<ITextDocumentChange> changes) {
    final Function1<TextDocumentChange, ITextRegionAccess> _function = (TextDocumentChange it) -> {
      ITextRegionAccessDiff _textRegionAccess = it.getTextRegionAccess();
      return ((ITextRegionAccess) _textRegionAccess);
    };
    return IterableExtensions.<ITextRegionAccess>head(IterableExtensions.<TextDocumentChange, ITextRegionAccess>map(Iterables.<TextDocumentChange>filter(changes, TextDocumentChange.class), _function));
  }
}
