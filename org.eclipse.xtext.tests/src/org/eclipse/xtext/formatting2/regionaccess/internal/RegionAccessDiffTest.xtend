/*******************************************************************************
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.formatting2.regionaccess.internal

import com.google.inject.Inject
import javax.inject.Provider
import org.eclipse.xtext.formatting2.debug.TextRegionAccessToString
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccess
import org.eclipse.xtext.formatting2.regionaccess.TextRegionAccessBuilder
import org.eclipse.xtext.formatting2.regionaccess.internal.regionaccesstestlanguage.Delegate
import org.eclipse.xtext.formatting2.regionaccess.internal.regionaccesstestlanguage.Delegation
import org.eclipse.xtext.formatting2.regionaccess.internal.regionaccesstestlanguage.RegionaccesstestlanguagePackage
import org.eclipse.xtext.formatting2.regionaccess.internal.regionaccesstestlanguage.Root
import org.eclipse.xtext.formatting2.regionaccess.internal.tests.RegionAccessTestLanguageInjectorProvider
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.serializer.impl.Serializer
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
@InjectWith(RegionAccessTestLanguageInjectorProvider)
@RunWith(XtextRunner)
class RegionAccessDiffTest {
	@Inject ParseHelper<Root> parseHelper
	@Inject Provider<TextRegionAccessBuilder> textRegionAccessBuilder
	@Inject ValidationTestHelper validationTestHelper
	@Inject Serializer serializer

	@Test def void testEmptyModification() {
		val access = '''
			1 foo
		'''.toTextRegionAccess

		access === '''
			0 0 H
			    B Simple'foo' Root
			0 1  S "1"        Simple:'1'
			1 1  H " "        Whitespace:TerminalRule'WS'
			2 3  S "foo"      Simple:name=ID
			    E Simple'foo' Root
			5 0 H
		'''
		access.modify[] === '''
			0 0 H
			    B Simple'foo' Root
			0 1  S "1"        Simple:'1'
			1 1  H " "        Whitespace:TerminalRule'WS'
			2 3  S "foo"      Simple:name=ID
			    E Simple'foo' Root
			5 0 H
		'''
	}

	@Test def void testSingleSemanticToken() {
		val access = '''
			1 foo
		'''.toTextRegionAccess
		access.modify [
			val extension ext = access.extensions
			val root = access.regionForRootEObject.semanticElement
			val foo = root.allRegionsFor.feature(RegionaccesstestlanguagePackage.Literals.SIMPLE__NAME)
			replace(foo, "baaar")
		] === '''
			0 0   H
			      B Simple'foo' Root
			0 1    S "1"        Simple:'1'
			1 1    H " "        Whitespace:TerminalRule'WS'
			2 5 1  S "baaar"    Simple:name=ID
			      E Simple'foo' Root
			7 0   H
			------------ diff 1 ------------
			2 3 S "foo"      Simple:name=ID
		'''
	}

	@Test def void testSerializeChildObject() {
		val access = '''
			2 foo
		'''.toTextRegionAccess
		access.modify [
			val child = (access.regionForRootEObject.semanticElement as Delegation).delegate
			val childRegion = access.regionForEObject(child)
			child.name = "baaaz"
			val textRegions = serializer.serializeToRegions(child)
			replace(childRegion.previousHiddenRegion, childRegion.nextHiddenRegion.previousSemanticRegion, textRegions)
		] === '''
			0 0   H
			      B Delegation Root
			0 1    S "2"        Delegation:'2'
			1 1 1  H " "        Whitespace:TerminalRule'WS'
			       B Delegate'baaaz' Delegate path:Delegation/delegate
			2 5 1   S "baaaz"    Delegate:name=ID
			       E Delegate'baaaz' Delegate path:Delegation/delegate
			      E Delegation Root
			7 0   H
			------------ diff 1 ------------
			1 1 H " "        Whitespace:TerminalRule'WS'
			    B Delegate'baaaz' Delegation:delegate=Delegate path:Delegation/delegate
			2 3  S "foo"      Delegate:name=ID
		'''
	}

	@Test def void testSerializeRootObject() {
		val access = '''
			3 foo
		'''.toTextRegionAccess
		access.modify [
			val root = access.regionForRootEObject;
			val rootObj = root.semanticElement as Delegate
			rootObj.name = "baaaz"
			val textRegions = serializer.serializeToRegions(rootObj)
			replace(root.previousHiddenRegion, root.nextHiddenRegion.previousSemanticRegion, textRegions)
		] === '''
			0 0 1 H
			      B Delegate'baaaz' Root
			0 1 1  S "3"        Unassigned:'3'
			1 1 1  H " "        Whitespace:TerminalRule'WS'
			2 5 1  S "baaaz"    Delegate:name=ID
			      E Delegate'baaaz' Root
			7 0   H
			------------ diff 1 ------------
			0 0 H
			    B Delegate'baaaz' Root
			0 1  S "3"        Unassigned:'3'
			1 1  H " "        Whitespace:TerminalRule'WS'
			2 3  S "foo"      Delegate:name=ID
		'''
	}

	private def ITextRegionAccess toTextRegionAccess(CharSequence file) {
		val obj = parseHelper.parse(file.toString.trim)
		validationTestHelper.assertNoErrors(obj)
		return textRegionAccessBuilder.get.forNodeModel(obj.eResource as XtextResource).create
	}

	private def ITextRegionAccess modify(ITextRegionAccess base,
		(StringBasedTextRegionAccessDiff.Factory)=>void modify) {
		val fac = new StringBasedTextRegionAccessDiff.Factory(base)
		modify.apply(fac)
		val modified = fac.create()
		return modified
	}

	private def ===(ITextRegionAccess access, CharSequence expectation) {
		val tra1 = new TextRegionAccessToString().withRegionAccess(access).cfg() + "\n"
		val expM = expectation.toString.replaceAll(System.lineSeparator, "\n")
		val tra1M = tra1.replaceAll(System.lineSeparator, "\n")
		Assert.assertEquals(expM, tra1M)
	}

	private def TextRegionAccessToString cfg(TextRegionAccessToString toStr) {
		toStr.hideColumnExplanation().withTextWidth(10)
	}

}
