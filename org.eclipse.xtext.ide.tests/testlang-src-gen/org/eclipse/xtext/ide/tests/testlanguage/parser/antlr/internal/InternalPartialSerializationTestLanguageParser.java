package org.eclipse.xtext.ide.tests.testlanguage.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.ide.tests.testlanguage.services.PartialSerializationTestLanguageGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalPartialSerializationTestLanguageParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'#2'", "'#1'", "'{'", "'ref'", "'}'", "';'", "'.'"
    };
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_STRING=6;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int RULE_INT=5;
    public static final int T__11=11;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;

    // delegates
    // delegators


        public InternalPartialSerializationTestLanguageParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalPartialSerializationTestLanguageParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalPartialSerializationTestLanguageParser.tokenNames; }
    public String getGrammarFileName() { return "InternalPartialSerializationTestLanguage.g"; }



     	private PartialSerializationTestLanguageGrammarAccess grammarAccess;

        public InternalPartialSerializationTestLanguageParser(TokenStream input, PartialSerializationTestLanguageGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Model";
       	}

       	@Override
       	protected PartialSerializationTestLanguageGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModel"
    // InternalPartialSerializationTestLanguage.g:68:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalPartialSerializationTestLanguage.g:68:46: (iv_ruleModel= ruleModel EOF )
            // InternalPartialSerializationTestLanguage.g:69:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalPartialSerializationTestLanguage.g:75:1: ruleModel returns [EObject current=null] : (this_Value_0= ruleValue | (otherlv_1= '#2' this_Node_2= ruleNode ) ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_Value_0 = null;

        EObject this_Node_2 = null;



        	enterRule();

        try {
            // InternalPartialSerializationTestLanguage.g:81:2: ( (this_Value_0= ruleValue | (otherlv_1= '#2' this_Node_2= ruleNode ) ) )
            // InternalPartialSerializationTestLanguage.g:82:2: (this_Value_0= ruleValue | (otherlv_1= '#2' this_Node_2= ruleNode ) )
            {
            // InternalPartialSerializationTestLanguage.g:82:2: (this_Value_0= ruleValue | (otherlv_1= '#2' this_Node_2= ruleNode ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==12) ) {
                alt1=1;
            }
            else if ( (LA1_0==11) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalPartialSerializationTestLanguage.g:83:3: this_Value_0= ruleValue
                    {

                    			newCompositeNode(grammarAccess.getModelAccess().getValueParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Value_0=ruleValue();

                    state._fsp--;


                    			current = this_Value_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalPartialSerializationTestLanguage.g:92:3: (otherlv_1= '#2' this_Node_2= ruleNode )
                    {
                    // InternalPartialSerializationTestLanguage.g:92:3: (otherlv_1= '#2' this_Node_2= ruleNode )
                    // InternalPartialSerializationTestLanguage.g:93:4: otherlv_1= '#2' this_Node_2= ruleNode
                    {
                    otherlv_1=(Token)match(input,11,FOLLOW_3); 

                    				newLeafNode(otherlv_1, grammarAccess.getModelAccess().getNumberSignDigitTwoKeyword_1_0());
                    			

                    				newCompositeNode(grammarAccess.getModelAccess().getNodeParserRuleCall_1_1());
                    			
                    pushFollow(FOLLOW_2);
                    this_Node_2=ruleNode();

                    state._fsp--;


                    				current = this_Node_2;
                    				afterParserOrEnumRuleCall();
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleValue"
    // InternalPartialSerializationTestLanguage.g:110:1: entryRuleValue returns [EObject current=null] : iv_ruleValue= ruleValue EOF ;
    public final EObject entryRuleValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValue = null;


        try {
            // InternalPartialSerializationTestLanguage.g:110:46: (iv_ruleValue= ruleValue EOF )
            // InternalPartialSerializationTestLanguage.g:111:2: iv_ruleValue= ruleValue EOF
            {
             newCompositeNode(grammarAccess.getValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleValue=ruleValue();

            state._fsp--;

             current =iv_ruleValue; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValue"


    // $ANTLR start "ruleValue"
    // InternalPartialSerializationTestLanguage.g:117:1: ruleValue returns [EObject current=null] : (otherlv_0= '#1' ( (lv_name_1_0= RULE_ID ) ) ) ;
    public final EObject ruleValue() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;


        	enterRule();

        try {
            // InternalPartialSerializationTestLanguage.g:123:2: ( (otherlv_0= '#1' ( (lv_name_1_0= RULE_ID ) ) ) )
            // InternalPartialSerializationTestLanguage.g:124:2: (otherlv_0= '#1' ( (lv_name_1_0= RULE_ID ) ) )
            {
            // InternalPartialSerializationTestLanguage.g:124:2: (otherlv_0= '#1' ( (lv_name_1_0= RULE_ID ) ) )
            // InternalPartialSerializationTestLanguage.g:125:3: otherlv_0= '#1' ( (lv_name_1_0= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getValueAccess().getNumberSignDigitOneKeyword_0());
            		
            // InternalPartialSerializationTestLanguage.g:129:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalPartialSerializationTestLanguage.g:130:4: (lv_name_1_0= RULE_ID )
            {
            // InternalPartialSerializationTestLanguage.g:130:4: (lv_name_1_0= RULE_ID )
            // InternalPartialSerializationTestLanguage.g:131:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_name_1_0, grammarAccess.getValueAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getValueRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValue"


    // $ANTLR start "entryRuleNode"
    // InternalPartialSerializationTestLanguage.g:151:1: entryRuleNode returns [EObject current=null] : iv_ruleNode= ruleNode EOF ;
    public final EObject entryRuleNode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNode = null;


        try {
            // InternalPartialSerializationTestLanguage.g:151:45: (iv_ruleNode= ruleNode EOF )
            // InternalPartialSerializationTestLanguage.g:152:2: iv_ruleNode= ruleNode EOF
            {
             newCompositeNode(grammarAccess.getNodeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNode=ruleNode();

            state._fsp--;

             current =iv_ruleNode; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNode"


    // $ANTLR start "ruleNode"
    // InternalPartialSerializationTestLanguage.g:158:1: ruleNode returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (otherlv_1= '{' ( (lv_children_2_0= ruleNode ) )* (otherlv_3= 'ref' ( ( ruleQualifiedName ) ) )? otherlv_5= '}' ) | otherlv_6= ';' ) ) ;
    public final EObject ruleNode() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        EObject lv_children_2_0 = null;



        	enterRule();

        try {
            // InternalPartialSerializationTestLanguage.g:164:2: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (otherlv_1= '{' ( (lv_children_2_0= ruleNode ) )* (otherlv_3= 'ref' ( ( ruleQualifiedName ) ) )? otherlv_5= '}' ) | otherlv_6= ';' ) ) )
            // InternalPartialSerializationTestLanguage.g:165:2: ( ( (lv_name_0_0= RULE_ID ) ) ( (otherlv_1= '{' ( (lv_children_2_0= ruleNode ) )* (otherlv_3= 'ref' ( ( ruleQualifiedName ) ) )? otherlv_5= '}' ) | otherlv_6= ';' ) )
            {
            // InternalPartialSerializationTestLanguage.g:165:2: ( ( (lv_name_0_0= RULE_ID ) ) ( (otherlv_1= '{' ( (lv_children_2_0= ruleNode ) )* (otherlv_3= 'ref' ( ( ruleQualifiedName ) ) )? otherlv_5= '}' ) | otherlv_6= ';' ) )
            // InternalPartialSerializationTestLanguage.g:166:3: ( (lv_name_0_0= RULE_ID ) ) ( (otherlv_1= '{' ( (lv_children_2_0= ruleNode ) )* (otherlv_3= 'ref' ( ( ruleQualifiedName ) ) )? otherlv_5= '}' ) | otherlv_6= ';' )
            {
            // InternalPartialSerializationTestLanguage.g:166:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalPartialSerializationTestLanguage.g:167:4: (lv_name_0_0= RULE_ID )
            {
            // InternalPartialSerializationTestLanguage.g:167:4: (lv_name_0_0= RULE_ID )
            // InternalPartialSerializationTestLanguage.g:168:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_name_0_0, grammarAccess.getNodeAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getNodeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalPartialSerializationTestLanguage.g:184:3: ( (otherlv_1= '{' ( (lv_children_2_0= ruleNode ) )* (otherlv_3= 'ref' ( ( ruleQualifiedName ) ) )? otherlv_5= '}' ) | otherlv_6= ';' )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==13) ) {
                alt4=1;
            }
            else if ( (LA4_0==16) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalPartialSerializationTestLanguage.g:185:4: (otherlv_1= '{' ( (lv_children_2_0= ruleNode ) )* (otherlv_3= 'ref' ( ( ruleQualifiedName ) ) )? otherlv_5= '}' )
                    {
                    // InternalPartialSerializationTestLanguage.g:185:4: (otherlv_1= '{' ( (lv_children_2_0= ruleNode ) )* (otherlv_3= 'ref' ( ( ruleQualifiedName ) ) )? otherlv_5= '}' )
                    // InternalPartialSerializationTestLanguage.g:186:5: otherlv_1= '{' ( (lv_children_2_0= ruleNode ) )* (otherlv_3= 'ref' ( ( ruleQualifiedName ) ) )? otherlv_5= '}'
                    {
                    otherlv_1=(Token)match(input,13,FOLLOW_5); 

                    					newLeafNode(otherlv_1, grammarAccess.getNodeAccess().getLeftCurlyBracketKeyword_1_0_0());
                    				
                    // InternalPartialSerializationTestLanguage.g:190:5: ( (lv_children_2_0= ruleNode ) )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==RULE_ID) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // InternalPartialSerializationTestLanguage.g:191:6: (lv_children_2_0= ruleNode )
                    	    {
                    	    // InternalPartialSerializationTestLanguage.g:191:6: (lv_children_2_0= ruleNode )
                    	    // InternalPartialSerializationTestLanguage.g:192:7: lv_children_2_0= ruleNode
                    	    {

                    	    							newCompositeNode(grammarAccess.getNodeAccess().getChildrenNodeParserRuleCall_1_0_1_0());
                    	    						
                    	    pushFollow(FOLLOW_5);
                    	    lv_children_2_0=ruleNode();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getNodeRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"children",
                    	    								lv_children_2_0,
                    	    								"org.eclipse.xtext.ide.tests.testlanguage.PartialSerializationTestLanguage.Node");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);

                    // InternalPartialSerializationTestLanguage.g:209:5: (otherlv_3= 'ref' ( ( ruleQualifiedName ) ) )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==14) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // InternalPartialSerializationTestLanguage.g:210:6: otherlv_3= 'ref' ( ( ruleQualifiedName ) )
                            {
                            otherlv_3=(Token)match(input,14,FOLLOW_3); 

                            						newLeafNode(otherlv_3, grammarAccess.getNodeAccess().getRefKeyword_1_0_2_0());
                            					
                            // InternalPartialSerializationTestLanguage.g:214:6: ( ( ruleQualifiedName ) )
                            // InternalPartialSerializationTestLanguage.g:215:7: ( ruleQualifiedName )
                            {
                            // InternalPartialSerializationTestLanguage.g:215:7: ( ruleQualifiedName )
                            // InternalPartialSerializationTestLanguage.g:216:8: ruleQualifiedName
                            {

                            								if (current==null) {
                            									current = createModelElement(grammarAccess.getNodeRule());
                            								}
                            							

                            								newCompositeNode(grammarAccess.getNodeAccess().getRefNodeCrossReference_1_0_2_1_0());
                            							
                            pushFollow(FOLLOW_6);
                            ruleQualifiedName();

                            state._fsp--;


                            								afterParserOrEnumRuleCall();
                            							

                            }


                            }


                            }
                            break;

                    }

                    otherlv_5=(Token)match(input,15,FOLLOW_2); 

                    					newLeafNode(otherlv_5, grammarAccess.getNodeAccess().getRightCurlyBracketKeyword_1_0_3());
                    				

                    }


                    }
                    break;
                case 2 :
                    // InternalPartialSerializationTestLanguage.g:237:4: otherlv_6= ';'
                    {
                    otherlv_6=(Token)match(input,16,FOLLOW_2); 

                    				newLeafNode(otherlv_6, grammarAccess.getNodeAccess().getSemicolonKeyword_1_1());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNode"


    // $ANTLR start "entryRuleQualifiedName"
    // InternalPartialSerializationTestLanguage.g:246:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final String entryRuleQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedName = null;


        try {
            // InternalPartialSerializationTestLanguage.g:246:53: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // InternalPartialSerializationTestLanguage.g:247:2: iv_ruleQualifiedName= ruleQualifiedName EOF
            {
             newCompositeNode(grammarAccess.getQualifiedNameRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedName=ruleQualifiedName();

            state._fsp--;

             current =iv_ruleQualifiedName.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // InternalPartialSerializationTestLanguage.g:253:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;


        	enterRule();

        try {
            // InternalPartialSerializationTestLanguage.g:259:2: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // InternalPartialSerializationTestLanguage.g:260:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // InternalPartialSerializationTestLanguage.g:260:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // InternalPartialSerializationTestLanguage.g:261:3: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_7); 

            			current.merge(this_ID_0);
            		

            			newLeafNode(this_ID_0, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0());
            		
            // InternalPartialSerializationTestLanguage.g:268:3: (kw= '.' this_ID_2= RULE_ID )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==17) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalPartialSerializationTestLanguage.g:269:4: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,17,FOLLOW_3); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0());
            	    			
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_7); 

            	    				current.merge(this_ID_2);
            	    			

            	    				newLeafNode(this_ID_2, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedName"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000012000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x000000000000C010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000020002L});

}