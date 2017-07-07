/**
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.formatting2.regionaccess.internal;

import com.google.inject.Inject;
import javax.inject.Provider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.formatting2.debug.TextRegionAccessToString;
import org.eclipse.xtext.formatting2.regionaccess.IEObjectRegion;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccess;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionExtensions;
import org.eclipse.xtext.formatting2.regionaccess.TextRegionAccessBuilder;
import org.eclipse.xtext.formatting2.regionaccess.internal.StringBasedTextRegionAccessDiff;
import org.eclipse.xtext.formatting2.regionaccess.internal.regionaccesstestlanguage.Delegate;
import org.eclipse.xtext.formatting2.regionaccess.internal.regionaccesstestlanguage.Delegation;
import org.eclipse.xtext.formatting2.regionaccess.internal.regionaccesstestlanguage.RegionaccesstestlanguagePackage;
import org.eclipse.xtext.formatting2.regionaccess.internal.regionaccesstestlanguage.Root;
import org.eclipse.xtext.formatting2.regionaccess.internal.tests.RegionAccessTestLanguageInjectorProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.serializer.impl.Serializer;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
@InjectWith(RegionAccessTestLanguageInjectorProvider.class)
@RunWith(XtextRunner.class)
@SuppressWarnings("all")
public class RegionAccessDiffTest {
  @Inject
  private ParseHelper<Root> parseHelper;
  
  @Inject
  private Provider<TextRegionAccessBuilder> textRegionAccessBuilder;
  
  @Inject
  private ValidationTestHelper validationTestHelper;
  
  @Inject
  private Serializer serializer;
  
  @Test
  public void testEmptyModification() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("1 foo");
    _builder.newLine();
    final ITextRegionAccess access = this.toTextRegionAccess(_builder);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("0 0 H");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("B Simple\'foo\' Root");
    _builder_1.newLine();
    _builder_1.append("0 1  S \"1\"        Simple:\'1\'");
    _builder_1.newLine();
    _builder_1.append("1 1  H \" \"        Whitespace:TerminalRule\'WS\'");
    _builder_1.newLine();
    _builder_1.append("2 3  S \"foo\"      Simple:name=ID");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("E Simple\'foo\' Root");
    _builder_1.newLine();
    _builder_1.append("5 0 H");
    _builder_1.newLine();
    this.operator_tripleEquals(access, _builder_1);
    final Procedure1<StringBasedTextRegionAccessDiff.Factory> _function = (StringBasedTextRegionAccessDiff.Factory it) -> {
    };
    ITextRegionAccess _modify = this.modify(access, _function);
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("0 0 H");
    _builder_2.newLine();
    _builder_2.append("    ");
    _builder_2.append("B Simple\'foo\' Root");
    _builder_2.newLine();
    _builder_2.append("0 1  S \"1\"        Simple:\'1\'");
    _builder_2.newLine();
    _builder_2.append("1 1  H \" \"        Whitespace:TerminalRule\'WS\'");
    _builder_2.newLine();
    _builder_2.append("2 3  S \"foo\"      Simple:name=ID");
    _builder_2.newLine();
    _builder_2.append("    ");
    _builder_2.append("E Simple\'foo\' Root");
    _builder_2.newLine();
    _builder_2.append("5 0 H");
    _builder_2.newLine();
    this.operator_tripleEquals(_modify, _builder_2);
  }
  
  @Test
  public void testSingleSemanticToken() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("1 foo");
    _builder.newLine();
    final ITextRegionAccess access = this.toTextRegionAccess(_builder);
    final Procedure1<StringBasedTextRegionAccessDiff.Factory> _function = (StringBasedTextRegionAccessDiff.Factory it) -> {
      @Extension
      final ITextRegionExtensions ext = access.getExtensions();
      final EObject root = access.regionForRootEObject().getSemanticElement();
      final ISemanticRegion foo = ext.allRegionsFor(root).feature(RegionaccesstestlanguagePackage.Literals.SIMPLE__NAME);
      it.replace(foo, "baaar");
    };
    ITextRegionAccess _modify = this.modify(access, _function);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("0 0   H");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("B Simple\'foo\' Root");
    _builder_1.newLine();
    _builder_1.append("0 1    S \"1\"        Simple:\'1\'");
    _builder_1.newLine();
    _builder_1.append("1 1    H \" \"        Whitespace:TerminalRule\'WS\'");
    _builder_1.newLine();
    _builder_1.append("2 5 1  S \"baaar\"    Simple:name=ID");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("E Simple\'foo\' Root");
    _builder_1.newLine();
    _builder_1.append("7 0   H");
    _builder_1.newLine();
    _builder_1.append("------------ diff 1 ------------");
    _builder_1.newLine();
    _builder_1.append("2 3 S \"foo\"      Simple:name=ID");
    _builder_1.newLine();
    this.operator_tripleEquals(_modify, _builder_1);
  }
  
  @Test
  public void testSerializeChildObject() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("2 foo");
    _builder.newLine();
    final ITextRegionAccess access = this.toTextRegionAccess(_builder);
    final Procedure1<StringBasedTextRegionAccessDiff.Factory> _function = (StringBasedTextRegionAccessDiff.Factory it) -> {
      EObject _semanticElement = access.regionForRootEObject().getSemanticElement();
      final Delegate child = ((Delegation) _semanticElement).getDelegate();
      final IEObjectRegion childRegion = access.regionForEObject(child);
      child.setName("baaaz");
      final ITextRegionAccess textRegions = this.serializer.serializeToRegions(child);
      it.replace(childRegion.getPreviousHiddenRegion(), childRegion.getNextHiddenRegion().getPreviousSemanticRegion(), textRegions);
    };
    ITextRegionAccess _modify = this.modify(access, _function);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("0 0   H");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("B Delegation Root");
    _builder_1.newLine();
    _builder_1.append("0 1    S \"2\"        Delegation:\'2\'");
    _builder_1.newLine();
    _builder_1.append("1 1 1  H \" \"        Whitespace:TerminalRule\'WS\'");
    _builder_1.newLine();
    _builder_1.append("       ");
    _builder_1.append("B Delegate\'baaaz\' Delegate path:Delegation/delegate");
    _builder_1.newLine();
    _builder_1.append("2 5 1   S \"baaaz\"    Delegate:name=ID");
    _builder_1.newLine();
    _builder_1.append("       ");
    _builder_1.append("E Delegate\'baaaz\' Delegate path:Delegation/delegate");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("E Delegation Root");
    _builder_1.newLine();
    _builder_1.append("7 0   H");
    _builder_1.newLine();
    _builder_1.append("------------ diff 1 ------------");
    _builder_1.newLine();
    _builder_1.append("1 1 H \" \"        Whitespace:TerminalRule\'WS\'");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("B Delegate\'baaaz\' Delegation:delegate=Delegate path:Delegation/delegate");
    _builder_1.newLine();
    _builder_1.append("2 3  S \"foo\"      Delegate:name=ID");
    _builder_1.newLine();
    this.operator_tripleEquals(_modify, _builder_1);
  }
  
  @Test
  public void testSerializeRootObject() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("3 foo");
    _builder.newLine();
    final ITextRegionAccess access = this.toTextRegionAccess(_builder);
    final Procedure1<StringBasedTextRegionAccessDiff.Factory> _function = (StringBasedTextRegionAccessDiff.Factory it) -> {
      final IEObjectRegion root = access.regionForRootEObject();
      EObject _semanticElement = root.getSemanticElement();
      final Delegate rootObj = ((Delegate) _semanticElement);
      rootObj.setName("baaaz");
      final ITextRegionAccess textRegions = this.serializer.serializeToRegions(rootObj);
      it.replace(root.getPreviousHiddenRegion(), root.getNextHiddenRegion().getPreviousSemanticRegion(), textRegions);
    };
    ITextRegionAccess _modify = this.modify(access, _function);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("0 0 1 H");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("B Delegate\'baaaz\' Root");
    _builder_1.newLine();
    _builder_1.append("0 1 1  S \"3\"        Unassigned:\'3\'");
    _builder_1.newLine();
    _builder_1.append("1 1 1  H \" \"        Whitespace:TerminalRule\'WS\'");
    _builder_1.newLine();
    _builder_1.append("2 5 1  S \"baaaz\"    Delegate:name=ID");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("E Delegate\'baaaz\' Root");
    _builder_1.newLine();
    _builder_1.append("7 0   H");
    _builder_1.newLine();
    _builder_1.append("------------ diff 1 ------------");
    _builder_1.newLine();
    _builder_1.append("0 0 H");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("B Delegate\'baaaz\' Root");
    _builder_1.newLine();
    _builder_1.append("0 1  S \"3\"        Unassigned:\'3\'");
    _builder_1.newLine();
    _builder_1.append("1 1  H \" \"        Whitespace:TerminalRule\'WS\'");
    _builder_1.newLine();
    _builder_1.append("2 3  S \"foo\"      Delegate:name=ID");
    _builder_1.newLine();
    this.operator_tripleEquals(_modify, _builder_1);
  }
  
  private ITextRegionAccess toTextRegionAccess(final CharSequence file) {
    try {
      final Root obj = this.parseHelper.parse(file.toString().trim());
      this.validationTestHelper.assertNoErrors(obj);
      Resource _eResource = obj.eResource();
      return this.textRegionAccessBuilder.get().forNodeModel(((XtextResource) _eResource)).create();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private ITextRegionAccess modify(final ITextRegionAccess base, final Procedure1<? super StringBasedTextRegionAccessDiff.Factory> modify) {
    final StringBasedTextRegionAccessDiff.Factory fac = new StringBasedTextRegionAccessDiff.Factory(base);
    modify.apply(fac);
    final StringBasedTextRegionAccessDiff modified = fac.create();
    return modified;
  }
  
  private void operator_tripleEquals(final ITextRegionAccess access, final CharSequence expectation) {
    TextRegionAccessToString _cfg = this.cfg(new TextRegionAccessToString().withRegionAccess(access));
    final String tra1 = (_cfg + "\n");
    final String expM = expectation.toString().replaceAll(System.lineSeparator(), "\n");
    final String tra1M = tra1.replaceAll(System.lineSeparator(), "\n");
    Assert.assertEquals(expM, tra1M);
  }
  
  private TextRegionAccessToString cfg(final TextRegionAccessToString toStr) {
    return toStr.hideColumnExplanation().withTextWidth(10);
  }
}
