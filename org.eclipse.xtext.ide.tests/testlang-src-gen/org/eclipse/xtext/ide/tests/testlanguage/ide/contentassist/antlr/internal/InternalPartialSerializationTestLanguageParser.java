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



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalPartialSerializationTestLanguageParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "';'", "'#2'", "'#1'", "'{'", "'}'", "'ref'", "'.'"
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



    // $ANTLR start "entryRuleModel"
    // InternalPartialSerializationTestLanguage.g:57:1: entryRuleModel : ruleModel EOF ;
    public final void entryRuleModel() throws RecognitionException {
        try {
            // InternalPartialSerializationTestLanguage.g:58:1: ( ruleModel EOF )
            // InternalPartialSerializationTestLanguage.g:59:1: ruleModel EOF
            {
             before(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            ruleModel();

            state._fsp--;

             after(grammarAccess.getModelRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalPartialSerializationTestLanguage.g:66:1: ruleModel : ( ( rule__Model__Alternatives ) ) ;
    public final void ruleModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:70:2: ( ( ( rule__Model__Alternatives ) ) )
            // InternalPartialSerializationTestLanguage.g:71:2: ( ( rule__Model__Alternatives ) )
            {
            // InternalPartialSerializationTestLanguage.g:71:2: ( ( rule__Model__Alternatives ) )
            // InternalPartialSerializationTestLanguage.g:72:3: ( rule__Model__Alternatives )
            {
             before(grammarAccess.getModelAccess().getAlternatives()); 
            // InternalPartialSerializationTestLanguage.g:73:3: ( rule__Model__Alternatives )
            // InternalPartialSerializationTestLanguage.g:73:4: rule__Model__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Model__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleValue"
    // InternalPartialSerializationTestLanguage.g:82:1: entryRuleValue : ruleValue EOF ;
    public final void entryRuleValue() throws RecognitionException {
        try {
            // InternalPartialSerializationTestLanguage.g:83:1: ( ruleValue EOF )
            // InternalPartialSerializationTestLanguage.g:84:1: ruleValue EOF
            {
             before(grammarAccess.getValueRule()); 
            pushFollow(FOLLOW_1);
            ruleValue();

            state._fsp--;

             after(grammarAccess.getValueRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleValue"


    // $ANTLR start "ruleValue"
    // InternalPartialSerializationTestLanguage.g:91:1: ruleValue : ( ( rule__Value__Group__0 ) ) ;
    public final void ruleValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:95:2: ( ( ( rule__Value__Group__0 ) ) )
            // InternalPartialSerializationTestLanguage.g:96:2: ( ( rule__Value__Group__0 ) )
            {
            // InternalPartialSerializationTestLanguage.g:96:2: ( ( rule__Value__Group__0 ) )
            // InternalPartialSerializationTestLanguage.g:97:3: ( rule__Value__Group__0 )
            {
             before(grammarAccess.getValueAccess().getGroup()); 
            // InternalPartialSerializationTestLanguage.g:98:3: ( rule__Value__Group__0 )
            // InternalPartialSerializationTestLanguage.g:98:4: rule__Value__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Value__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getValueAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleValue"


    // $ANTLR start "entryRuleNode"
    // InternalPartialSerializationTestLanguage.g:107:1: entryRuleNode : ruleNode EOF ;
    public final void entryRuleNode() throws RecognitionException {
        try {
            // InternalPartialSerializationTestLanguage.g:108:1: ( ruleNode EOF )
            // InternalPartialSerializationTestLanguage.g:109:1: ruleNode EOF
            {
             before(grammarAccess.getNodeRule()); 
            pushFollow(FOLLOW_1);
            ruleNode();

            state._fsp--;

             after(grammarAccess.getNodeRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleNode"


    // $ANTLR start "ruleNode"
    // InternalPartialSerializationTestLanguage.g:116:1: ruleNode : ( ( rule__Node__Group__0 ) ) ;
    public final void ruleNode() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:120:2: ( ( ( rule__Node__Group__0 ) ) )
            // InternalPartialSerializationTestLanguage.g:121:2: ( ( rule__Node__Group__0 ) )
            {
            // InternalPartialSerializationTestLanguage.g:121:2: ( ( rule__Node__Group__0 ) )
            // InternalPartialSerializationTestLanguage.g:122:3: ( rule__Node__Group__0 )
            {
             before(grammarAccess.getNodeAccess().getGroup()); 
            // InternalPartialSerializationTestLanguage.g:123:3: ( rule__Node__Group__0 )
            // InternalPartialSerializationTestLanguage.g:123:4: rule__Node__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Node__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getNodeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNode"


    // $ANTLR start "entryRuleQualifiedName"
    // InternalPartialSerializationTestLanguage.g:132:1: entryRuleQualifiedName : ruleQualifiedName EOF ;
    public final void entryRuleQualifiedName() throws RecognitionException {
        try {
            // InternalPartialSerializationTestLanguage.g:133:1: ( ruleQualifiedName EOF )
            // InternalPartialSerializationTestLanguage.g:134:1: ruleQualifiedName EOF
            {
             before(grammarAccess.getQualifiedNameRule()); 
            pushFollow(FOLLOW_1);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getQualifiedNameRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // InternalPartialSerializationTestLanguage.g:141:1: ruleQualifiedName : ( ( rule__QualifiedName__Group__0 ) ) ;
    public final void ruleQualifiedName() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:145:2: ( ( ( rule__QualifiedName__Group__0 ) ) )
            // InternalPartialSerializationTestLanguage.g:146:2: ( ( rule__QualifiedName__Group__0 ) )
            {
            // InternalPartialSerializationTestLanguage.g:146:2: ( ( rule__QualifiedName__Group__0 ) )
            // InternalPartialSerializationTestLanguage.g:147:3: ( rule__QualifiedName__Group__0 )
            {
             before(grammarAccess.getQualifiedNameAccess().getGroup()); 
            // InternalPartialSerializationTestLanguage.g:148:3: ( rule__QualifiedName__Group__0 )
            // InternalPartialSerializationTestLanguage.g:148:4: rule__QualifiedName__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getQualifiedNameAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "rule__Model__Alternatives"
    // InternalPartialSerializationTestLanguage.g:156:1: rule__Model__Alternatives : ( ( ruleValue ) | ( ( rule__Model__Group_1__0 ) ) );
    public final void rule__Model__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:160:1: ( ( ruleValue ) | ( ( rule__Model__Group_1__0 ) ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==13) ) {
                alt1=1;
            }
            else if ( (LA1_0==12) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalPartialSerializationTestLanguage.g:161:2: ( ruleValue )
                    {
                    // InternalPartialSerializationTestLanguage.g:161:2: ( ruleValue )
                    // InternalPartialSerializationTestLanguage.g:162:3: ruleValue
                    {
                     before(grammarAccess.getModelAccess().getValueParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleValue();

                    state._fsp--;

                     after(grammarAccess.getModelAccess().getValueParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalPartialSerializationTestLanguage.g:167:2: ( ( rule__Model__Group_1__0 ) )
                    {
                    // InternalPartialSerializationTestLanguage.g:167:2: ( ( rule__Model__Group_1__0 ) )
                    // InternalPartialSerializationTestLanguage.g:168:3: ( rule__Model__Group_1__0 )
                    {
                     before(grammarAccess.getModelAccess().getGroup_1()); 
                    // InternalPartialSerializationTestLanguage.g:169:3: ( rule__Model__Group_1__0 )
                    // InternalPartialSerializationTestLanguage.g:169:4: rule__Model__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Model__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getModelAccess().getGroup_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Alternatives"


    // $ANTLR start "rule__Node__Alternatives_1"
    // InternalPartialSerializationTestLanguage.g:177:1: rule__Node__Alternatives_1 : ( ( ( rule__Node__Group_1_0__0 ) ) | ( ';' ) );
    public final void rule__Node__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:181:1: ( ( ( rule__Node__Group_1_0__0 ) ) | ( ';' ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==14) ) {
                alt2=1;
            }
            else if ( (LA2_0==11) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalPartialSerializationTestLanguage.g:182:2: ( ( rule__Node__Group_1_0__0 ) )
                    {
                    // InternalPartialSerializationTestLanguage.g:182:2: ( ( rule__Node__Group_1_0__0 ) )
                    // InternalPartialSerializationTestLanguage.g:183:3: ( rule__Node__Group_1_0__0 )
                    {
                     before(grammarAccess.getNodeAccess().getGroup_1_0()); 
                    // InternalPartialSerializationTestLanguage.g:184:3: ( rule__Node__Group_1_0__0 )
                    // InternalPartialSerializationTestLanguage.g:184:4: rule__Node__Group_1_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Node__Group_1_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getNodeAccess().getGroup_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalPartialSerializationTestLanguage.g:188:2: ( ';' )
                    {
                    // InternalPartialSerializationTestLanguage.g:188:2: ( ';' )
                    // InternalPartialSerializationTestLanguage.g:189:3: ';'
                    {
                     before(grammarAccess.getNodeAccess().getSemicolonKeyword_1_1()); 
                    match(input,11,FOLLOW_2); 
                     after(grammarAccess.getNodeAccess().getSemicolonKeyword_1_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__Alternatives_1"


    // $ANTLR start "rule__Model__Group_1__0"
    // InternalPartialSerializationTestLanguage.g:198:1: rule__Model__Group_1__0 : rule__Model__Group_1__0__Impl rule__Model__Group_1__1 ;
    public final void rule__Model__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:202:1: ( rule__Model__Group_1__0__Impl rule__Model__Group_1__1 )
            // InternalPartialSerializationTestLanguage.g:203:2: rule__Model__Group_1__0__Impl rule__Model__Group_1__1
            {
            pushFollow(FOLLOW_3);
            rule__Model__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Model__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_1__0"


    // $ANTLR start "rule__Model__Group_1__0__Impl"
    // InternalPartialSerializationTestLanguage.g:210:1: rule__Model__Group_1__0__Impl : ( '#2' ) ;
    public final void rule__Model__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:214:1: ( ( '#2' ) )
            // InternalPartialSerializationTestLanguage.g:215:1: ( '#2' )
            {
            // InternalPartialSerializationTestLanguage.g:215:1: ( '#2' )
            // InternalPartialSerializationTestLanguage.g:216:2: '#2'
            {
             before(grammarAccess.getModelAccess().getNumberSignDigitTwoKeyword_1_0()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getModelAccess().getNumberSignDigitTwoKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_1__0__Impl"


    // $ANTLR start "rule__Model__Group_1__1"
    // InternalPartialSerializationTestLanguage.g:225:1: rule__Model__Group_1__1 : rule__Model__Group_1__1__Impl ;
    public final void rule__Model__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:229:1: ( rule__Model__Group_1__1__Impl )
            // InternalPartialSerializationTestLanguage.g:230:2: rule__Model__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Model__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_1__1"


    // $ANTLR start "rule__Model__Group_1__1__Impl"
    // InternalPartialSerializationTestLanguage.g:236:1: rule__Model__Group_1__1__Impl : ( ruleNode ) ;
    public final void rule__Model__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:240:1: ( ( ruleNode ) )
            // InternalPartialSerializationTestLanguage.g:241:1: ( ruleNode )
            {
            // InternalPartialSerializationTestLanguage.g:241:1: ( ruleNode )
            // InternalPartialSerializationTestLanguage.g:242:2: ruleNode
            {
             before(grammarAccess.getModelAccess().getNodeParserRuleCall_1_1()); 
            pushFollow(FOLLOW_2);
            ruleNode();

            state._fsp--;

             after(grammarAccess.getModelAccess().getNodeParserRuleCall_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_1__1__Impl"


    // $ANTLR start "rule__Value__Group__0"
    // InternalPartialSerializationTestLanguage.g:252:1: rule__Value__Group__0 : rule__Value__Group__0__Impl rule__Value__Group__1 ;
    public final void rule__Value__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:256:1: ( rule__Value__Group__0__Impl rule__Value__Group__1 )
            // InternalPartialSerializationTestLanguage.g:257:2: rule__Value__Group__0__Impl rule__Value__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__Value__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Value__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group__0"


    // $ANTLR start "rule__Value__Group__0__Impl"
    // InternalPartialSerializationTestLanguage.g:264:1: rule__Value__Group__0__Impl : ( '#1' ) ;
    public final void rule__Value__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:268:1: ( ( '#1' ) )
            // InternalPartialSerializationTestLanguage.g:269:1: ( '#1' )
            {
            // InternalPartialSerializationTestLanguage.g:269:1: ( '#1' )
            // InternalPartialSerializationTestLanguage.g:270:2: '#1'
            {
             before(grammarAccess.getValueAccess().getNumberSignDigitOneKeyword_0()); 
            match(input,13,FOLLOW_2); 
             after(grammarAccess.getValueAccess().getNumberSignDigitOneKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group__0__Impl"


    // $ANTLR start "rule__Value__Group__1"
    // InternalPartialSerializationTestLanguage.g:279:1: rule__Value__Group__1 : rule__Value__Group__1__Impl ;
    public final void rule__Value__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:283:1: ( rule__Value__Group__1__Impl )
            // InternalPartialSerializationTestLanguage.g:284:2: rule__Value__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Value__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group__1"


    // $ANTLR start "rule__Value__Group__1__Impl"
    // InternalPartialSerializationTestLanguage.g:290:1: rule__Value__Group__1__Impl : ( ( rule__Value__NameAssignment_1 ) ) ;
    public final void rule__Value__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:294:1: ( ( ( rule__Value__NameAssignment_1 ) ) )
            // InternalPartialSerializationTestLanguage.g:295:1: ( ( rule__Value__NameAssignment_1 ) )
            {
            // InternalPartialSerializationTestLanguage.g:295:1: ( ( rule__Value__NameAssignment_1 ) )
            // InternalPartialSerializationTestLanguage.g:296:2: ( rule__Value__NameAssignment_1 )
            {
             before(grammarAccess.getValueAccess().getNameAssignment_1()); 
            // InternalPartialSerializationTestLanguage.g:297:2: ( rule__Value__NameAssignment_1 )
            // InternalPartialSerializationTestLanguage.g:297:3: rule__Value__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Value__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getValueAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group__1__Impl"


    // $ANTLR start "rule__Node__Group__0"
    // InternalPartialSerializationTestLanguage.g:306:1: rule__Node__Group__0 : rule__Node__Group__0__Impl rule__Node__Group__1 ;
    public final void rule__Node__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:310:1: ( rule__Node__Group__0__Impl rule__Node__Group__1 )
            // InternalPartialSerializationTestLanguage.g:311:2: rule__Node__Group__0__Impl rule__Node__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__Node__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Node__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__Group__0"


    // $ANTLR start "rule__Node__Group__0__Impl"
    // InternalPartialSerializationTestLanguage.g:318:1: rule__Node__Group__0__Impl : ( ( rule__Node__NameAssignment_0 ) ) ;
    public final void rule__Node__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:322:1: ( ( ( rule__Node__NameAssignment_0 ) ) )
            // InternalPartialSerializationTestLanguage.g:323:1: ( ( rule__Node__NameAssignment_0 ) )
            {
            // InternalPartialSerializationTestLanguage.g:323:1: ( ( rule__Node__NameAssignment_0 ) )
            // InternalPartialSerializationTestLanguage.g:324:2: ( rule__Node__NameAssignment_0 )
            {
             before(grammarAccess.getNodeAccess().getNameAssignment_0()); 
            // InternalPartialSerializationTestLanguage.g:325:2: ( rule__Node__NameAssignment_0 )
            // InternalPartialSerializationTestLanguage.g:325:3: rule__Node__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Node__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getNodeAccess().getNameAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__Group__0__Impl"


    // $ANTLR start "rule__Node__Group__1"
    // InternalPartialSerializationTestLanguage.g:333:1: rule__Node__Group__1 : rule__Node__Group__1__Impl ;
    public final void rule__Node__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:337:1: ( rule__Node__Group__1__Impl )
            // InternalPartialSerializationTestLanguage.g:338:2: rule__Node__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Node__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__Group__1"


    // $ANTLR start "rule__Node__Group__1__Impl"
    // InternalPartialSerializationTestLanguage.g:344:1: rule__Node__Group__1__Impl : ( ( rule__Node__Alternatives_1 ) ) ;
    public final void rule__Node__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:348:1: ( ( ( rule__Node__Alternatives_1 ) ) )
            // InternalPartialSerializationTestLanguage.g:349:1: ( ( rule__Node__Alternatives_1 ) )
            {
            // InternalPartialSerializationTestLanguage.g:349:1: ( ( rule__Node__Alternatives_1 ) )
            // InternalPartialSerializationTestLanguage.g:350:2: ( rule__Node__Alternatives_1 )
            {
             before(grammarAccess.getNodeAccess().getAlternatives_1()); 
            // InternalPartialSerializationTestLanguage.g:351:2: ( rule__Node__Alternatives_1 )
            // InternalPartialSerializationTestLanguage.g:351:3: rule__Node__Alternatives_1
            {
            pushFollow(FOLLOW_2);
            rule__Node__Alternatives_1();

            state._fsp--;


            }

             after(grammarAccess.getNodeAccess().getAlternatives_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__Group__1__Impl"


    // $ANTLR start "rule__Node__Group_1_0__0"
    // InternalPartialSerializationTestLanguage.g:360:1: rule__Node__Group_1_0__0 : rule__Node__Group_1_0__0__Impl rule__Node__Group_1_0__1 ;
    public final void rule__Node__Group_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:364:1: ( rule__Node__Group_1_0__0__Impl rule__Node__Group_1_0__1 )
            // InternalPartialSerializationTestLanguage.g:365:2: rule__Node__Group_1_0__0__Impl rule__Node__Group_1_0__1
            {
            pushFollow(FOLLOW_5);
            rule__Node__Group_1_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Node__Group_1_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__Group_1_0__0"


    // $ANTLR start "rule__Node__Group_1_0__0__Impl"
    // InternalPartialSerializationTestLanguage.g:372:1: rule__Node__Group_1_0__0__Impl : ( '{' ) ;
    public final void rule__Node__Group_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:376:1: ( ( '{' ) )
            // InternalPartialSerializationTestLanguage.g:377:1: ( '{' )
            {
            // InternalPartialSerializationTestLanguage.g:377:1: ( '{' )
            // InternalPartialSerializationTestLanguage.g:378:2: '{'
            {
             before(grammarAccess.getNodeAccess().getLeftCurlyBracketKeyword_1_0_0()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getNodeAccess().getLeftCurlyBracketKeyword_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__Group_1_0__0__Impl"


    // $ANTLR start "rule__Node__Group_1_0__1"
    // InternalPartialSerializationTestLanguage.g:387:1: rule__Node__Group_1_0__1 : rule__Node__Group_1_0__1__Impl rule__Node__Group_1_0__2 ;
    public final void rule__Node__Group_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:391:1: ( rule__Node__Group_1_0__1__Impl rule__Node__Group_1_0__2 )
            // InternalPartialSerializationTestLanguage.g:392:2: rule__Node__Group_1_0__1__Impl rule__Node__Group_1_0__2
            {
            pushFollow(FOLLOW_5);
            rule__Node__Group_1_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Node__Group_1_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__Group_1_0__1"


    // $ANTLR start "rule__Node__Group_1_0__1__Impl"
    // InternalPartialSerializationTestLanguage.g:399:1: rule__Node__Group_1_0__1__Impl : ( ( rule__Node__ChildrenAssignment_1_0_1 )* ) ;
    public final void rule__Node__Group_1_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:403:1: ( ( ( rule__Node__ChildrenAssignment_1_0_1 )* ) )
            // InternalPartialSerializationTestLanguage.g:404:1: ( ( rule__Node__ChildrenAssignment_1_0_1 )* )
            {
            // InternalPartialSerializationTestLanguage.g:404:1: ( ( rule__Node__ChildrenAssignment_1_0_1 )* )
            // InternalPartialSerializationTestLanguage.g:405:2: ( rule__Node__ChildrenAssignment_1_0_1 )*
            {
             before(grammarAccess.getNodeAccess().getChildrenAssignment_1_0_1()); 
            // InternalPartialSerializationTestLanguage.g:406:2: ( rule__Node__ChildrenAssignment_1_0_1 )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==RULE_ID) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalPartialSerializationTestLanguage.g:406:3: rule__Node__ChildrenAssignment_1_0_1
            	    {
            	    pushFollow(FOLLOW_6);
            	    rule__Node__ChildrenAssignment_1_0_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

             after(grammarAccess.getNodeAccess().getChildrenAssignment_1_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__Group_1_0__1__Impl"


    // $ANTLR start "rule__Node__Group_1_0__2"
    // InternalPartialSerializationTestLanguage.g:414:1: rule__Node__Group_1_0__2 : rule__Node__Group_1_0__2__Impl rule__Node__Group_1_0__3 ;
    public final void rule__Node__Group_1_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:418:1: ( rule__Node__Group_1_0__2__Impl rule__Node__Group_1_0__3 )
            // InternalPartialSerializationTestLanguage.g:419:2: rule__Node__Group_1_0__2__Impl rule__Node__Group_1_0__3
            {
            pushFollow(FOLLOW_5);
            rule__Node__Group_1_0__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Node__Group_1_0__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__Group_1_0__2"


    // $ANTLR start "rule__Node__Group_1_0__2__Impl"
    // InternalPartialSerializationTestLanguage.g:426:1: rule__Node__Group_1_0__2__Impl : ( ( rule__Node__Group_1_0_2__0 )? ) ;
    public final void rule__Node__Group_1_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:430:1: ( ( ( rule__Node__Group_1_0_2__0 )? ) )
            // InternalPartialSerializationTestLanguage.g:431:1: ( ( rule__Node__Group_1_0_2__0 )? )
            {
            // InternalPartialSerializationTestLanguage.g:431:1: ( ( rule__Node__Group_1_0_2__0 )? )
            // InternalPartialSerializationTestLanguage.g:432:2: ( rule__Node__Group_1_0_2__0 )?
            {
             before(grammarAccess.getNodeAccess().getGroup_1_0_2()); 
            // InternalPartialSerializationTestLanguage.g:433:2: ( rule__Node__Group_1_0_2__0 )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==16) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalPartialSerializationTestLanguage.g:433:3: rule__Node__Group_1_0_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Node__Group_1_0_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getNodeAccess().getGroup_1_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__Group_1_0__2__Impl"


    // $ANTLR start "rule__Node__Group_1_0__3"
    // InternalPartialSerializationTestLanguage.g:441:1: rule__Node__Group_1_0__3 : rule__Node__Group_1_0__3__Impl ;
    public final void rule__Node__Group_1_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:445:1: ( rule__Node__Group_1_0__3__Impl )
            // InternalPartialSerializationTestLanguage.g:446:2: rule__Node__Group_1_0__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Node__Group_1_0__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__Group_1_0__3"


    // $ANTLR start "rule__Node__Group_1_0__3__Impl"
    // InternalPartialSerializationTestLanguage.g:452:1: rule__Node__Group_1_0__3__Impl : ( '}' ) ;
    public final void rule__Node__Group_1_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:456:1: ( ( '}' ) )
            // InternalPartialSerializationTestLanguage.g:457:1: ( '}' )
            {
            // InternalPartialSerializationTestLanguage.g:457:1: ( '}' )
            // InternalPartialSerializationTestLanguage.g:458:2: '}'
            {
             before(grammarAccess.getNodeAccess().getRightCurlyBracketKeyword_1_0_3()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getNodeAccess().getRightCurlyBracketKeyword_1_0_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__Group_1_0__3__Impl"


    // $ANTLR start "rule__Node__Group_1_0_2__0"
    // InternalPartialSerializationTestLanguage.g:468:1: rule__Node__Group_1_0_2__0 : rule__Node__Group_1_0_2__0__Impl rule__Node__Group_1_0_2__1 ;
    public final void rule__Node__Group_1_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:472:1: ( rule__Node__Group_1_0_2__0__Impl rule__Node__Group_1_0_2__1 )
            // InternalPartialSerializationTestLanguage.g:473:2: rule__Node__Group_1_0_2__0__Impl rule__Node__Group_1_0_2__1
            {
            pushFollow(FOLLOW_3);
            rule__Node__Group_1_0_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Node__Group_1_0_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__Group_1_0_2__0"


    // $ANTLR start "rule__Node__Group_1_0_2__0__Impl"
    // InternalPartialSerializationTestLanguage.g:480:1: rule__Node__Group_1_0_2__0__Impl : ( 'ref' ) ;
    public final void rule__Node__Group_1_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:484:1: ( ( 'ref' ) )
            // InternalPartialSerializationTestLanguage.g:485:1: ( 'ref' )
            {
            // InternalPartialSerializationTestLanguage.g:485:1: ( 'ref' )
            // InternalPartialSerializationTestLanguage.g:486:2: 'ref'
            {
             before(grammarAccess.getNodeAccess().getRefKeyword_1_0_2_0()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getNodeAccess().getRefKeyword_1_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__Group_1_0_2__0__Impl"


    // $ANTLR start "rule__Node__Group_1_0_2__1"
    // InternalPartialSerializationTestLanguage.g:495:1: rule__Node__Group_1_0_2__1 : rule__Node__Group_1_0_2__1__Impl ;
    public final void rule__Node__Group_1_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:499:1: ( rule__Node__Group_1_0_2__1__Impl )
            // InternalPartialSerializationTestLanguage.g:500:2: rule__Node__Group_1_0_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Node__Group_1_0_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__Group_1_0_2__1"


    // $ANTLR start "rule__Node__Group_1_0_2__1__Impl"
    // InternalPartialSerializationTestLanguage.g:506:1: rule__Node__Group_1_0_2__1__Impl : ( ( rule__Node__RefAssignment_1_0_2_1 ) ) ;
    public final void rule__Node__Group_1_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:510:1: ( ( ( rule__Node__RefAssignment_1_0_2_1 ) ) )
            // InternalPartialSerializationTestLanguage.g:511:1: ( ( rule__Node__RefAssignment_1_0_2_1 ) )
            {
            // InternalPartialSerializationTestLanguage.g:511:1: ( ( rule__Node__RefAssignment_1_0_2_1 ) )
            // InternalPartialSerializationTestLanguage.g:512:2: ( rule__Node__RefAssignment_1_0_2_1 )
            {
             before(grammarAccess.getNodeAccess().getRefAssignment_1_0_2_1()); 
            // InternalPartialSerializationTestLanguage.g:513:2: ( rule__Node__RefAssignment_1_0_2_1 )
            // InternalPartialSerializationTestLanguage.g:513:3: rule__Node__RefAssignment_1_0_2_1
            {
            pushFollow(FOLLOW_2);
            rule__Node__RefAssignment_1_0_2_1();

            state._fsp--;


            }

             after(grammarAccess.getNodeAccess().getRefAssignment_1_0_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__Group_1_0_2__1__Impl"


    // $ANTLR start "rule__QualifiedName__Group__0"
    // InternalPartialSerializationTestLanguage.g:522:1: rule__QualifiedName__Group__0 : rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1 ;
    public final void rule__QualifiedName__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:526:1: ( rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1 )
            // InternalPartialSerializationTestLanguage.g:527:2: rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1
            {
            pushFollow(FOLLOW_7);
            rule__QualifiedName__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__0"


    // $ANTLR start "rule__QualifiedName__Group__0__Impl"
    // InternalPartialSerializationTestLanguage.g:534:1: rule__QualifiedName__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedName__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:538:1: ( ( RULE_ID ) )
            // InternalPartialSerializationTestLanguage.g:539:1: ( RULE_ID )
            {
            // InternalPartialSerializationTestLanguage.g:539:1: ( RULE_ID )
            // InternalPartialSerializationTestLanguage.g:540:2: RULE_ID
            {
             before(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__0__Impl"


    // $ANTLR start "rule__QualifiedName__Group__1"
    // InternalPartialSerializationTestLanguage.g:549:1: rule__QualifiedName__Group__1 : rule__QualifiedName__Group__1__Impl ;
    public final void rule__QualifiedName__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:553:1: ( rule__QualifiedName__Group__1__Impl )
            // InternalPartialSerializationTestLanguage.g:554:2: rule__QualifiedName__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__1"


    // $ANTLR start "rule__QualifiedName__Group__1__Impl"
    // InternalPartialSerializationTestLanguage.g:560:1: rule__QualifiedName__Group__1__Impl : ( ( rule__QualifiedName__Group_1__0 )* ) ;
    public final void rule__QualifiedName__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:564:1: ( ( ( rule__QualifiedName__Group_1__0 )* ) )
            // InternalPartialSerializationTestLanguage.g:565:1: ( ( rule__QualifiedName__Group_1__0 )* )
            {
            // InternalPartialSerializationTestLanguage.g:565:1: ( ( rule__QualifiedName__Group_1__0 )* )
            // InternalPartialSerializationTestLanguage.g:566:2: ( rule__QualifiedName__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedNameAccess().getGroup_1()); 
            // InternalPartialSerializationTestLanguage.g:567:2: ( rule__QualifiedName__Group_1__0 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==17) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalPartialSerializationTestLanguage.g:567:3: rule__QualifiedName__Group_1__0
            	    {
            	    pushFollow(FOLLOW_8);
            	    rule__QualifiedName__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

             after(grammarAccess.getQualifiedNameAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__1__Impl"


    // $ANTLR start "rule__QualifiedName__Group_1__0"
    // InternalPartialSerializationTestLanguage.g:576:1: rule__QualifiedName__Group_1__0 : rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1 ;
    public final void rule__QualifiedName__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:580:1: ( rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1 )
            // InternalPartialSerializationTestLanguage.g:581:2: rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1
            {
            pushFollow(FOLLOW_3);
            rule__QualifiedName__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__0"


    // $ANTLR start "rule__QualifiedName__Group_1__0__Impl"
    // InternalPartialSerializationTestLanguage.g:588:1: rule__QualifiedName__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedName__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:592:1: ( ( '.' ) )
            // InternalPartialSerializationTestLanguage.g:593:1: ( '.' )
            {
            // InternalPartialSerializationTestLanguage.g:593:1: ( '.' )
            // InternalPartialSerializationTestLanguage.g:594:2: '.'
            {
             before(grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__0__Impl"


    // $ANTLR start "rule__QualifiedName__Group_1__1"
    // InternalPartialSerializationTestLanguage.g:603:1: rule__QualifiedName__Group_1__1 : rule__QualifiedName__Group_1__1__Impl ;
    public final void rule__QualifiedName__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:607:1: ( rule__QualifiedName__Group_1__1__Impl )
            // InternalPartialSerializationTestLanguage.g:608:2: rule__QualifiedName__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__1"


    // $ANTLR start "rule__QualifiedName__Group_1__1__Impl"
    // InternalPartialSerializationTestLanguage.g:614:1: rule__QualifiedName__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedName__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:618:1: ( ( RULE_ID ) )
            // InternalPartialSerializationTestLanguage.g:619:1: ( RULE_ID )
            {
            // InternalPartialSerializationTestLanguage.g:619:1: ( RULE_ID )
            // InternalPartialSerializationTestLanguage.g:620:2: RULE_ID
            {
             before(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__1__Impl"


    // $ANTLR start "rule__Value__NameAssignment_1"
    // InternalPartialSerializationTestLanguage.g:630:1: rule__Value__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Value__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:634:1: ( ( RULE_ID ) )
            // InternalPartialSerializationTestLanguage.g:635:2: ( RULE_ID )
            {
            // InternalPartialSerializationTestLanguage.g:635:2: ( RULE_ID )
            // InternalPartialSerializationTestLanguage.g:636:3: RULE_ID
            {
             before(grammarAccess.getValueAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getValueAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__NameAssignment_1"


    // $ANTLR start "rule__Node__NameAssignment_0"
    // InternalPartialSerializationTestLanguage.g:645:1: rule__Node__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Node__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:649:1: ( ( RULE_ID ) )
            // InternalPartialSerializationTestLanguage.g:650:2: ( RULE_ID )
            {
            // InternalPartialSerializationTestLanguage.g:650:2: ( RULE_ID )
            // InternalPartialSerializationTestLanguage.g:651:3: RULE_ID
            {
             before(grammarAccess.getNodeAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getNodeAccess().getNameIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__NameAssignment_0"


    // $ANTLR start "rule__Node__ChildrenAssignment_1_0_1"
    // InternalPartialSerializationTestLanguage.g:660:1: rule__Node__ChildrenAssignment_1_0_1 : ( ruleNode ) ;
    public final void rule__Node__ChildrenAssignment_1_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:664:1: ( ( ruleNode ) )
            // InternalPartialSerializationTestLanguage.g:665:2: ( ruleNode )
            {
            // InternalPartialSerializationTestLanguage.g:665:2: ( ruleNode )
            // InternalPartialSerializationTestLanguage.g:666:3: ruleNode
            {
             before(grammarAccess.getNodeAccess().getChildrenNodeParserRuleCall_1_0_1_0()); 
            pushFollow(FOLLOW_2);
            ruleNode();

            state._fsp--;

             after(grammarAccess.getNodeAccess().getChildrenNodeParserRuleCall_1_0_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__ChildrenAssignment_1_0_1"


    // $ANTLR start "rule__Node__RefAssignment_1_0_2_1"
    // InternalPartialSerializationTestLanguage.g:675:1: rule__Node__RefAssignment_1_0_2_1 : ( ( ruleQualifiedName ) ) ;
    public final void rule__Node__RefAssignment_1_0_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalPartialSerializationTestLanguage.g:679:1: ( ( ( ruleQualifiedName ) ) )
            // InternalPartialSerializationTestLanguage.g:680:2: ( ( ruleQualifiedName ) )
            {
            // InternalPartialSerializationTestLanguage.g:680:2: ( ( ruleQualifiedName ) )
            // InternalPartialSerializationTestLanguage.g:681:3: ( ruleQualifiedName )
            {
             before(grammarAccess.getNodeAccess().getRefNodeCrossReference_1_0_2_1_0()); 
            // InternalPartialSerializationTestLanguage.g:682:3: ( ruleQualifiedName )
            // InternalPartialSerializationTestLanguage.g:683:4: ruleQualifiedName
            {
             before(grammarAccess.getNodeAccess().getRefNodeQualifiedNameParserRuleCall_1_0_2_1_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getNodeAccess().getRefNodeQualifiedNameParserRuleCall_1_0_2_1_0_1()); 

            }

             after(grammarAccess.getNodeAccess().getRefNodeCrossReference_1_0_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Node__RefAssignment_1_0_2_1"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000004800L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000018010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000020002L});

}