// Generated from C:/Users/HP/Desktop/Compiladores/ProjetoTuga/src/Tuga.g4 by ANTLR 4.13.2
package Tuga;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class TugaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, LPAREN=12, RPAREN=13, NOT=14, MULT=15, DIV=16, MOD=17, 
		SUM=18, SUB=19, LESS=20, GREATER=21, LESS_EQ=22, GREATER_EQ=23, EQUALS=24, 
		N_EQUALS=25, AND=26, OR=27, END_INST=28, INT=29, DOUBLE=30, STRING=31, 
		TRUE=32, FALSE=33, INT_TYPE=34, DOUBLE_TYPE=35, STRING_TYPE=36, BOOLEAN_TYPE=37, 
		VAR=38, WS=39, SL_COMMENT=40, ML_COMMENT=41;
	public static final int
		RULE_tuga = 0, RULE_declare_var = 1, RULE_variable = 2, RULE_function = 3, 
		RULE_arg_list = 4, RULE_argument = 5, RULE_function_call = 6, RULE_expr_list = 7, 
		RULE_inst = 8, RULE_print = 9, RULE_assign = 10, RULE_scope = 11, RULE_scopeOrInst = 12, 
		RULE_if = 13, RULE_ifelse = 14, RULE_while = 15, RULE_function_call_inst = 16, 
		RULE_return = 17, RULE_empty = 18, RULE_expr = 19, RULE_literal = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"tuga", "declare_var", "variable", "function", "arg_list", "argument", 
			"function_call", "expr_list", "inst", "print", "assign", "scope", "scopeOrInst", 
			"if", "ifelse", "while", "function_call_inst", "return", "empty", "expr", 
			"literal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "','", "'funcao'", "'escreve'", "'<-'", "'inicio'", "'fim'", 
			"'se'", "'senao'", "'enquanto'", "'retorna'", "'('", "')'", "'nao'", 
			"'*'", "'/'", "'%'", "'+'", "'-'", "'<'", "'>'", "'<='", "'>='", "'igual'", 
			"'diferente'", "'e'", "'ou'", "';'", null, null, null, "'verdadeiro'", 
			"'falso'", "'inteiro'", "'real'", "'string'", "'booleano'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"LPAREN", "RPAREN", "NOT", "MULT", "DIV", "MOD", "SUM", "SUB", "LESS", 
			"GREATER", "LESS_EQ", "GREATER_EQ", "EQUALS", "N_EQUALS", "AND", "OR", 
			"END_INST", "INT", "DOUBLE", "STRING", "TRUE", "FALSE", "INT_TYPE", "DOUBLE_TYPE", 
			"STRING_TYPE", "BOOLEAN_TYPE", "VAR", "WS", "SL_COMMENT", "ML_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Tuga.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TugaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TugaContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(TugaParser.EOF, 0); }
		public List<Declare_varContext> declare_var() {
			return getRuleContexts(Declare_varContext.class);
		}
		public Declare_varContext declare_var(int i) {
			return getRuleContext(Declare_varContext.class,i);
		}
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public TugaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tuga; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterTuga(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitTuga(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitTuga(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TugaContext tuga() throws RecognitionException {
		TugaContext _localctx = new TugaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_tuga);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VAR) {
				{
				{
				setState(42);
				declare_var();
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(48);
				function();
				}
				}
				setState(51); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
			setState(53);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Declare_varContext extends ParserRuleContext {
		public Declare_varContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declare_var; }
	 
		public Declare_varContext() { }
		public void copyFrom(Declare_varContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclVarContext extends Declare_varContext {
		public Token type;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode END_INST() { return getToken(TugaParser.END_INST, 0); }
		public TerminalNode INT_TYPE() { return getToken(TugaParser.INT_TYPE, 0); }
		public TerminalNode DOUBLE_TYPE() { return getToken(TugaParser.DOUBLE_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(TugaParser.STRING_TYPE, 0); }
		public TerminalNode BOOLEAN_TYPE() { return getToken(TugaParser.BOOLEAN_TYPE, 0); }
		public DeclVarContext(Declare_varContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterDeclVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitDeclVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitDeclVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Declare_varContext declare_var() throws RecognitionException {
		Declare_varContext _localctx = new Declare_varContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declare_var);
		int _la;
		try {
			_localctx = new DeclVarContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			variable();
			setState(56);
			match(T__0);
			setState(57);
			((DeclVarContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 257698037760L) != 0)) ) {
				((DeclVarContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(58);
			match(END_INST);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableContext extends ParserRuleContext {
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
	 
		public VariableContext() { }
		public void copyFrom(VariableContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarsContext extends VariableContext {
		public TerminalNode VAR() { return getToken(TugaParser.VAR, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public VarsContext(VariableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterVars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitVars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitVars(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarContext extends VariableContext {
		public TerminalNode VAR() { return getToken(TugaParser.VAR, 0); }
		public VarContext(VariableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_variable);
		try {
			setState(64);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				_localctx = new VarsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				match(VAR);
				setState(61);
				match(T__1);
				setState(62);
				variable();
				}
				break;
			case 2:
				_localctx = new VarContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(63);
				match(VAR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionContext extends ParserRuleContext {
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
	 
		public FunctionContext() { }
		public void copyFrom(FunctionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionDeclContext extends FunctionContext {
		public Token type;
		public TerminalNode VAR() { return getToken(TugaParser.VAR, 0); }
		public TerminalNode LPAREN() { return getToken(TugaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(TugaParser.RPAREN, 0); }
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public Arg_listContext arg_list() {
			return getRuleContext(Arg_listContext.class,0);
		}
		public TerminalNode INT_TYPE() { return getToken(TugaParser.INT_TYPE, 0); }
		public TerminalNode DOUBLE_TYPE() { return getToken(TugaParser.DOUBLE_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(TugaParser.STRING_TYPE, 0); }
		public TerminalNode BOOLEAN_TYPE() { return getToken(TugaParser.BOOLEAN_TYPE, 0); }
		public FunctionDeclContext(FunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterFunctionDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitFunctionDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitFunctionDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_function);
		int _la;
		try {
			_localctx = new FunctionDeclContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(T__2);
			setState(67);
			match(VAR);
			setState(68);
			match(LPAREN);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(69);
				arg_list();
				}
			}

			setState(72);
			match(RPAREN);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(73);
				match(T__0);
				setState(74);
				((FunctionDeclContext)_localctx).type = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 257698037760L) != 0)) ) {
					((FunctionDeclContext)_localctx).type = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(77);
			scope();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Arg_listContext extends ParserRuleContext {
		public Arg_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg_list; }
	 
		public Arg_listContext() { }
		public void copyFrom(Arg_listContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArgsContext extends Arg_listContext {
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public Arg_listContext arg_list() {
			return getRuleContext(Arg_listContext.class,0);
		}
		public ArgsContext(Arg_listContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArgContext extends Arg_listContext {
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public ArgContext(Arg_listContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Arg_listContext arg_list() throws RecognitionException {
		Arg_listContext _localctx = new Arg_listContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_arg_list);
		try {
			setState(84);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new ArgsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				argument();
				setState(80);
				match(T__1);
				setState(81);
				arg_list();
				}
				break;
			case 2:
				_localctx = new ArgContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				argument();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentContext extends ParserRuleContext {
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
	 
		public ArgumentContext() { }
		public void copyFrom(ArgumentContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclArgContext extends ArgumentContext {
		public Token type;
		public TerminalNode VAR() { return getToken(TugaParser.VAR, 0); }
		public TerminalNode INT_TYPE() { return getToken(TugaParser.INT_TYPE, 0); }
		public TerminalNode DOUBLE_TYPE() { return getToken(TugaParser.DOUBLE_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(TugaParser.STRING_TYPE, 0); }
		public TerminalNode BOOLEAN_TYPE() { return getToken(TugaParser.BOOLEAN_TYPE, 0); }
		public DeclArgContext(ArgumentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterDeclArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitDeclArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitDeclArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_argument);
		int _la;
		try {
			_localctx = new DeclArgContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(VAR);
			setState(87);
			match(T__0);
			setState(88);
			((DeclArgContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 257698037760L) != 0)) ) {
				((DeclArgContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_callContext extends ParserRuleContext {
		public Function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_call; }
	 
		public Function_callContext() { }
		public void copyFrom(Function_callContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallContext extends Function_callContext {
		public TerminalNode VAR() { return getToken(TugaParser.VAR, 0); }
		public TerminalNode LPAREN() { return getToken(TugaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(TugaParser.RPAREN, 0); }
		public Expr_listContext expr_list() {
			return getRuleContext(Expr_listContext.class,0);
		}
		public FunctionCallContext(Function_callContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_callContext function_call() throws RecognitionException {
		Function_callContext _localctx = new Function_callContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_function_call);
		int _la;
		try {
			_localctx = new FunctionCallContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(VAR);
			setState(91);
			match(LPAREN);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 291521449984L) != 0)) {
				{
				setState(92);
				expr_list();
				}
			}

			setState(95);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr_listContext extends ParserRuleContext {
		public Expr_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_list; }
	 
		public Expr_listContext() { }
		public void copyFrom(Expr_listContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends Expr_listContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExpressionContext(Expr_listContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionsContext extends Expr_listContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_listContext expr_list() {
			return getRuleContext(Expr_listContext.class,0);
		}
		public ExpressionsContext(Expr_listContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterExpressions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitExpressions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitExpressions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_listContext expr_list() throws RecognitionException {
		Expr_listContext _localctx = new Expr_listContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expr_list);
		try {
			setState(102);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new ExpressionsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				expr(0);
				setState(98);
				match(T__1);
				setState(99);
				expr_list();
				}
				break;
			case 2:
				_localctx = new ExpressionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(101);
				expr(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstContext extends ParserRuleContext {
		public PrintContext print() {
			return getRuleContext(PrintContext.class,0);
		}
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public IfContext if_() {
			return getRuleContext(IfContext.class,0);
		}
		public IfelseContext ifelse() {
			return getRuleContext(IfelseContext.class,0);
		}
		public WhileContext while_() {
			return getRuleContext(WhileContext.class,0);
		}
		public Function_call_instContext function_call_inst() {
			return getRuleContext(Function_call_instContext.class,0);
		}
		public ReturnContext return_() {
			return getRuleContext(ReturnContext.class,0);
		}
		public EmptyContext empty() {
			return getRuleContext(EmptyContext.class,0);
		}
		public InstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitInst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitInst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstContext inst() throws RecognitionException {
		InstContext _localctx = new InstContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_inst);
		try {
			setState(113);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				print();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				assign();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(106);
				scope();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(107);
				if_();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(108);
				ifelse();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(109);
				while_();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(110);
				function_call_inst();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(111);
				return_();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(112);
				empty();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrintContext extends ParserRuleContext {
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
	 
		public PrintContext() { }
		public void copyFrom(PrintContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InstPrintContext extends PrintContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode END_INST() { return getToken(TugaParser.END_INST, 0); }
		public InstPrintContext(PrintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterInstPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitInstPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitInstPrint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_print);
		try {
			_localctx = new InstPrintContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(T__3);
			setState(116);
			expr(0);
			setState(117);
			match(END_INST);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignContext extends ParserRuleContext {
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
	 
		public AssignContext() { }
		public void copyFrom(AssignContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InstAssignContext extends AssignContext {
		public TerminalNode VAR() { return getToken(TugaParser.VAR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode END_INST() { return getToken(TugaParser.END_INST, 0); }
		public InstAssignContext(AssignContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterInstAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitInstAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitInstAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_assign);
		try {
			_localctx = new InstAssignContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(VAR);
			setState(120);
			match(T__4);
			setState(121);
			expr(0);
			setState(122);
			match(END_INST);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScopeContext extends ParserRuleContext {
		public ScopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scope; }
	 
		public ScopeContext() { }
		public void copyFrom(ScopeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InstScopeContext extends ScopeContext {
		public List<Declare_varContext> declare_var() {
			return getRuleContexts(Declare_varContext.class);
		}
		public Declare_varContext declare_var(int i) {
			return getRuleContext(Declare_varContext.class,i);
		}
		public List<InstContext> inst() {
			return getRuleContexts(InstContext.class);
		}
		public InstContext inst(int i) {
			return getRuleContext(InstContext.class,i);
		}
		public InstScopeContext(ScopeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterInstScope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitInstScope(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitInstScope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScopeContext scope() throws RecognitionException {
		ScopeContext _localctx = new ScopeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_scope);
		int _la;
		try {
			int _alt;
			_localctx = new InstScopeContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(T__5);
			setState(128);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(125);
					declare_var();
					}
					} 
				}
				setState(130);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 275146345808L) != 0)) {
				{
				{
				setState(131);
				inst();
				}
				}
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(137);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScopeOrInstContext extends ParserRuleContext {
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public InstContext inst() {
			return getRuleContext(InstContext.class,0);
		}
		public ScopeOrInstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scopeOrInst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterScopeOrInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitScopeOrInst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitScopeOrInst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScopeOrInstContext scopeOrInst() throws RecognitionException {
		ScopeOrInstContext _localctx = new ScopeOrInstContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_scopeOrInst);
		try {
			setState(141);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(139);
				scope();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(140);
				inst();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfContext extends ParserRuleContext {
		public IfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if; }
	 
		public IfContext() { }
		public void copyFrom(IfContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InstIfContext extends IfContext {
		public TerminalNode LPAREN() { return getToken(TugaParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(TugaParser.RPAREN, 0); }
		public ScopeOrInstContext scopeOrInst() {
			return getRuleContext(ScopeOrInstContext.class,0);
		}
		public InstIfContext(IfContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterInstIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitInstIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitInstIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfContext if_() throws RecognitionException {
		IfContext _localctx = new IfContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_if);
		try {
			_localctx = new InstIfContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(T__7);
			setState(144);
			match(LPAREN);
			setState(145);
			expr(0);
			setState(146);
			match(RPAREN);
			setState(147);
			scopeOrInst();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfelseContext extends ParserRuleContext {
		public IfelseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifelse; }
	 
		public IfelseContext() { }
		public void copyFrom(IfelseContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InstIfElseContext extends IfelseContext {
		public TerminalNode LPAREN() { return getToken(TugaParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(TugaParser.RPAREN, 0); }
		public List<ScopeOrInstContext> scopeOrInst() {
			return getRuleContexts(ScopeOrInstContext.class);
		}
		public ScopeOrInstContext scopeOrInst(int i) {
			return getRuleContext(ScopeOrInstContext.class,i);
		}
		public InstIfElseContext(IfelseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterInstIfElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitInstIfElse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitInstIfElse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfelseContext ifelse() throws RecognitionException {
		IfelseContext _localctx = new IfelseContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_ifelse);
		try {
			_localctx = new InstIfElseContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(T__7);
			setState(150);
			match(LPAREN);
			setState(151);
			expr(0);
			setState(152);
			match(RPAREN);
			setState(153);
			scopeOrInst();
			setState(154);
			match(T__8);
			setState(155);
			scopeOrInst();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileContext extends ParserRuleContext {
		public WhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while; }
	 
		public WhileContext() { }
		public void copyFrom(WhileContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InstWhileContext extends WhileContext {
		public TerminalNode LPAREN() { return getToken(TugaParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(TugaParser.RPAREN, 0); }
		public ScopeOrInstContext scopeOrInst() {
			return getRuleContext(ScopeOrInstContext.class,0);
		}
		public InstWhileContext(WhileContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterInstWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitInstWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitInstWhile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileContext while_() throws RecognitionException {
		WhileContext _localctx = new WhileContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_while);
		try {
			_localctx = new InstWhileContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(T__9);
			setState(158);
			match(LPAREN);
			setState(159);
			expr(0);
			setState(160);
			match(RPAREN);
			setState(161);
			scopeOrInst();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_call_instContext extends ParserRuleContext {
		public Function_call_instContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_call_inst; }
	 
		public Function_call_instContext() { }
		public void copyFrom(Function_call_instContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InstFunctionCallContext extends Function_call_instContext {
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public TerminalNode END_INST() { return getToken(TugaParser.END_INST, 0); }
		public InstFunctionCallContext(Function_call_instContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterInstFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitInstFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitInstFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_call_instContext function_call_inst() throws RecognitionException {
		Function_call_instContext _localctx = new Function_call_instContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_function_call_inst);
		try {
			_localctx = new InstFunctionCallContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			function_call();
			setState(164);
			match(END_INST);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnContext extends ParserRuleContext {
		public ReturnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return; }
	 
		public ReturnContext() { }
		public void copyFrom(ReturnContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InstReturnContext extends ReturnContext {
		public TerminalNode END_INST() { return getToken(TugaParser.END_INST, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public InstReturnContext(ReturnContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterInstReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitInstReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitInstReturn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnContext return_() throws RecognitionException {
		ReturnContext _localctx = new ReturnContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_return);
		int _la;
		try {
			_localctx = new InstReturnContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(T__10);
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 291521449984L) != 0)) {
				{
				setState(167);
				expr(0);
				}
			}

			setState(170);
			match(END_INST);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EmptyContext extends ParserRuleContext {
		public EmptyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_empty; }
	 
		public EmptyContext() { }
		public void copyFrom(EmptyContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InstEmptyContext extends EmptyContext {
		public TerminalNode END_INST() { return getToken(TugaParser.END_INST, 0); }
		public InstEmptyContext(EmptyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterInstEmpty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitInstEmpty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitInstEmpty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmptyContext empty() throws RecognitionException {
		EmptyContext _localctx = new EmptyContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_empty);
		try {
			_localctx = new InstEmptyContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(END_INST);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualsOpContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode EQUALS() { return getToken(TugaParser.EQUALS, 0); }
		public TerminalNode N_EQUALS() { return getToken(TugaParser.N_EQUALS, 0); }
		public EqualsOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterEqualsOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitEqualsOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitEqualsOp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrOpContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OR() { return getToken(TugaParser.OR, 0); }
		public OrOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterOrOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitOrOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitOrOp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NegateOpContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SUB() { return getToken(TugaParser.SUB, 0); }
		public NegateOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterNegateOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitNegateOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitNegateOp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SumSubOpContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode SUM() { return getToken(TugaParser.SUM, 0); }
		public TerminalNode SUB() { return getToken(TugaParser.SUB, 0); }
		public SumSubOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterSumSubOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitSumSubOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitSumSubOp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicNegateOpContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode NOT() { return getToken(TugaParser.NOT, 0); }
		public LogicNegateOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterLogicNegateOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitLogicNegateOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitLogicNegateOp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LiteralExprContext extends ExprContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarExprContext extends ExprContext {
		public TerminalNode VAR() { return getToken(TugaParser.VAR, 0); }
		public VarExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterVarExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitVarExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitVarExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelOpContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LESS() { return getToken(TugaParser.LESS, 0); }
		public TerminalNode GREATER() { return getToken(TugaParser.GREATER, 0); }
		public TerminalNode LESS_EQ() { return getToken(TugaParser.LESS_EQ, 0); }
		public TerminalNode GREATER_EQ() { return getToken(TugaParser.GREATER_EQ, 0); }
		public RelOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterRelOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitRelOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitRelOp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenExprContext extends ExprContext {
		public TerminalNode LPAREN() { return getToken(TugaParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(TugaParser.RPAREN, 0); }
		public ParenExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitParenExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FuncExprContext extends ExprContext {
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public FuncExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterFuncExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitFuncExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitFuncExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultDivModOpContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MULT() { return getToken(TugaParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(TugaParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(TugaParser.MOD, 0); }
		public MultDivModOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterMultDivModOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitMultDivModOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitMultDivModOp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndOpContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode AND() { return getToken(TugaParser.AND, 0); }
		public AndOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterAndOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitAndOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitAndOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(175);
				match(LPAREN);
				setState(176);
				expr(0);
				setState(177);
				match(RPAREN);
				}
				break;
			case 2:
				{
				_localctx = new NegateOpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(179);
				((NegateOpContext)_localctx).op = match(SUB);
				setState(180);
				expr(11);
				}
				break;
			case 3:
				{
				_localctx = new LogicNegateOpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(181);
				((LogicNegateOpContext)_localctx).op = match(NOT);
				setState(182);
				expr(10);
				}
				break;
			case 4:
				{
				_localctx = new LiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(183);
				literal();
				}
				break;
			case 5:
				{
				_localctx = new VarExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(184);
				match(VAR);
				}
				break;
			case 6:
				{
				_localctx = new FuncExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(185);
				function_call();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(208);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(206);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						_localctx = new MultDivModOpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(188);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(189);
						((MultDivModOpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 229376L) != 0)) ) {
							((MultDivModOpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(190);
						expr(10);
						}
						break;
					case 2:
						{
						_localctx = new SumSubOpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(191);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(192);
						((SumSubOpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==SUM || _la==SUB) ) {
							((SumSubOpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(193);
						expr(9);
						}
						break;
					case 3:
						{
						_localctx = new RelOpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(194);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(195);
						((RelOpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 15728640L) != 0)) ) {
							((RelOpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(196);
						expr(8);
						}
						break;
					case 4:
						{
						_localctx = new EqualsOpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(197);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(198);
						((EqualsOpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQUALS || _la==N_EQUALS) ) {
							((EqualsOpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(199);
						expr(7);
						}
						break;
					case 5:
						{
						_localctx = new AndOpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(200);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(201);
						((AndOpContext)_localctx).op = match(AND);
						setState(202);
						expr(6);
						}
						break;
					case 6:
						{
						_localctx = new OrOpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(203);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(204);
						((OrOpContext)_localctx).op = match(OR);
						setState(205);
						expr(5);
						}
						break;
					}
					} 
				}
				setState(210);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	 
		public LiteralContext() { }
		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TrueContext extends LiteralContext {
		public TerminalNode TRUE() { return getToken(TugaParser.TRUE, 0); }
		public TrueContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterTrue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitTrue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitTrue(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends LiteralContext {
		public TerminalNode STRING() { return getToken(TugaParser.STRING, 0); }
		public StringContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FalseContext extends LiteralContext {
		public TerminalNode FALSE() { return getToken(TugaParser.FALSE, 0); }
		public FalseContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitFalse(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DoubleContext extends LiteralContext {
		public TerminalNode DOUBLE() { return getToken(TugaParser.DOUBLE, 0); }
		public DoubleContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterDouble(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitDouble(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitDouble(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntContext extends LiteralContext {
		public TerminalNode INT() { return getToken(TugaParser.INT, 0); }
		public IntContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_literal);
		try {
			setState(216);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				_localctx = new IntContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(211);
				match(INT);
				}
				break;
			case DOUBLE:
				_localctx = new DoubleContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(212);
				match(DOUBLE);
				}
				break;
			case STRING:
				_localctx = new StringContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(213);
				match(STRING);
				}
				break;
			case TRUE:
				_localctx = new TrueContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(214);
				match(TRUE);
				}
				break;
			case FALSE:
				_localctx = new FalseContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(215);
				match(FALSE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 19:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		case 4:
			return precpred(_ctx, 5);
		case 5:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001)\u00db\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0001\u0000\u0005\u0000"+
		",\b\u0000\n\u0000\f\u0000/\t\u0000\u0001\u0000\u0004\u00002\b\u0000\u000b"+
		"\u0000\f\u00003\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0003\u0002A\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0003\u0003G\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u0003L\b\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004U\b\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0003"+
		"\u0006^\b\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0003\u0007g\b\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\br\b\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0005\u000b\u007f\b\u000b\n\u000b\f\u000b\u0082\t\u000b"+
		"\u0001\u000b\u0005\u000b\u0085\b\u000b\n\u000b\f\u000b\u0088\t\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0003\f\u008e\b\f\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0011\u0001\u0011\u0003\u0011\u00a9\b\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u00bb\b\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013"+
		"\u00cf\b\u0013\n\u0013\f\u0013\u00d2\t\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u00d9\b\u0014\u0001\u0014\u0000"+
		"\u0001&\u0015\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \"$&(\u0000\u0005\u0001\u0000\"%\u0001\u0000"+
		"\u000f\u0011\u0001\u0000\u0012\u0013\u0001\u0000\u0014\u0017\u0001\u0000"+
		"\u0018\u0019\u00e8\u0000-\u0001\u0000\u0000\u0000\u00027\u0001\u0000\u0000"+
		"\u0000\u0004@\u0001\u0000\u0000\u0000\u0006B\u0001\u0000\u0000\u0000\b"+
		"T\u0001\u0000\u0000\u0000\nV\u0001\u0000\u0000\u0000\fZ\u0001\u0000\u0000"+
		"\u0000\u000ef\u0001\u0000\u0000\u0000\u0010q\u0001\u0000\u0000\u0000\u0012"+
		"s\u0001\u0000\u0000\u0000\u0014w\u0001\u0000\u0000\u0000\u0016|\u0001"+
		"\u0000\u0000\u0000\u0018\u008d\u0001\u0000\u0000\u0000\u001a\u008f\u0001"+
		"\u0000\u0000\u0000\u001c\u0095\u0001\u0000\u0000\u0000\u001e\u009d\u0001"+
		"\u0000\u0000\u0000 \u00a3\u0001\u0000\u0000\u0000\"\u00a6\u0001\u0000"+
		"\u0000\u0000$\u00ac\u0001\u0000\u0000\u0000&\u00ba\u0001\u0000\u0000\u0000"+
		"(\u00d8\u0001\u0000\u0000\u0000*,\u0003\u0002\u0001\u0000+*\u0001\u0000"+
		"\u0000\u0000,/\u0001\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000-.\u0001"+
		"\u0000\u0000\u0000.1\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000\u0000"+
		"02\u0003\u0006\u0003\u000010\u0001\u0000\u0000\u000023\u0001\u0000\u0000"+
		"\u000031\u0001\u0000\u0000\u000034\u0001\u0000\u0000\u000045\u0001\u0000"+
		"\u0000\u000056\u0005\u0000\u0000\u00016\u0001\u0001\u0000\u0000\u0000"+
		"78\u0003\u0004\u0002\u000089\u0005\u0001\u0000\u00009:\u0007\u0000\u0000"+
		"\u0000:;\u0005\u001c\u0000\u0000;\u0003\u0001\u0000\u0000\u0000<=\u0005"+
		"&\u0000\u0000=>\u0005\u0002\u0000\u0000>A\u0003\u0004\u0002\u0000?A\u0005"+
		"&\u0000\u0000@<\u0001\u0000\u0000\u0000@?\u0001\u0000\u0000\u0000A\u0005"+
		"\u0001\u0000\u0000\u0000BC\u0005\u0003\u0000\u0000CD\u0005&\u0000\u0000"+
		"DF\u0005\f\u0000\u0000EG\u0003\b\u0004\u0000FE\u0001\u0000\u0000\u0000"+
		"FG\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000\u0000HK\u0005\r\u0000\u0000"+
		"IJ\u0005\u0001\u0000\u0000JL\u0007\u0000\u0000\u0000KI\u0001\u0000\u0000"+
		"\u0000KL\u0001\u0000\u0000\u0000LM\u0001\u0000\u0000\u0000MN\u0003\u0016"+
		"\u000b\u0000N\u0007\u0001\u0000\u0000\u0000OP\u0003\n\u0005\u0000PQ\u0005"+
		"\u0002\u0000\u0000QR\u0003\b\u0004\u0000RU\u0001\u0000\u0000\u0000SU\u0003"+
		"\n\u0005\u0000TO\u0001\u0000\u0000\u0000TS\u0001\u0000\u0000\u0000U\t"+
		"\u0001\u0000\u0000\u0000VW\u0005&\u0000\u0000WX\u0005\u0001\u0000\u0000"+
		"XY\u0007\u0000\u0000\u0000Y\u000b\u0001\u0000\u0000\u0000Z[\u0005&\u0000"+
		"\u0000[]\u0005\f\u0000\u0000\\^\u0003\u000e\u0007\u0000]\\\u0001\u0000"+
		"\u0000\u0000]^\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_`\u0005"+
		"\r\u0000\u0000`\r\u0001\u0000\u0000\u0000ab\u0003&\u0013\u0000bc\u0005"+
		"\u0002\u0000\u0000cd\u0003\u000e\u0007\u0000dg\u0001\u0000\u0000\u0000"+
		"eg\u0003&\u0013\u0000fa\u0001\u0000\u0000\u0000fe\u0001\u0000\u0000\u0000"+
		"g\u000f\u0001\u0000\u0000\u0000hr\u0003\u0012\t\u0000ir\u0003\u0014\n"+
		"\u0000jr\u0003\u0016\u000b\u0000kr\u0003\u001a\r\u0000lr\u0003\u001c\u000e"+
		"\u0000mr\u0003\u001e\u000f\u0000nr\u0003 \u0010\u0000or\u0003\"\u0011"+
		"\u0000pr\u0003$\u0012\u0000qh\u0001\u0000\u0000\u0000qi\u0001\u0000\u0000"+
		"\u0000qj\u0001\u0000\u0000\u0000qk\u0001\u0000\u0000\u0000ql\u0001\u0000"+
		"\u0000\u0000qm\u0001\u0000\u0000\u0000qn\u0001\u0000\u0000\u0000qo\u0001"+
		"\u0000\u0000\u0000qp\u0001\u0000\u0000\u0000r\u0011\u0001\u0000\u0000"+
		"\u0000st\u0005\u0004\u0000\u0000tu\u0003&\u0013\u0000uv\u0005\u001c\u0000"+
		"\u0000v\u0013\u0001\u0000\u0000\u0000wx\u0005&\u0000\u0000xy\u0005\u0005"+
		"\u0000\u0000yz\u0003&\u0013\u0000z{\u0005\u001c\u0000\u0000{\u0015\u0001"+
		"\u0000\u0000\u0000|\u0080\u0005\u0006\u0000\u0000}\u007f\u0003\u0002\u0001"+
		"\u0000~}\u0001\u0000\u0000\u0000\u007f\u0082\u0001\u0000\u0000\u0000\u0080"+
		"~\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0086"+
		"\u0001\u0000\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0083\u0085"+
		"\u0003\u0010\b\u0000\u0084\u0083\u0001\u0000\u0000\u0000\u0085\u0088\u0001"+
		"\u0000\u0000\u0000\u0086\u0084\u0001\u0000\u0000\u0000\u0086\u0087\u0001"+
		"\u0000\u0000\u0000\u0087\u0089\u0001\u0000\u0000\u0000\u0088\u0086\u0001"+
		"\u0000\u0000\u0000\u0089\u008a\u0005\u0007\u0000\u0000\u008a\u0017\u0001"+
		"\u0000\u0000\u0000\u008b\u008e\u0003\u0016\u000b\u0000\u008c\u008e\u0003"+
		"\u0010\b\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008d\u008c\u0001\u0000"+
		"\u0000\u0000\u008e\u0019\u0001\u0000\u0000\u0000\u008f\u0090\u0005\b\u0000"+
		"\u0000\u0090\u0091\u0005\f\u0000\u0000\u0091\u0092\u0003&\u0013\u0000"+
		"\u0092\u0093\u0005\r\u0000\u0000\u0093\u0094\u0003\u0018\f\u0000\u0094"+
		"\u001b\u0001\u0000\u0000\u0000\u0095\u0096\u0005\b\u0000\u0000\u0096\u0097"+
		"\u0005\f\u0000\u0000\u0097\u0098\u0003&\u0013\u0000\u0098\u0099\u0005"+
		"\r\u0000\u0000\u0099\u009a\u0003\u0018\f\u0000\u009a\u009b\u0005\t\u0000"+
		"\u0000\u009b\u009c\u0003\u0018\f\u0000\u009c\u001d\u0001\u0000\u0000\u0000"+
		"\u009d\u009e\u0005\n\u0000\u0000\u009e\u009f\u0005\f\u0000\u0000\u009f"+
		"\u00a0\u0003&\u0013\u0000\u00a0\u00a1\u0005\r\u0000\u0000\u00a1\u00a2"+
		"\u0003\u0018\f\u0000\u00a2\u001f\u0001\u0000\u0000\u0000\u00a3\u00a4\u0003"+
		"\f\u0006\u0000\u00a4\u00a5\u0005\u001c\u0000\u0000\u00a5!\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a8\u0005\u000b\u0000\u0000\u00a7\u00a9\u0003&\u0013"+
		"\u0000\u00a8\u00a7\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000"+
		"\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u00ab\u0005\u001c\u0000"+
		"\u0000\u00ab#\u0001\u0000\u0000\u0000\u00ac\u00ad\u0005\u001c\u0000\u0000"+
		"\u00ad%\u0001\u0000\u0000\u0000\u00ae\u00af\u0006\u0013\uffff\uffff\u0000"+
		"\u00af\u00b0\u0005\f\u0000\u0000\u00b0\u00b1\u0003&\u0013\u0000\u00b1"+
		"\u00b2\u0005\r\u0000\u0000\u00b2\u00bb\u0001\u0000\u0000\u0000\u00b3\u00b4"+
		"\u0005\u0013\u0000\u0000\u00b4\u00bb\u0003&\u0013\u000b\u00b5\u00b6\u0005"+
		"\u000e\u0000\u0000\u00b6\u00bb\u0003&\u0013\n\u00b7\u00bb\u0003(\u0014"+
		"\u0000\u00b8\u00bb\u0005&\u0000\u0000\u00b9\u00bb\u0003\f\u0006\u0000"+
		"\u00ba\u00ae\u0001\u0000\u0000\u0000\u00ba\u00b3\u0001\u0000\u0000\u0000"+
		"\u00ba\u00b5\u0001\u0000\u0000\u0000\u00ba\u00b7\u0001\u0000\u0000\u0000"+
		"\u00ba\u00b8\u0001\u0000\u0000\u0000\u00ba\u00b9\u0001\u0000\u0000\u0000"+
		"\u00bb\u00d0\u0001\u0000\u0000\u0000\u00bc\u00bd\n\t\u0000\u0000\u00bd"+
		"\u00be\u0007\u0001\u0000\u0000\u00be\u00cf\u0003&\u0013\n\u00bf\u00c0"+
		"\n\b\u0000\u0000\u00c0\u00c1\u0007\u0002\u0000\u0000\u00c1\u00cf\u0003"+
		"&\u0013\t\u00c2\u00c3\n\u0007\u0000\u0000\u00c3\u00c4\u0007\u0003\u0000"+
		"\u0000\u00c4\u00cf\u0003&\u0013\b\u00c5\u00c6\n\u0006\u0000\u0000\u00c6"+
		"\u00c7\u0007\u0004\u0000\u0000\u00c7\u00cf\u0003&\u0013\u0007\u00c8\u00c9"+
		"\n\u0005\u0000\u0000\u00c9\u00ca\u0005\u001a\u0000\u0000\u00ca\u00cf\u0003"+
		"&\u0013\u0006\u00cb\u00cc\n\u0004\u0000\u0000\u00cc\u00cd\u0005\u001b"+
		"\u0000\u0000\u00cd\u00cf\u0003&\u0013\u0005\u00ce\u00bc\u0001\u0000\u0000"+
		"\u0000\u00ce\u00bf\u0001\u0000\u0000\u0000\u00ce\u00c2\u0001\u0000\u0000"+
		"\u0000\u00ce\u00c5\u0001\u0000\u0000\u0000\u00ce\u00c8\u0001\u0000\u0000"+
		"\u0000\u00ce\u00cb\u0001\u0000\u0000\u0000\u00cf\u00d2\u0001\u0000\u0000"+
		"\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000\u00d0\u00d1\u0001\u0000\u0000"+
		"\u0000\u00d1\'\u0001\u0000\u0000\u0000\u00d2\u00d0\u0001\u0000\u0000\u0000"+
		"\u00d3\u00d9\u0005\u001d\u0000\u0000\u00d4\u00d9\u0005\u001e\u0000\u0000"+
		"\u00d5\u00d9\u0005\u001f\u0000\u0000\u00d6\u00d9\u0005 \u0000\u0000\u00d7"+
		"\u00d9\u0005!\u0000\u0000\u00d8\u00d3\u0001\u0000\u0000\u0000\u00d8\u00d4"+
		"\u0001\u0000\u0000\u0000\u00d8\u00d5\u0001\u0000\u0000\u0000\u00d8\u00d6"+
		"\u0001\u0000\u0000\u0000\u00d8\u00d7\u0001\u0000\u0000\u0000\u00d9)\u0001"+
		"\u0000\u0000\u0000\u0011-3@FKT]fq\u0080\u0086\u008d\u00a8\u00ba\u00ce"+
		"\u00d0\u00d8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}