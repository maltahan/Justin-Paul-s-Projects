// $ANTLR 3.1.1 /home/tobryan1/workspace/symbolic-algebra/src/Expression.g 2008-11-05 22:34:25

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class ExpressionLexer extends Lexer {
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

    public ExpressionLexer() {;} 
    public ExpressionLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public ExpressionLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/home/tobryan1/workspace/symbolic-algebra/src/Expression.g"; }

    // $ANTLR start "T__7"
    public final void mT__7() throws RecognitionException {
        try {
            int _type = T__7;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:3:6: ( '+' )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:3:8: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__7"

    // $ANTLR start "T__8"
    public final void mT__8() throws RecognitionException {
        try {
            int _type = T__8;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:4:6: ( '-' )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:4:8: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__8"

    // $ANTLR start "T__9"
    public final void mT__9() throws RecognitionException {
        try {
            int _type = T__9;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:5:6: ( '*' )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:5:8: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__9"

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:6:7: ( '/' )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:6:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__10"

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:7:7: ( 'sin ' )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:7:9: 'sin '
            {
            match("sin "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:8:7: ( 'cos ' )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:8:9: 'cos '
            {
            match("cos "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:9:7: ( 'ln ' )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:9:9: 'ln '
            {
            match("ln "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:10:7: ( '~' )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:10:9: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:11:7: ( '^' )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:11:9: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:12:7: ( 'sin(' )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:12:9: 'sin('
            {
            match("sin("); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:13:7: ( 'cos(' )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:13:9: 'cos('
            {
            match("cos("); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:14:7: ( 'ln(' )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:14:9: 'ln('
            {
            match("ln("); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:15:7: ( ')' )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:15:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:16:7: ( '(' )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:16:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:32:8: ( ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )? )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:32:10: ( '-' )? ( '0' .. '9' )+ ( '.' ( '0' .. '9' )* )?
            {
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:32:10: ( '-' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:32:10: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:32:15: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:32:15: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:32:25: ( '.' ( '0' .. '9' )* )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='.') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:32:26: '.' ( '0' .. '9' )*
                    {
                    match('.'); 
                    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:32:30: ( '0' .. '9' )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:32:30: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "VAR"
    public final void mVAR() throws RecognitionException {
        try {
            int _type = VAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:33:5: ( 'x' )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:33:7: 'x'
            {
            match('x'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VAR"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:34:12: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:34:14: ( ' ' | '\\t' | '\\n' | '\\r' )+
            {
            // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:34:14: ( ' ' | '\\t' | '\\n' | '\\r' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\t' && LA5_0<='\n')||LA5_0=='\r'||LA5_0==' ') ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);

             skip(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHITESPACE"

    public void mTokens() throws RecognitionException {
        // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:8: ( T__7 | T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | NUMBER | VAR | WHITESPACE )
        int alt6=17;
        alt6 = dfa6.predict(input);
        switch (alt6) {
            case 1 :
                // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:10: T__7
                {
                mT__7(); 

                }
                break;
            case 2 :
                // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:15: T__8
                {
                mT__8(); 

                }
                break;
            case 3 :
                // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:20: T__9
                {
                mT__9(); 

                }
                break;
            case 4 :
                // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:25: T__10
                {
                mT__10(); 

                }
                break;
            case 5 :
                // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:31: T__11
                {
                mT__11(); 

                }
                break;
            case 6 :
                // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:37: T__12
                {
                mT__12(); 

                }
                break;
            case 7 :
                // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:43: T__13
                {
                mT__13(); 

                }
                break;
            case 8 :
                // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:49: T__14
                {
                mT__14(); 

                }
                break;
            case 9 :
                // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:55: T__15
                {
                mT__15(); 

                }
                break;
            case 10 :
                // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:61: T__16
                {
                mT__16(); 

                }
                break;
            case 11 :
                // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:67: T__17
                {
                mT__17(); 

                }
                break;
            case 12 :
                // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:73: T__18
                {
                mT__18(); 

                }
                break;
            case 13 :
                // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:79: T__19
                {
                mT__19(); 

                }
                break;
            case 14 :
                // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:85: T__20
                {
                mT__20(); 

                }
                break;
            case 15 :
                // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:91: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 16 :
                // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:98: VAR
                {
                mVAR(); 

                }
                break;
            case 17 :
                // /home/tobryan1/workspace/symbolic-algebra/src/Expression.g:1:102: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;

        }

    }


    protected DFA6 dfa6 = new DFA6(this);
    static final String DFA6_eotS =
        "\2\uffff\1\17\30\uffff";
    static final String DFA6_eofS =
        "\33\uffff";
    static final String DFA6_minS =
        "\1\11\1\uffff\1\60\2\uffff\1\151\1\157\1\156\10\uffff\1\156\1\163"+
        "\3\40\6\uffff";
    static final String DFA6_maxS =
        "\1\176\1\uffff\1\71\2\uffff\1\151\1\157\1\156\10\uffff\1\156\1\163"+
        "\3\50\6\uffff";
    static final String DFA6_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\4\3\uffff\1\10\1\11\1\15\1\16\1\17\1"+
        "\20\1\21\1\2\5\uffff\1\7\1\14\1\5\1\12\1\6\1\13";
    static final String DFA6_specialS =
        "\33\uffff}>";
    static final String[] DFA6_transitionS = {
            "\2\16\2\uffff\1\16\22\uffff\1\16\7\uffff\1\13\1\12\1\3\1\1\1"+
            "\uffff\1\2\1\uffff\1\4\12\14\44\uffff\1\11\4\uffff\1\6\10\uffff"+
            "\1\7\6\uffff\1\5\4\uffff\1\15\5\uffff\1\10",
            "",
            "\12\14",
            "",
            "",
            "\1\20",
            "\1\21",
            "\1\22",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\23",
            "\1\24",
            "\1\25\7\uffff\1\26",
            "\1\27\7\uffff\1\30",
            "\1\31\7\uffff\1\32",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__7 | T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | NUMBER | VAR | WHITESPACE );";
        }
    }
 

}