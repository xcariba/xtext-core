/*
 * Copyright (c) 2016 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
grammar InternalPartialSerializationTestLanguage;

options {
	superClass=AbstractInternalContentAssistParser;
}

@lexer::header {
package org.eclipse.xtext.ide.tests.testlanguage.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package org.eclipse.xtext.ide.tests.testlanguage.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import org.eclipse.xtext.ide.tests.testlanguage.services.PartialSerializationTestLanguageGrammarAccess;

}
@parser::members {
	private PartialSerializationTestLanguageGrammarAccess grammarAccess;

	public void setGrammarAccess(PartialSerializationTestLanguageGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}

	@Override
	protected Grammar getGrammar() {
		return grammarAccess.getGrammar();
	}

	@Override
	protected String getValueForTokenName(String tokenName) {
		return tokenName;
	}
}

// Entry rule entryRuleModel
entryRuleModel
:
{ before(grammarAccess.getModelRule()); }
	 ruleModel
{ after(grammarAccess.getModelRule()); } 
	 EOF 
;

// Rule Model
ruleModel 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getModelAccess().getAlternatives()); }
		(rule__Model__Alternatives)
		{ after(grammarAccess.getModelAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleValue
entryRuleValue
:
{ before(grammarAccess.getValueRule()); }
	 ruleValue
{ after(grammarAccess.getValueRule()); } 
	 EOF 
;

// Rule Value
ruleValue 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getValueAccess().getGroup()); }
		(rule__Value__Group__0)
		{ after(grammarAccess.getValueAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleNode
entryRuleNode
:
{ before(grammarAccess.getNodeRule()); }
	 ruleNode
{ after(grammarAccess.getNodeRule()); } 
	 EOF 
;

// Rule Node
ruleNode 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getNodeAccess().getGroup()); }
		(rule__Node__Group__0)
		{ after(grammarAccess.getNodeAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleQualifiedName
entryRuleQualifiedName
:
{ before(grammarAccess.getQualifiedNameRule()); }
	 ruleQualifiedName
{ after(grammarAccess.getQualifiedNameRule()); } 
	 EOF 
;

// Rule QualifiedName
ruleQualifiedName 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getQualifiedNameAccess().getGroup()); }
		(rule__QualifiedName__Group__0)
		{ after(grammarAccess.getQualifiedNameAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Model__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getModelAccess().getValueParserRuleCall_0()); }
		ruleValue
		{ after(grammarAccess.getModelAccess().getValueParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getModelAccess().getGroup_1()); }
		(rule__Model__Group_1__0)
		{ after(grammarAccess.getModelAccess().getGroup_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Node__Alternatives_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNodeAccess().getGroup_1_0()); }
		(rule__Node__Group_1_0__0)
		{ after(grammarAccess.getNodeAccess().getGroup_1_0()); }
	)
	|
	(
		{ before(grammarAccess.getNodeAccess().getSemicolonKeyword_1_1()); }
		';'
		{ after(grammarAccess.getNodeAccess().getSemicolonKeyword_1_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Model__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Model__Group_1__0__Impl
	rule__Model__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Model__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getModelAccess().getNumberSignDigitTwoKeyword_1_0()); }
	'#2'
	{ after(grammarAccess.getModelAccess().getNumberSignDigitTwoKeyword_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Model__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Model__Group_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Model__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getModelAccess().getNodeParserRuleCall_1_1()); }
	ruleNode
	{ after(grammarAccess.getModelAccess().getNodeParserRuleCall_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Value__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Value__Group__0__Impl
	rule__Value__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Value__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getValueAccess().getNumberSignDigitOneKeyword_0()); }
	'#1'
	{ after(grammarAccess.getValueAccess().getNumberSignDigitOneKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Value__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Value__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Value__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getValueAccess().getNameAssignment_1()); }
	(rule__Value__NameAssignment_1)
	{ after(grammarAccess.getValueAccess().getNameAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Node__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Node__Group__0__Impl
	rule__Node__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Node__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNodeAccess().getNameAssignment_0()); }
	(rule__Node__NameAssignment_0)
	{ after(grammarAccess.getNodeAccess().getNameAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Node__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Node__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Node__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNodeAccess().getAlternatives_1()); }
	(rule__Node__Alternatives_1)
	{ after(grammarAccess.getNodeAccess().getAlternatives_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Node__Group_1_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Node__Group_1_0__0__Impl
	rule__Node__Group_1_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Node__Group_1_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNodeAccess().getLeftCurlyBracketKeyword_1_0_0()); }
	'{'
	{ after(grammarAccess.getNodeAccess().getLeftCurlyBracketKeyword_1_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Node__Group_1_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Node__Group_1_0__1__Impl
	rule__Node__Group_1_0__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Node__Group_1_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNodeAccess().getChildrenAssignment_1_0_1()); }
	(rule__Node__ChildrenAssignment_1_0_1)*
	{ after(grammarAccess.getNodeAccess().getChildrenAssignment_1_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Node__Group_1_0__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Node__Group_1_0__2__Impl
	rule__Node__Group_1_0__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Node__Group_1_0__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNodeAccess().getGroup_1_0_2()); }
	(rule__Node__Group_1_0_2__0)?
	{ after(grammarAccess.getNodeAccess().getGroup_1_0_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Node__Group_1_0__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Node__Group_1_0__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Node__Group_1_0__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNodeAccess().getRightCurlyBracketKeyword_1_0_3()); }
	'}'
	{ after(grammarAccess.getNodeAccess().getRightCurlyBracketKeyword_1_0_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Node__Group_1_0_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Node__Group_1_0_2__0__Impl
	rule__Node__Group_1_0_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Node__Group_1_0_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNodeAccess().getRefKeyword_1_0_2_0()); }
	'ref'
	{ after(grammarAccess.getNodeAccess().getRefKeyword_1_0_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Node__Group_1_0_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Node__Group_1_0_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Node__Group_1_0_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNodeAccess().getRefAssignment_1_0_2_1()); }
	(rule__Node__RefAssignment_1_0_2_1)
	{ after(grammarAccess.getNodeAccess().getRefAssignment_1_0_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__QualifiedName__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__QualifiedName__Group__0__Impl
	rule__QualifiedName__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); }
	RULE_ID
	{ after(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__QualifiedName__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getQualifiedNameAccess().getGroup_1()); }
	(rule__QualifiedName__Group_1__0)*
	{ after(grammarAccess.getQualifiedNameAccess().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__QualifiedName__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__QualifiedName__Group_1__0__Impl
	rule__QualifiedName__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); }
	'.'
	{ after(grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__QualifiedName__Group_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); }
	RULE_ID
	{ after(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Value__NameAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getValueAccess().getNameIDTerminalRuleCall_1_0()); }
		RULE_ID
		{ after(grammarAccess.getValueAccess().getNameIDTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Node__NameAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNodeAccess().getNameIDTerminalRuleCall_0_0()); }
		RULE_ID
		{ after(grammarAccess.getNodeAccess().getNameIDTerminalRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Node__ChildrenAssignment_1_0_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNodeAccess().getChildrenNodeParserRuleCall_1_0_1_0()); }
		ruleNode
		{ after(grammarAccess.getNodeAccess().getChildrenNodeParserRuleCall_1_0_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Node__RefAssignment_1_0_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNodeAccess().getRefNodeCrossReference_1_0_2_1_0()); }
		(
			{ before(grammarAccess.getNodeAccess().getRefNodeQualifiedNameParserRuleCall_1_0_2_1_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getNodeAccess().getRefNodeQualifiedNameParserRuleCall_1_0_2_1_0_1()); }
		)
		{ after(grammarAccess.getNodeAccess().getRefNodeCrossReference_1_0_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;
