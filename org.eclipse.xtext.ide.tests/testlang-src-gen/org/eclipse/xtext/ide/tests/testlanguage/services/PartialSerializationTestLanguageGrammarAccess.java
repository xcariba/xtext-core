/*
 * Copyright (c) 2016 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.ide.tests.testlanguage.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.common.services.TerminalsGrammarAccess;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractGrammarElementFinder;
import org.eclipse.xtext.service.GrammarProvider;

@Singleton
public class PartialSerializationTestLanguageGrammarAccess extends AbstractGrammarElementFinder {
	
	public class ModelElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.xtext.ide.tests.testlanguage.PartialSerializationTestLanguage.Model");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cValueParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final Group cGroup_1 = (Group)cAlternatives.eContents().get(1);
		private final Keyword cNumberSignDigitTwoKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final RuleCall cNodeParserRuleCall_1_1 = (RuleCall)cGroup_1.eContents().get(1);
		
		//Model:
		//	Value | "#2" Node;
		@Override public ParserRule getRule() { return rule; }
		
		//Value | "#2" Node
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//Value
		public RuleCall getValueParserRuleCall_0() { return cValueParserRuleCall_0; }
		
		//"#2" Node
		public Group getGroup_1() { return cGroup_1; }
		
		//"#2"
		public Keyword getNumberSignDigitTwoKeyword_1_0() { return cNumberSignDigitTwoKeyword_1_0; }
		
		//Node
		public RuleCall getNodeParserRuleCall_1_1() { return cNodeParserRuleCall_1_1; }
	}
	public class ValueElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.xtext.ide.tests.testlanguage.PartialSerializationTestLanguage.Value");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cNumberSignDigitOneKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		
		//Value:
		//	"#1" name=ID;
		@Override public ParserRule getRule() { return rule; }
		
		//"#1" name=ID
		public Group getGroup() { return cGroup; }
		
		//"#1"
		public Keyword getNumberSignDigitOneKeyword_0() { return cNumberSignDigitOneKeyword_0; }
		
		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }
	}
	public class NodeElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.xtext.ide.tests.testlanguage.PartialSerializationTestLanguage.Node");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cNameAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cNameIDTerminalRuleCall_0_0 = (RuleCall)cNameAssignment_0.eContents().get(0);
		private final Alternatives cAlternatives_1 = (Alternatives)cGroup.eContents().get(1);
		private final Group cGroup_1_0 = (Group)cAlternatives_1.eContents().get(0);
		private final Keyword cLeftCurlyBracketKeyword_1_0_0 = (Keyword)cGroup_1_0.eContents().get(0);
		private final Assignment cChildrenAssignment_1_0_1 = (Assignment)cGroup_1_0.eContents().get(1);
		private final RuleCall cChildrenNodeParserRuleCall_1_0_1_0 = (RuleCall)cChildrenAssignment_1_0_1.eContents().get(0);
		private final Group cGroup_1_0_2 = (Group)cGroup_1_0.eContents().get(2);
		private final Keyword cRefKeyword_1_0_2_0 = (Keyword)cGroup_1_0_2.eContents().get(0);
		private final Assignment cRefAssignment_1_0_2_1 = (Assignment)cGroup_1_0_2.eContents().get(1);
		private final CrossReference cRefNodeCrossReference_1_0_2_1_0 = (CrossReference)cRefAssignment_1_0_2_1.eContents().get(0);
		private final RuleCall cRefNodeQualifiedNameParserRuleCall_1_0_2_1_0_1 = (RuleCall)cRefNodeCrossReference_1_0_2_1_0.eContents().get(1);
		private final Keyword cRightCurlyBracketKeyword_1_0_3 = (Keyword)cGroup_1_0.eContents().get(3);
		private final Keyword cSemicolonKeyword_1_1 = (Keyword)cAlternatives_1.eContents().get(1);
		
		//Node:
		//	name=ID ("{" children+=Node* ("ref" ref=[Node|QualifiedName])? "}" | ";");
		@Override public ParserRule getRule() { return rule; }
		
		//name=ID ("{" children+=Node* ("ref" ref=[Node|QualifiedName])? "}" | ";")
		public Group getGroup() { return cGroup; }
		
		//name=ID
		public Assignment getNameAssignment_0() { return cNameAssignment_0; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_0_0() { return cNameIDTerminalRuleCall_0_0; }
		
		//"{" children+=Node* ("ref" ref=[Node|QualifiedName])? "}" | ";"
		public Alternatives getAlternatives_1() { return cAlternatives_1; }
		
		//"{" children+=Node* ("ref" ref=[Node|QualifiedName])? "}"
		public Group getGroup_1_0() { return cGroup_1_0; }
		
		//"{"
		public Keyword getLeftCurlyBracketKeyword_1_0_0() { return cLeftCurlyBracketKeyword_1_0_0; }
		
		//children+=Node*
		public Assignment getChildrenAssignment_1_0_1() { return cChildrenAssignment_1_0_1; }
		
		//Node
		public RuleCall getChildrenNodeParserRuleCall_1_0_1_0() { return cChildrenNodeParserRuleCall_1_0_1_0; }
		
		//("ref" ref=[Node|QualifiedName])?
		public Group getGroup_1_0_2() { return cGroup_1_0_2; }
		
		//"ref"
		public Keyword getRefKeyword_1_0_2_0() { return cRefKeyword_1_0_2_0; }
		
		//ref=[Node|QualifiedName]
		public Assignment getRefAssignment_1_0_2_1() { return cRefAssignment_1_0_2_1; }
		
		//[Node|QualifiedName]
		public CrossReference getRefNodeCrossReference_1_0_2_1_0() { return cRefNodeCrossReference_1_0_2_1_0; }
		
		//QualifiedName
		public RuleCall getRefNodeQualifiedNameParserRuleCall_1_0_2_1_0_1() { return cRefNodeQualifiedNameParserRuleCall_1_0_2_1_0_1; }
		
		//"}"
		public Keyword getRightCurlyBracketKeyword_1_0_3() { return cRightCurlyBracketKeyword_1_0_3; }
		
		//";"
		public Keyword getSemicolonKeyword_1_1() { return cSemicolonKeyword_1_1; }
	}
	public class QualifiedNameElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.xtext.ide.tests.testlanguage.PartialSerializationTestLanguage.QualifiedName");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cIDTerminalRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cFullStopKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final RuleCall cIDTerminalRuleCall_1_1 = (RuleCall)cGroup_1.eContents().get(1);
		
		//QualifiedName:
		//	ID ("." ID)*;
		@Override public ParserRule getRule() { return rule; }
		
		//ID ("." ID)*
		public Group getGroup() { return cGroup; }
		
		//ID
		public RuleCall getIDTerminalRuleCall_0() { return cIDTerminalRuleCall_0; }
		
		//("." ID)*
		public Group getGroup_1() { return cGroup_1; }
		
		//"."
		public Keyword getFullStopKeyword_1_0() { return cFullStopKeyword_1_0; }
		
		//ID
		public RuleCall getIDTerminalRuleCall_1_1() { return cIDTerminalRuleCall_1_1; }
	}
	
	
	private final ModelElements pModel;
	private final ValueElements pValue;
	private final NodeElements pNode;
	private final QualifiedNameElements pQualifiedName;
	
	private final Grammar grammar;
	
	private final TerminalsGrammarAccess gaTerminals;

	@Inject
	public PartialSerializationTestLanguageGrammarAccess(GrammarProvider grammarProvider,
			TerminalsGrammarAccess gaTerminals) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaTerminals = gaTerminals;
		this.pModel = new ModelElements();
		this.pValue = new ValueElements();
		this.pNode = new NodeElements();
		this.pQualifiedName = new QualifiedNameElements();
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("org.eclipse.xtext.ide.tests.testlanguage.PartialSerializationTestLanguage".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	@Override
	public Grammar getGrammar() {
		return grammar;
	}
	
	
	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//Model:
	//	Value | "#2" Node;
	public ModelElements getModelAccess() {
		return pModel;
	}
	
	public ParserRule getModelRule() {
		return getModelAccess().getRule();
	}
	
	//Value:
	//	"#1" name=ID;
	public ValueElements getValueAccess() {
		return pValue;
	}
	
	public ParserRule getValueRule() {
		return getValueAccess().getRule();
	}
	
	//Node:
	//	name=ID ("{" children+=Node* ("ref" ref=[Node|QualifiedName])? "}" | ";");
	public NodeElements getNodeAccess() {
		return pNode;
	}
	
	public ParserRule getNodeRule() {
		return getNodeAccess().getRule();
	}
	
	//QualifiedName:
	//	ID ("." ID)*;
	public QualifiedNameElements getQualifiedNameAccess() {
		return pQualifiedName;
	}
	
	public ParserRule getQualifiedNameRule() {
		return getQualifiedNameAccess().getRule();
	}
	
	//terminal ID:
	//	'^'? ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	}
	
	//terminal INT returns ecore::EInt:
	//	'0'..'9'+;
	public TerminalRule getINTRule() {
		return gaTerminals.getINTRule();
	}
	
	//terminal STRING:
	//	'"' ('\\' . | !('\\' | '"'))* '"' |
	//	"'" ('\\' . | !('\\' | "'"))* "'";
	public TerminalRule getSTRINGRule() {
		return gaTerminals.getSTRINGRule();
	}
	
	//terminal ML_COMMENT:
	//	'/*'->'*/';
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	}
	
	//terminal SL_COMMENT:
	//	'//' !('\n' | '\r')* ('\r'? '\n')?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	}
	
	//terminal WS:
	//	' ' | '\t' | '\r' | '\n'+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	}
	
	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	}
}
