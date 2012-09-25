// $ANTLR 3.1.1 /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g 2008-11-05 22:27:24

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class ExprTree extends TreeParser {
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


        public ExprTree(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public ExprTree(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return ExprTree.tokenNames; }
    public String getGrammarFileName() { return "/home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g"; }



    // $ANTLR start "expression"
    // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:9:1: expression returns [IExpression value] : val= expr EOF ;
    public final IExpression expression() throws RecognitionException {
        IExpression value = null;

        IExpression val = null;


        try {
            // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:10:3: (val= expr EOF )
            // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:10:5: val= expr EOF
            {
            pushFollow(FOLLOW_expr_in_expression45);
            val=expr();

            state._fsp--;

            match(input,EOF,FOLLOW_EOF_in_expression47); 
             value = val; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "expression"


    // $ANTLR start "expr"
    // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:13:1: expr returns [IExpression value] : ( ^( '^' base= expr exponent= expr ) | ^( 'sin ' arg= expr ) | ^( 'cos ' arg= expr ) | ^( 'ln ' arg= expr ) | ^( 'sin(' arg= expr ) | ^( 'cos(' arg= expr ) | ^( 'ln(' arg= expr ) | ^( '~' val= expr ) | ^( '*' left= expr right= expr ) | ^( '/' top= expr bottom= expr ) | ^( '+' left= expr right= expr ) | ^( '-' left= expr right= expr ) | VAR | NUMBER );
    public final IExpression expr() throws RecognitionException {
        IExpression value = null;

        CommonTree NUMBER1=null;
        IExpression base = null;

        IExpression exponent = null;

        IExpression arg = null;

        IExpression val = null;

        IExpression left = null;

        IExpression right = null;

        IExpression top = null;

        IExpression bottom = null;


        try {
            // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:14:3: ( ^( '^' base= expr exponent= expr ) | ^( 'sin ' arg= expr ) | ^( 'cos ' arg= expr ) | ^( 'ln ' arg= expr ) | ^( 'sin(' arg= expr ) | ^( 'cos(' arg= expr ) | ^( 'ln(' arg= expr ) | ^( '~' val= expr ) | ^( '*' left= expr right= expr ) | ^( '/' top= expr bottom= expr ) | ^( '+' left= expr right= expr ) | ^( '-' left= expr right= expr ) | VAR | NUMBER )
            int alt1=14;
            switch ( input.LA(1) ) {
            case 15:
                {
                alt1=1;
                }
                break;
            case 11:
                {
                alt1=2;
                }
                break;
            case 12:
                {
                alt1=3;
                }
                break;
            case 13:
                {
                alt1=4;
                }
                break;
            case 16:
                {
                alt1=5;
                }
                break;
            case 17:
                {
                alt1=6;
                }
                break;
            case 18:
                {
                alt1=7;
                }
                break;
            case 14:
                {
                alt1=8;
                }
                break;
            case 9:
                {
                alt1=9;
                }
                break;
            case 10:
                {
                alt1=10;
                }
                break;
            case 7:
                {
                alt1=11;
                }
                break;
            case 8:
                {
                alt1=12;
                }
                break;
            case VAR:
                {
                alt1=13;
                }
                break;
            case NUMBER:
                {
                alt1=14;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:14:5: ^( '^' base= expr exponent= expr )
                    {
                    match(input,15,FOLLOW_15_in_expr67); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr71);
                    base=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr75);
                    exponent=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     value = new Exponentiation(base, exponent); 

                    }
                    break;
                case 2 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:15:5: ^( 'sin ' arg= expr )
                    {
                    match(input,11,FOLLOW_11_in_expr85); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr89);
                    arg=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     value = new Sin(arg); 

                    }
                    break;
                case 3 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:16:5: ^( 'cos ' arg= expr )
                    {
                    match(input,12,FOLLOW_12_in_expr99); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr103);
                    arg=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     value = new Cos(arg); 

                    }
                    break;
                case 4 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:17:5: ^( 'ln ' arg= expr )
                    {
                    match(input,13,FOLLOW_13_in_expr113); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr117);
                    arg=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     value = new Ln(arg); 

                    }
                    break;
                case 5 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:18:5: ^( 'sin(' arg= expr )
                    {
                    match(input,16,FOLLOW_16_in_expr127); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr131);
                    arg=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     value = new Sin(arg); 

                    }
                    break;
                case 6 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:19:5: ^( 'cos(' arg= expr )
                    {
                    match(input,17,FOLLOW_17_in_expr141); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr145);
                    arg=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     value = new Cos(arg); 

                    }
                    break;
                case 7 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:20:5: ^( 'ln(' arg= expr )
                    {
                    match(input,18,FOLLOW_18_in_expr155); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr159);
                    arg=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     value = new Ln(arg); 

                    }
                    break;
                case 8 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:21:5: ^( '~' val= expr )
                    {
                    match(input,14,FOLLOW_14_in_expr169); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr173);
                    val=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     value = new Neg(val); 

                    }
                    break;
                case 9 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:22:5: ^( '*' left= expr right= expr )
                    {
                    match(input,9,FOLLOW_9_in_expr183); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr187);
                    left=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr191);
                    right=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     value = new Product(left, right); 

                    }
                    break;
                case 10 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:23:5: ^( '/' top= expr bottom= expr )
                    {
                    match(input,10,FOLLOW_10_in_expr201); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr205);
                    top=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr209);
                    bottom=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     value = new Quotient(top, bottom); 

                    }
                    break;
                case 11 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:24:5: ^( '+' left= expr right= expr )
                    {
                    match(input,7,FOLLOW_7_in_expr219); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr223);
                    left=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr227);
                    right=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     value = new Sum(left, right); 

                    }
                    break;
                case 12 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:25:5: ^( '-' left= expr right= expr )
                    {
                    match(input,8,FOLLOW_8_in_expr237); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr241);
                    left=expr();

                    state._fsp--;

                    pushFollow(FOLLOW_expr_in_expr245);
                    right=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 
                     value = new Difference(left, right); 

                    }
                    break;
                case 13 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:26:5: VAR
                    {
                    match(input,VAR,FOLLOW_VAR_in_expr254); 
                     value = new Var(); 

                    }
                    break;
                case 14 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/ExprTree.g:27:5: NUMBER
                    {
                    NUMBER1=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_expr262); 
                     value = new Number(Double.parseDouble((NUMBER1!=null?NUMBER1.getText():null))); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "expr"

    // Delegated rules


 

    public static final BitSet FOLLOW_expr_in_expression45 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_expression47 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_expr67 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr71 = new BitSet(new long[]{0x000000000007FFB0L});
    public static final BitSet FOLLOW_expr_in_expr75 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_11_in_expr85 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr89 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_12_in_expr99 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr103 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_13_in_expr113 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr117 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_16_in_expr127 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr131 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_17_in_expr141 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr145 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_18_in_expr155 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr159 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_14_in_expr169 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr173 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_9_in_expr183 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr187 = new BitSet(new long[]{0x000000000007FFB0L});
    public static final BitSet FOLLOW_expr_in_expr191 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_10_in_expr201 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr205 = new BitSet(new long[]{0x000000000007FFB0L});
    public static final BitSet FOLLOW_expr_in_expr209 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_7_in_expr219 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr223 = new BitSet(new long[]{0x000000000007FFB0L});
    public static final BitSet FOLLOW_expr_in_expr227 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_8_in_expr237 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr241 = new BitSet(new long[]{0x000000000007FFB0L});
    public static final BitSet FOLLOW_expr_in_expr245 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_VAR_in_expr254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_expr262 = new BitSet(new long[]{0x0000000000000002L});

}