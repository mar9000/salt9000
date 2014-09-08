// Generated from src/org/mar9000/salt/grammar/Salt.g4 by ANTLR 4.4
package org.mar9000.salt.grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SaltLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__8=1, T__7=2, T__6=3, T__5=4, T__4=5, T__3=6, T__2=7, T__1=8, T__0=9, 
		OPEN=10, OPEN_EMPTY=11, OPEN_BOTH=12, OPEN_VERTICAL=13, OPEN_HORIZONTAL=14, 
		OPEN_EXTERNAL=15, OPEN_MENU=16, OPEN_TAB=17, SELECTED_TAB=18, CLOSE=19, 
		COLUMN_SEPARATOR=20, NL=21, CHAR=22, SPECIAL_CHAR=23, DIGIT=24, SP=25;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'"
	};
	public static final String[] ruleNames = {
		"T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", 
		"OPEN", "OPEN_EMPTY", "OPEN_BOTH", "OPEN_VERTICAL", "OPEN_HORIZONTAL", 
		"OPEN_EXTERNAL", "OPEN_MENU", "OPEN_TAB", "SELECTED_TAB", "CLOSE", "COLUMN_SEPARATOR", 
		"NL", "CHAR", "SPECIAL_CHAR", "DIGIT", "SP"
	};


	public SaltLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Salt.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\33}\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\b"+
		"\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\5\13S\n"+
		"\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3"+
		"\26\5\26r\n\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\2\2\33"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\3\2\6\4\2C\\c|\7\2"+
		"##%\61<B]_ab\3\2\62;\4\2\13\13\"\"\u0081\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\3"+
		"\65\3\2\2\2\5\67\3\2\2\2\7:\3\2\2\2\t<\3\2\2\2\13>\3\2\2\2\rA\3\2\2\2"+
		"\17C\3\2\2\2\21G\3\2\2\2\23I\3\2\2\2\25R\3\2\2\2\27T\3\2\2\2\31V\3\2\2"+
		"\2\33Y\3\2\2\2\35\\\3\2\2\2\37_\3\2\2\2!b\3\2\2\2#e\3\2\2\2%h\3\2\2\2"+
		"\'l\3\2\2\2)n\3\2\2\2+q\3\2\2\2-u\3\2\2\2/w\3\2\2\2\61y\3\2\2\2\63{\3"+
		"\2\2\2\65\66\7_\2\2\66\4\3\2\2\2\678\7]\2\289\7_\2\29\6\3\2\2\2:;\7`\2"+
		"\2;\b\3\2\2\2<=\7\60\2\2=\n\3\2\2\2>?\7*\2\2?@\7+\2\2@\f\3\2\2\2AB\7]"+
		"\2\2B\16\3\2\2\2CD\7]\2\2DE\7Z\2\2EF\7_\2\2F\20\3\2\2\2GH\7$\2\2H\22\3"+
		"\2\2\2IJ\7*\2\2JK\7Z\2\2KL\7+\2\2L\24\3\2\2\2MS\5\27\f\2NS\5\31\r\2OS"+
		"\5\33\16\2PS\5\35\17\2QS\5\37\20\2RM\3\2\2\2RN\3\2\2\2RO\3\2\2\2RP\3\2"+
		"\2\2RQ\3\2\2\2S\26\3\2\2\2TU\7}\2\2U\30\3\2\2\2VW\7}\2\2WX\7%\2\2X\32"+
		"\3\2\2\2YZ\7}\2\2Z[\7#\2\2[\34\3\2\2\2\\]\7}\2\2]^\7/\2\2^\36\3\2\2\2"+
		"_`\7}\2\2`a\7-\2\2a \3\2\2\2bc\7}\2\2cd\7,\2\2d\"\3\2\2\2ef\7}\2\2fg\7"+
		"\61\2\2g$\3\2\2\2hi\7>\2\2ij\7d\2\2jk\7@\2\2k&\3\2\2\2lm\7\177\2\2m(\3"+
		"\2\2\2no\7~\2\2o*\3\2\2\2pr\7\17\2\2qp\3\2\2\2qr\3\2\2\2rs\3\2\2\2st\7"+
		"\f\2\2t,\3\2\2\2uv\t\2\2\2v.\3\2\2\2wx\t\3\2\2x\60\3\2\2\2yz\t\4\2\2z"+
		"\62\3\2\2\2{|\t\5\2\2|\64\3\2\2\2\5\2Rq\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}