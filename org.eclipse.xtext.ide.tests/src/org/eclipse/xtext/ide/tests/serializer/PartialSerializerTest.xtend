/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ide.tests.serializer

import com.google.inject.Inject
import javax.inject.Provider
import org.eclipse.xtext.ide.serializer.impl.ChangeSerializer
import org.eclipse.xtext.ide.tests.testlanguage.partialSerializationTestLanguage.Value
import org.eclipse.xtext.ide.tests.testlanguage.tests.PartialSerializationTestLanguageInjectorProvider
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.InMemoryURIHandler
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
@RunWith(XtextRunner)
@InjectWith(PartialSerializationTestLanguageInjectorProvider)
class PartialSerializerTest {

	// extension PartialSerializationTestLanguageFactory fac = PartialSerializationTestLanguageFactory.eINSTANCE
	@Inject Provider<ChangeSerializer> serializerProvider
	@Inject extension ChangeSerializerTestHelper

	@Test
	def void testSimple() {
		val fs = new InMemoryURIHandler()
		fs += "inmemory:/file1.pstl" -> '''#1 foo'''

		val rs = fs.createResourceSet
		val model = rs.contents("inmemory:/file1.pstl", Value)

		val serializer = serializerProvider.get()
		serializer.beginRecordChanges(model.eResource)
		model.name = "bar"
		serializer.endRecordChanges.textRegionAccess === '''
			0 0   H
			      B Value'bar'           Model
			0 2    S "#1"                 Value:'#1'
			2 1    H " "                  Whitespace:TerminalRule'WS'
			3 3 1  S "bar"                Value:name=ID
			      E Value'bar'           Model
			6 0   H
			------------ diff 1 ------------
			3 3 S "foo"                Value:name=ID
		'''
	}

}
