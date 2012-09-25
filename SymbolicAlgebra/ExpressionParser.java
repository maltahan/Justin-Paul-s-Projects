// $ANTLR 3.1.1 /home/tobryan1/workspace/symbolic-algebra/src/Expression.g 2008-11-05 22:34:25

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class ExpressionParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "NUMBER", "VAR", "WHITESPACE", "'+'", "'-'", "'*'", "'/'", "'sin '", "'cos '", "'ln '", "'~'", "'^'", "'sin('", "'cos('", "'ln('", "')'", "'('"
    };
    public static final int T__20=20;
    public static final int NUMBER=4;
    public static final int WHITESPACE=6;
    public static final int EOF=-1;
    public static final int T__9=9;
    public static final int T__8=8;
    public static final int T__7=7;
    public static final int T__19=19;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int T__10=10;
    public static final int VAR=5;

    // delegates
    // delegators


        public ExpressionParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public ExpressionParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return ExpressionParser.tokenNames; }
    public String getGrammarFileName() { return "/home/tobryan1/workspace/symbolic-algebra/src/Expression.g"; }


    public static class expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expr"
    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:7:1: expr : addExpr EOF ;
    public final ExpressionParser.expr_return expr() throws RecognitionException {
        ExpressionParser.expr_return retval = new ExpressionParser.expr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EOF2=null;
        ExpressionParser.addExpr_return addExpr1 = null;


        CommonTree EOF2_tree=null;

        try {
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:7:6: ( addExpr EOF )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:7:8: addExpr EOF
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_addExpr_in_expr30);
            addExpr1=addExpr();

            state._fsp--;

            adaptor.addChild(root_0, addExpr1.getTree());
            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_expr32); 

            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expr"

    public static class addExpr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "addExpr"
    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:10:1: addExpr : multExpr ( ( '+' | '-' ) multExpr )* ;
    public final ExpressionParser.addExpr_return addExpr() throws RecognitionException {
        ExpressionParser.addExpr_return retval = new ExpressionParser.addExpr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal4=null;
        Token char_literal5=null;
        ExpressionParser.multExpr_return multExpr3 = null;

        ExpressionParser.multExpr_return multExpr6 = null;


        CommonTree char_literal4_tree=null;
        CommonTree char_literal5_tree=null;

        try {
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:10:9: ( multExpr ( ( '+' | '-' ) multExpr )* )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:10:11: multExpr ( ( '+' | '-' ) multExpr )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_multExpr_in_addExpr44);
            multExpr3=multExpr();

            state._fsp--;

            adaptor.addChild(root_0, multExpr3.getTree());
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:10:20: ( ( '+' | '-' ) multExpr )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=7 && LA2_0<=8)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:10:21: ( '+' | '-' ) multExpr
            	    {
            	    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:10:21: ( '+' | '-' )
            	    int alt1=2;
            	    int LA1_0 = input.LA(1);

            	    if ( (LA1_0==7) ) {
            	        alt1=1;
            	    }
            	    else if ( (LA1_0==8) ) {
            	        alt1=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 1, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt1) {
            	        case 1 :
            	            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:10:22: '+'
            	            {
            	            char_literal4=(Token)match(input,7,FOLLOW_7_in_addExpr48); 
            	            char_literal4_tree = (CommonTree)adaptor.create(char_literal4);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal4_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:10:27: '-'
            	            {
            	            char_literal5=(Token)match(input,8,FOLLOW_8_in_addExpr51); 
            	            char_literal5_tree = (CommonTree)adaptor.create(char_literal5);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal5_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_multExpr_in_addExpr55);
            	    multExpr6=multExpr();

            	    state._fsp--;

            	    adaptor.addChild(root_0, multExpr6.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "addExpr"

    public static class multExpr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "multExpr"
    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:13:1: multExpr : unaryExpr ( ( '*' | '/' ) unaryExpr )* ;
    public final ExpressionParser.multExpr_return multExpr() throws RecognitionException {
        ExpressionParser.multExpr_return retval = new ExpressionParser.multExpr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal8=null;
        Token char_literal9=null;
        ExpressionParser.unaryExpr_return unaryExpr7 = null;

        ExpressionParser.unaryExpr_return unaryExpr10 = null;


        CommonTree char_literal8_tree=null;
        CommonTree char_literal9_tree=null;

        try {
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:13:10: ( unaryExpr ( ( '*' | '/' ) unaryExpr )* )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:13:12: unaryExpr ( ( '*' | '/' ) unaryExpr )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_unaryExpr_in_multExpr68);
            unaryExpr7=unaryExpr();

            state._fsp--;

            adaptor.addChild(root_0, unaryExpr7.getTree());
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:13:22: ( ( '*' | '/' ) unaryExpr )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=9 && LA4_0<=10)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:13:23: ( '*' | '/' ) unaryExpr
            	    {
            	    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:13:23: ( '*' | '/' )
            	    int alt3=2;
            	    int LA3_0 = input.LA(1);

            	    if ( (LA3_0==9) ) {
            	        alt3=1;
            	    }
            	    else if ( (LA3_0==10) ) {
            	        alt3=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 3, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt3) {
            	        case 1 :
            	            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:13:24: '*'
            	            {
            	            char_literal8=(Token)match(input,9,FOLLOW_9_in_multExpr72); 
            	            char_literal8_tree = (CommonTree)adaptor.create(char_literal8);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal8_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:13:29: '/'
            	            {
            	            char_literal9=(Token)match(input,10,FOLLOW_10_in_multExpr75); 
            	            char_literal9_tree = (CommonTree)adaptor.create(char_literal9);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal9_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_unaryExpr_in_multExpr79);
            	    unaryExpr10=unaryExpr();

            	    state._fsp--;

            	    adaptor.addChild(root_0, unaryExpr10.getTree());

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "multExpr"

    public static class unaryExpr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "unaryExpr"
    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:16:1: unaryExpr : ( ( 'sin ' | 'cos ' | 'ln ' | '~' ) unaryExpr | expExpr );
    public final ExpressionParser.unaryExpr_return unaryExpr() throws RecognitionException {
        ExpressionParser.unaryExpr_return retval = new ExpressionParser.unaryExpr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal11=null;
        Token string_literal12=null;
        Token string_literal13=null;
        Token char_literal14=null;
        ExpressionParser.unaryExpr_return unaryExpr15 = null;

        ExpressionParser.expExpr_return expExpr16 = null;


        CommonTree string_literal11_tree=null;
        CommonTree string_literal12_tree=null;
        CommonTree string_literal13_tree=null;
        CommonTree char_literal14_tree=null;

        try {
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:16:11: ( ( 'sin ' | 'cos ' | 'ln ' | '~' ) unaryExpr | expExpr )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( ((LA6_0>=11 && LA6_0<=14)) ) {
                alt6=1;
            }
            else if ( ((LA6_0>=NUMBER && LA6_0<=VAR)||(LA6_0>=16 && LA6_0<=18)||LA6_0==20) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:16:13: ( 'sin ' | 'cos ' | 'ln ' | '~' ) unaryExpr
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:16:13: ( 'sin ' | 'cos ' | 'ln ' | '~' )
                    int alt5=4;
                    switch ( input.LA(1) ) {
                    case 11:
                        {
                        alt5=1;
                        }
                        break;
                    case 12:
                        {
                        alt5=2;
                        }
                        break;
                    case 13:
                        {
                        alt5=3;
                        }
                        break;
                    case 14:
                        {
                        alt5=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 0, input);

                        throw nvae;
                    }

                    switch (alt5) {
                        case 1 :
                            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:16:14: 'sin '
                            {
                            string_literal11=(Token)match(input,11,FOLLOW_11_in_unaryExpr95); 
                            string_literal11_tree = (CommonTree)adaptor.create(string_literal11);
                            root_0 = (CommonTree)adaptor.becomeRoot(string_literal11_tree, root_0);


                            }
                            break;
                        case 2 :
                            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:16:22: 'cos '
                            {
                            string_literal12=(Token)match(input,12,FOLLOW_12_in_unaryExpr98); 
                            string_literal12_tree = (CommonTree)adaptor.create(string_literal12);
                            root_0 = (CommonTree)adaptor.becomeRoot(string_literal12_tree, root_0);


                            }
                            break;
                        case 3 :
                            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:16:30: 'ln '
                            {
                            string_literal13=(Token)match(input,13,FOLLOW_13_in_unaryExpr101); 
                            string_literal13_tree = (CommonTree)adaptor.create(string_literal13);
                            root_0 = (CommonTree)adaptor.becomeRoot(string_literal13_tree, root_0);


                            }
                            break;
                        case 4 :
                            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:16:37: '~'
                            {
                            char_literal14=(Token)match(input,14,FOLLOW_14_in_unaryExpr104); 
                            char_literal14_tree = (CommonTree)adaptor.create(char_literal14);
                            root_0 = (CommonTree)adaptor.becomeRoot(char_literal14_tree, root_0);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_unaryExpr_in_unaryExpr108);
                    unaryExpr15=unaryExpr();

                    state._fsp--;

                    adaptor.addChild(root_0, unaryExpr15.getTree());

                    }
                    break;
                case 2 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:17:5: expExpr
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_expExpr_in_unaryExpr114);
                    expExpr16=expExpr();

                    state._fsp--;

                    adaptor.addChild(root_0, expExpr16.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "unaryExpr"

    public static class expExpr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expExpr"
    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:20:1: expExpr : atom ( '^' expExpr )? ;
    public final ExpressionParser.expExpr_return expExpr() throws RecognitionException {
        ExpressionParser.expExpr_return retval = new ExpressionParser.expExpr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal18=null;
        ExpressionParser.atom_return atom17 = null;

        ExpressionParser.expExpr_return expExpr19 = null;


        CommonTree char_literal18_tree=null;

        try {
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:20:9: ( atom ( '^' expExpr )? )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:20:11: atom ( '^' expExpr )?
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_atom_in_expExpr127);
            atom17=atom();

            state._fsp--;

            adaptor.addChild(root_0, atom17.getTree());
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:20:16: ( '^' expExpr )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==15) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:20:17: '^' expExpr
                    {
                    char_literal18=(Token)match(input,15,FOLLOW_15_in_expExpr130); 
                    char_literal18_tree = (CommonTree)adaptor.create(char_literal18);
                    root_0 = (CommonTree)adaptor.becomeRoot(char_literal18_tree, root_0);

                    pushFollow(FOLLOW_expExpr_in_expExpr133);
                    expExpr19=expExpr();

                    state._fsp--;

                    adaptor.addChild(root_0, expExpr19.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expExpr"

    public static class parenExpr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parenExpr"
    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:23:1: parenExpr : ( ( 'sin(' | 'cos(' | 'ln(' ) addExpr ')' | '(' addExpr ')' );
    public final ExpressionParser.parenExpr_return parenExpr() throws RecognitionException {
        ExpressionParser.parenExpr_return retval = new ExpressionParser.parenExpr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal20=null;
        Token string_literal21=null;
        Token string_literal22=null;
        Token char_literal24=null;
        Token char_literal25=null;
        Token char_literal27=null;
        ExpressionParser.addExpr_return addExpr23 = null;

        ExpressionParser.addExpr_return addExpr26 = null;


        CommonTree string_literal20_tree=null;
        CommonTree string_literal21_tree=null;
        CommonTree string_literal22_tree=null;
        CommonTree char_literal24_tree=null;
        CommonTree char_literal25_tree=null;
        CommonTree char_literal27_tree=null;

        try {
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:23:11: ( ( 'sin(' | 'cos(' | 'ln(' ) addExpr ')' | '(' addExpr ')' )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( ((LA9_0>=16 && LA9_0<=18)) ) {
                alt9=1;
            }
            else if ( (LA9_0==20) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:23:13: ( 'sin(' | 'cos(' | 'ln(' ) addExpr ')'
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:23:13: ( 'sin(' | 'cos(' | 'ln(' )
                    int alt8=3;
                    switch ( input.LA(1) ) {
                    case 16:
                        {
                        alt8=1;
                        }
                        break;
                    case 17:
                        {
                        alt8=2;
                        }
                        break;
                    case 18:
                        {
                        alt8=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 0, input);

                        throw nvae;
                    }

                    switch (alt8) {
                        case 1 :
                            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:23:14: 'sin('
                            {
                            string_literal20=(Token)match(input,16,FOLLOW_16_in_parenExpr147); 
                            string_literal20_tree = (CommonTree)adaptor.create(string_literal20);
                            root_0 = (CommonTree)adaptor.becomeRoot(string_literal20_tree, root_0);


                            }
                            break;
                        case 2 :
                            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:23:22: 'cos('
                            {
                            string_literal21=(Token)match(input,17,FOLLOW_17_in_parenExpr150); 
                            string_literal21_tree = (CommonTree)adaptor.create(string_literal21);
                            root_0 = (CommonTree)adaptor.becomeRoot(string_literal21_tree, root_0);


                            }
                            break;
                        case 3 :
                            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:23:30: 'ln('
                            {
                            string_literal22=(Token)match(input,18,FOLLOW_18_in_parenExpr153); 
                            string_literal22_tree = (CommonTree)adaptor.create(string_literal22);
                            root_0 = (CommonTree)adaptor.becomeRoot(string_literal22_tree, root_0);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_addExpr_in_parenExpr157);
                    addExpr23=addExpr();

                    state._fsp--;

                    adaptor.addChild(root_0, addExpr23.getTree());
                    char_literal24=(Token)match(input,19,FOLLOW_19_in_parenExpr159); 

                    }
                    break;
                case 2 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:24:5: '(' addExpr ')'
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    char_literal25=(Token)match(input,20,FOLLOW_20_in_parenExpr166); 
                    pushFollow(FOLLOW_addExpr_in_parenExpr169);
                    addExpr26=addExpr();

                    state._fsp--;

                    adaptor.addChild(root_0, addExpr26.getTree());
                    char_literal27=(Token)match(input,19,FOLLOW_19_in_parenExpr171); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "parenExpr"

    public static class atom_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "atom"
    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:27:1: atom : ( parenExpr | NUMBER | VAR );
    public final ExpressionParser.atom_return atom() throws RecognitionException {
        ExpressionParser.atom_return retval = new ExpressionParser.atom_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NUMBER29=null;
        Token VAR30=null;
        ExpressionParser.parenExpr_return parenExpr28 = null;


        CommonTree NUMBER29_tree=null;
        CommonTree VAR30_tree=null;

        try {
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:27:6: ( parenExpr | NUMBER | VAR )
            int alt10=3;
            switch ( input.LA(1) ) {
            case 16:
            case 17:
            case 18:
            case 20:
                {
                alt10=1;
                }
                break;
            case NUMBER:
                {
                alt10=2;
                }
                break;
            case VAR:
                {
                alt10=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:27:8: parenExpr
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_parenExpr_in_atom185);
                    parenExpr28=parenExpr();

                    state._fsp--;

                    adaptor.addChild(root_0, parenExpr28.getTree());

                    }
                    break;
                case 2 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:28:5: NUMBER
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    NUMBER29=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom192); 
                    NUMBER29_tree = (CommonTree)adaptor.create(NUMBER29);
                    adaptor.addChild(root_0, NUMBER29_tree);


                    }
                    break;
                case 3 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:29:5: VAR
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    VAR30=(Token)match(input,VAR,FOLLOW_VAR_in_atom198); 
                    VAR30_tree = (CommonTree)adaptor.create(VAR30);
                    adaptor.addChild(root_0, VAR30_tree);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "atom"

    // Delegated rules


 

    public static final BitSet FOLLOW_addExpr_in_expr30 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_expr32 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multExpr_in_addExpr44 = new BitSet(new long[]{0x0000000000000182L});
    public static final BitSet FOLLOW_7_in_addExpr48 = new BitSet(new long[]{0x0000000000177830L});
    public static final BitSet FOLLOW_8_in_addExpr51 = new BitSet(new long[]{0x0000000000177830L});
    public static final BitSet FOLLOW_multExpr_in_addExpr55 = new BitSet(new long[]{0x0000000000000182L});
    public static final BitSet FOLLOW_unaryExpr_in_multExpr68 = new BitSet(new long[]{0x0000000000000602L});
    public static final BitSet FOLLOW_9_in_multExpr72 = new BitSet(new long[]{0x0000000000177830L});
    public static final BitSet FOLLOW_10_in_multExpr75 = new BitSet(new long[]{0x0000000000177830L});
    public static final BitSet FOLLOW_unaryExpr_in_multExpr79 = new BitSet(new long[]{0x0000000000000602L});
    public static final BitSet FOLLOW_11_in_unaryExpr95 = new BitSet(new long[]{0x0000000000177830L});
    public static final BitSet FOLLOW_12_in_unaryExpr98 = new BitSet(new long[]{0x0000000000177830L});
    public static final BitSet FOLLOW_13_in_unaryExpr101 = new BitSet(new long[]{0x0000000000177830L});
    public static final BitSet FOLLOW_14_in_unaryExpr104 = new BitSet(new long[]{0x0000000000177830L});
    public static final BitSet FOLLOW_unaryExpr_in_unaryExpr108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expExpr_in_unaryExpr114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_expExpr127 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_expExpr130 = new BitSet(new long[]{0x0000000000177830L});
    public static final BitSet FOLLOW_expExpr_in_expExpr133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_parenExpr147 = new BitSet(new long[]{0x0000000000177830L});
    public static final BitSet FOLLOW_17_in_parenExpr150 = new BitSet(new long[]{0x0000000000177830L});
    public static final BitSet FOLLOW_18_in_parenExpr153 = new BitSet(new long[]{0x0000000000177830L});
    public static final BitSet FOLLOW_addExpr_in_parenExpr157 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_parenExpr159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_parenExpr166 = new BitSet(new long[]{0x0000000000177830L});
    public static final BitSet FOLLOW_addExpr_in_parenExpr169 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_parenExpr171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenExpr_in_atom185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_atom192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_atom198 = new BitSet(new long[]{0x0000000000000002L});

}