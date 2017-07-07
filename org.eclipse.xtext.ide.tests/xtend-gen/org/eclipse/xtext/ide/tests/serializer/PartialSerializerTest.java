/**
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.ide.tests.serializer;

import com.google.inject.Inject;
import javax.inject.Provider;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccess;
import org.eclipse.xtext.ide.serializer.impl.ChangeSerializer;
import org.eclipse.xtext.ide.tests.serializer.ChangeSerializerTestHelper;
import org.eclipse.xtext.ide.tests.testlanguage.partialSerializationTestLanguage.Value;
import org.eclipse.xtext.ide.tests.testlanguage.tests.PartialSerializationTestLanguageInjectorProvider;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.InMemoryURIHandler;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
@RunWith(XtextRunner.class)
@InjectWith(PartialSerializationTestLanguageInjectorProvider.class)
@SuppressWarnings("all")
public class PartialSerializerTest {
  @Inject
  private Provider<ChangeSerializer> serializerProvider;
  
  @Inject
  @Extension
  private ChangeSerializerTestHelper _changeSerializerTestHelper;
  
  @Test
  public void testSimple() {
    final InMemoryURIHandler fs = new InMemoryURIHandler();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#1 foo");
    Pair<String, String> _mappedTo = Pair.<String, String>of("inmemory:/file1.pstl", _builder.toString());
    this._changeSerializerTestHelper.operator_add(fs, _mappedTo);
    final ResourceSet rs = this._changeSerializerTestHelper.createResourceSet(fs);
    final Value model = this._changeSerializerTestHelper.<Value>contents(rs, "inmemory:/file1.pstl", Value.class);
    final ChangeSerializer serializer = this.serializerProvider.get();
    serializer.beginRecordChanges(model.eResource());
    model.setName("bar");
    ITextRegionAccess _textRegionAccess = this._changeSerializerTestHelper.getTextRegionAccess(serializer.endRecordChanges());
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("0 0   H");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("B Value\'bar\'           Model");
    _builder_1.newLine();
    _builder_1.append("0 2    S \"#1\"                 Value:\'#1\'");
    _builder_1.newLine();
    _builder_1.append("2 1    H \" \"                  Whitespace:TerminalRule\'WS\'");
    _builder_1.newLine();
    _builder_1.append("3 3 1  S \"bar\"                Value:name=ID");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("E Value\'bar\'           Model");
    _builder_1.newLine();
    _builder_1.append("6 0   H");
    _builder_1.newLine();
    _builder_1.append("------------ diff 1 ------------");
    _builder_1.newLine();
    _builder_1.append("3 3 S \"foo\"                Value:name=ID");
    _builder_1.newLine();
    this._changeSerializerTestHelper.operator_tripleEquals(_textRegionAccess, _builder_1);
  }
}
