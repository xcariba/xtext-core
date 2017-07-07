/*
 * Copyright (c) 2016 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.ide.tests.testlanguage.serializer;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.ide.tests.testlanguage.services.PartialSerializationTestLanguageGrammarAccess;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AlternativeAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public class PartialSerializationTestLanguageSyntacticSequencer extends AbstractSyntacticSequencer {

	protected PartialSerializationTestLanguageGrammarAccess grammarAccess;
	protected AbstractElementAlias match_Node_SemicolonKeyword_1_1_or___LeftCurlyBracketKeyword_1_0_0_RightCurlyBracketKeyword_1_0_3__;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (PartialSerializationTestLanguageGrammarAccess) access;
		match_Node_SemicolonKeyword_1_1_or___LeftCurlyBracketKeyword_1_0_0_RightCurlyBracketKeyword_1_0_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getNodeAccess().getLeftCurlyBracketKeyword_1_0_0()), new TokenAlias(false, false, grammarAccess.getNodeAccess().getRightCurlyBracketKeyword_1_0_3())), new TokenAlias(false, false, grammarAccess.getNodeAccess().getSemicolonKeyword_1_1()));
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		return "";
	}
	
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if (match_Node_SemicolonKeyword_1_1_or___LeftCurlyBracketKeyword_1_0_0_RightCurlyBracketKeyword_1_0_3__.equals(syntax))
				emit_Node_SemicolonKeyword_1_1_or___LeftCurlyBracketKeyword_1_0_0_RightCurlyBracketKeyword_1_0_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Ambiguous syntax:
	 *     ';' | ('{' '}')
	 *
	 * This ambiguous syntax occurs at:
	 *     name=ID (ambiguity) (rule end)
	 */
	protected void emit_Node_SemicolonKeyword_1_1_or___LeftCurlyBracketKeyword_1_0_0_RightCurlyBracketKeyword_1_0_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
