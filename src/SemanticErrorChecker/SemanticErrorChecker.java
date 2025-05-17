package SemanticErrorChecker;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.*;

import java.util.*;

import Tuga.*;
import Types.*;
import MyErrorListeners.*;
import Types.Symbols.*;

public class SemanticErrorChecker extends TugaBaseVisitor<TugaTypes>
{
    private static final String MAIN = "principal";

    private ParseTreeProperty<TugaTypes> types;
    private ArrayList<MyErrorListener> listeners;
    private TugaTypes currentVisitingVarType;
    private HashMap<String, TugaTypes> varTypes;

    private boolean hasMain;
    private boolean initialFunctionDeclaration;
    private HashMap<String, FunctionSymbol> functions;  // symbol table
    private Scope outerScope;
    private FunctionSymbol currFunction;
    private HashMap<String, FunctionSymbol> functionsFound;
    private boolean hasReturn;

    private Stack<FunctionSymbol> currFunctionCall;
    private Stack<Integer> currArgNum;

    public SemanticErrorChecker(ParseTreeProperty<TugaTypes> types, HashMap<String, TugaTypes> varTypes, HashMap<String, FunctionSymbol> functions)
    {
        this.types = types;
        this.listeners = new ArrayList<MyErrorListener>();
        this.currentVisitingVarType = null;
        this.varTypes = varTypes;

        this.hasMain = false;
        this.initialFunctionDeclaration = true;
        this.functions = functions;
        this.outerScope = new Scope();
        this.functionsFound = new HashMap<String, FunctionSymbol>();

        this.currFunctionCall = new Stack<FunctionSymbol>();
        this.currArgNum = new Stack<Integer>();
    }

    // verifica se o parsed program contem a funcao main(principal) e da raise em um erro caso nao tenha
    @Override
    public TugaTypes visit(ParseTree tree)
    {
        TugaTypes result = super.visit(tree);
        if (!(tree instanceof TugaParser.TugaContext))
            return result;
        TugaParser.TugaContext ctx = (TugaParser.TugaContext) tree;

        if (!hasMain)
            raiseError(ctx.stop.getLine(), 0, "falta funcao principal()");

        return result;
    }

    @Override
    public TugaTypes visitFunctionDecl(TugaParser.FunctionDeclContext ctx)
    {
        if (initialFunctionDeclaration)
        {
            // adiciona todas as funcoes ao scope raiz
            for (FunctionSymbol fn : functions.values())
            {
                if (outerScope.contains(fn.name()))
                {
                    String msg = "'" + fn.name() + "'" + " ja foi declarado";
                    raiseError(fn.line(), 0, msg);
                }
                else
                {
                    outerScope.addSymbol(fn);
                }
            }
            initialFunctionDeclaration = false;
        }
        currFunction = functions.get(ctx.VAR().getText());

        if (functionsFound.containsKey(currFunction.name()))
        {
            String msg = "'" + currFunction.name() + "' ja foi declarado";
            raiseError(ctx.start.getLine(), ctx.start.getCharPositionInLine(), msg);
        }
        else
        {
            functionsFound.put(currFunction.name(), currFunction);
        }

        Scope scope = new Scope(outerScope);
        outerScope = scope;

        hasReturn = false;
        if (ctx.arg_list() != null)
            visit(ctx.arg_list());
        visit(ctx.scope());
        if (ctx.VAR().getText().equals(MAIN))
            this.hasMain = true;
        if (!hasReturn && currFunction.type() != TugaTypes.VOID)
        {
            String msg = "funcao sem retorno";
            raiseError(ctx.start.getLine(), ctx.start.getCharPositionInLine(), msg);
        }

        outerScope = scope.parent();
        currFunction = null;
        return null;
    }

    @Override
    public TugaTypes visitDeclArg(TugaParser.DeclArgContext ctx)
    {
        String name = ctx.VAR().getText();
        TugaTypes type = antlrTypeConvert(ctx.type.getType());

        if (outerScope.contains(name))
        {
            String msg = "'" + ctx.VAR().getText() + "' ja foi declarado";
            raiseError(ctx.start.getLine(), ctx.start.getCharPositionInLine(), msg);
            return type;
        }
        outerScope.addSymbol(new VariableSymbol(name, type));

        return type;
    }

    public TugaTypes antlrTypeConvert(int type)
    {
        TugaTypes result = null;
        switch (type) {
            case TugaParser.INT_TYPE:
                result = TugaTypes.INT;
                break;
            case TugaParser.DOUBLE_TYPE:
                result = TugaTypes.DOUBLE;
                break;
            case TugaParser.STRING_TYPE:
                result = TugaTypes.STRING;
                break;
            case TugaParser.BOOLEAN_TYPE:
                result = TugaTypes.BOOLEAN;
                break;
            default:
                throw new IllegalStateException("Invalid variable type.");
        }
        return result;
    }


    @Override
    public TugaTypes visitFunctionCall(TugaParser.FunctionCallContext ctx)
    {
        if (!functions.containsKey(ctx.VAR().getText()))
        {
            String msg = "'" + ctx.VAR().getText() + "' nao foi declarado";
            raiseError(ctx.start.getLine(), ctx.start.getCharPositionInLine(), msg);
            return null;
        }

        currArgNum.push(0);
        FunctionSymbol fn = functions.get(ctx.VAR().getText());
        currFunctionCall.push(fn);
        if (ctx.expr_list() != null)
            visit(ctx.expr_list());
        currFunctionCall.pop();

        if (currArgNum.peek() != fn.argNum())
        {
            String msg = "'" + fn.name() + "' requer " + fn.argNum() + " argumentos";
            raiseError(ctx.start.getLine(), ctx.start.getCharPositionInLine(), msg);
        }
        currArgNum.pop();

        return null;
    }

    @Override
    public TugaTypes visitInstFunctionCall(TugaParser.InstFunctionCallContext ctx)
    {
        TugaTypes result = visit(ctx.function_call());

        TugaParser.FunctionCallContext fnCtx = (TugaParser.FunctionCallContext) ctx.function_call();
        String fnName = fnCtx.VAR().getText();
        // checka se esta na tabela de simbolos e se retorna algum valor
        if (functions.containsKey(fnName) && !(functions.get(fnName).type() == TugaTypes.VOID))
        {
            String msg = "valor de " + "'" + fnName + "'" + " tem de ser atribuido a uma variavel";
            raiseError(ctx.start.getLine(), ctx.start.getCharPositionInLine(), msg);
        }

        return result;
    }

    @Override
    public TugaTypes visitExpression(TugaParser.ExpressionContext ctx)
    {
        argLogic(ctx.expr());
        return null;
    }

    @Override
    public TugaTypes visitExpressions(TugaParser.ExpressionsContext ctx)
    {
        argLogic(ctx.expr());
        visit(ctx.expr_list());
        return null;
    }

    public void argLogic(TugaParser.ExprContext ctx)
    {
        int id = currArgNum.pop();
        FunctionSymbol currFn = currFunctionCall.peek();
        currArgNum.push(id + 1);
        if (id >= currFn.argNum())
            return;

        TugaTypes expectedType = currFn.getArg(id).type();
        TugaTypes type = visit(ctx);

        if (!expectedType.equals(type) && !(expectedType == TugaTypes.DOUBLE && type == TugaTypes.INT))
        {
            String msg = "'" + getOriginalText(ctx) + "' devia ser do tipo " + expectedType;
            raiseError(ctx.start.getLine(), ctx.start.getCharPositionInLine(), msg);
        }
    }

    @Override
    public TugaTypes visitFuncExpr(TugaParser.FuncExprContext ctx)
    {
        visit(ctx.function_call());

        TugaParser.FunctionCallContext fnCtx = (TugaParser.FunctionCallContext)ctx.function_call();
        String fnName = fnCtx.VAR().getText();

        FunctionSymbol fn = functions.get(fnName);
        if (fn == null)
            return TugaTypes.ERROR;
        TugaTypes result = functions.get(fnName).type();

        types.put(ctx, result);
        return result;
    }

    @Override
    public TugaTypes visitInstReturn(TugaParser.InstReturnContext ctx)
    {
        hasReturn = true;
        TugaTypes exprType = TugaTypes.VOID;
        if (ctx.expr() != null)
            exprType = visit(ctx.expr());

        if (currFunction.type() != exprType && !(currFunction.type() == TugaTypes.DOUBLE && exprType == TugaTypes.INT))
        {
            String msg = "funcao de tipo " + currFunction.type() +
                         " nao pode retornar uma expressao do tipo " + exprType;
            raiseError(ctx.start.getLine(), ctx.start.getCharPositionInLine(), msg);
        }

        return exprType;
    }

    @Override
    public TugaTypes visitInstAssign(TugaParser.InstAssignContext ctx)
    {
        String var = ctx.VAR().getText();
        // if (!this.varTypes.containsKey(var))
        if (!outerScope.contains(var) || (outerScope.contains(var) && (outerScope.getSymbol(var) instanceof FunctionSymbol)))
        {
            String msg = "'" + ctx.VAR().getText() + "'" + " nao eh variavel";
            raiseError(ctx.start.getLine(), ctx.start.getCharPositionInLine(), msg);
            types.put(ctx, TugaTypes.ERROR);
            return TugaTypes.ERROR;
        }

        final String operator = "<-";
        TugaTypes varType = this.outerScope.getSymbol(var).type();
        TugaTypes exprType = visit(ctx.expr());

        TugaTypes result = TugaTypes.ERROR;

        if (varType == TugaTypes.INT && exprType == TugaTypes.INT)
            result = TugaTypes.INT;
        else if (varType == TugaTypes.DOUBLE && (exprType == TugaTypes.INT || exprType == TugaTypes.DOUBLE))
            result = TugaTypes.DOUBLE;
        else if (varType == TugaTypes.BOOLEAN && exprType == TugaTypes.BOOLEAN)
            result = TugaTypes.BOOLEAN;
        else if (varType == TugaTypes.STRING && exprType == TugaTypes.STRING)
            result = TugaTypes.STRING;
        else if (exprType == TugaTypes.ERROR)
        {
            types.put(ctx, TugaTypes.ERROR);
            return TugaTypes.ERROR;
        }

        if (result == TugaTypes.ERROR)
        {
            types.put(ctx, TugaTypes.ERROR);
            TypeError(ctx, operator, varType, exprType);
        }

        return result;
    }

    @Override
    public TugaTypes visitInstIf(TugaParser.InstIfContext ctx)
    {
        final String operator = "se";
        TugaTypes conditonType = visit(ctx.expr());

        if (conditonType == TugaTypes.ERROR)
        {
            types.put(ctx, TugaTypes.ERROR);
            conditonType = TugaTypes.ERROR;
        }
        else if (conditonType != TugaTypes.BOOLEAN)
        {
            ExpressionTypeError(ctx, operator, TugaTypes.BOOLEAN);
            types.put(ctx, TugaTypes.ERROR);
            conditonType = TugaTypes.ERROR;
        }

        visit(ctx.scopeOrInst());
        return conditonType;
    }

    @Override
    public TugaTypes visitInstIfElse(TugaParser.InstIfElseContext ctx)
    {
        final String operator = "se";
        TugaTypes conditonType = visit(ctx.expr());

        if (conditonType == TugaTypes.ERROR)
        {
            types.put(ctx, TugaTypes.ERROR);
            conditonType = TugaTypes.ERROR;
        }
        else if (conditonType != TugaTypes.BOOLEAN)
        {
            ExpressionTypeError(ctx, operator, TugaTypes.BOOLEAN);
            types.put(ctx, TugaTypes.ERROR);
            conditonType = TugaTypes.ERROR;
        }

        visit(ctx.scopeOrInst(0));
        visit(ctx.scopeOrInst(1));
        return conditonType;
    }

    @Override
    public TugaTypes visitInstWhile(TugaParser.InstWhileContext ctx)
    {
        final String operator = "enquanto";
        TugaTypes conditonType = visit(ctx.expr());

        if (conditonType == TugaTypes.ERROR)
        {
            types.put(ctx, TugaTypes.ERROR);
            conditonType = TugaTypes.ERROR;
        }
        else if (conditonType != TugaTypes.BOOLEAN)
        {
            ExpressionTypeError(ctx, operator, TugaTypes.BOOLEAN);
            types.put(ctx, TugaTypes.ERROR);
            conditonType = TugaTypes.ERROR;
        }

        visit(ctx.scopeOrInst());
        return conditonType;
    }

    @Override
    public TugaTypes visitParenExpr(TugaParser.ParenExprContext ctx)
    {
        TugaTypes result = visit(ctx.expr());
        types.put(ctx, result);
        return result;
    }

    @Override
    public TugaTypes visitLiteralExpr(TugaParser.LiteralExprContext ctx)
    {
        TugaTypes result = visit(ctx.literal());
        types.put(ctx, result);
        return result;
    }

    @Override
    public TugaTypes visitNegateOp(TugaParser.NegateOpContext ctx)
    {
        String op = ctx.op.getText();
        TugaTypes type = visit(ctx.expr());

        if (type == TugaTypes.INT || type == TugaTypes.DOUBLE)
        {
            types.put(ctx, type);
            return type;
        }

        setError(ctx, op, type);
        return TugaTypes.ERROR;
    }

    @Override
    public TugaTypes visitLogicNegateOp(TugaParser.LogicNegateOpContext ctx)
    {
        String op = ctx.op.getText();
        TugaTypes type = visit(ctx.expr());

        if (type == TugaTypes.BOOLEAN)
        {
            types.put(ctx, type);
            return type;
        }

        setError(ctx, op, type);
        return TugaTypes.ERROR;
    }

    @Override
        public TugaTypes visitMultDivModOp(TugaParser.MultDivModOpContext ctx)
    {
        String op = ctx.op.getText();
        TugaTypes left = visit(ctx.expr(0));
        TugaTypes right = visit(ctx.expr(1));

        if (!left.isNumeric() || !right.isNumeric())
        {
            setError(ctx, op, left, right);
            return TugaTypes.ERROR;
        }

        if (left == TugaTypes.INT && right == TugaTypes.INT)
        {
            types.put(ctx, TugaTypes.INT);
            return TugaTypes.INT;
        }

        if (ctx.op.getType() == TugaParser.MOD)
        {
            setError(ctx, op, left, right);
            return TugaTypes.ERROR;
        }

        types.put(ctx, TugaTypes.DOUBLE);
        return TugaTypes.DOUBLE;
    }

    @Override
    public TugaTypes visitSumSubOp(TugaParser.SumSubOpContext ctx)
    {
        String op = ctx.op.getText();
        TugaTypes left = visit(ctx.expr(0));
        TugaTypes right = visit(ctx.expr(1));

        if (ctx.op.getType() == TugaParser.SUM && (left == TugaTypes.STRING || right == TugaTypes.STRING))
        {
            types.put(ctx, TugaTypes.STRING);
            return TugaTypes.STRING;
        }

        if (!left.isNumeric() || !right.isNumeric())
        {
            setError(ctx, op, left, right);
            return TugaTypes.ERROR;
        }

        if (left == TugaTypes.DOUBLE || right == TugaTypes.DOUBLE)
        {
            types.put(ctx, TugaTypes.DOUBLE);
            return TugaTypes.DOUBLE;
        }

        types.put(ctx, TugaTypes.INT);
        return TugaTypes.INT;
    }

    @Override
    public TugaTypes visitRelOp(TugaParser.RelOpContext ctx)
    {
        String op = ctx.op.getText();
        TugaTypes left = visit(ctx.expr(0));
        TugaTypes right = visit(ctx.expr(1));

        if (!left.isNumeric() || !right.isNumeric())
        {
            setError(ctx, op, left, right);
            return TugaTypes.ERROR;
        }

        types.put(ctx, TugaTypes.BOOLEAN);
        return TugaTypes.BOOLEAN;
    }

    @Override
    public TugaTypes visitEqualsOp(TugaParser.EqualsOpContext ctx)
    {
        String op = ctx.op.getText();
        TugaTypes left = visit(ctx.expr(0));
        TugaTypes right = visit(ctx.expr(1));

        if (left.isNumeric() && right.isNumeric())
        {
            types.put(ctx, TugaTypes.BOOLEAN);
            return TugaTypes.BOOLEAN;
        }

        if (left == TugaTypes.STRING && right == TugaTypes.STRING)
        {
            types.put(ctx, TugaTypes.BOOLEAN);
            return TugaTypes.BOOLEAN;
        }

        if (left == TugaTypes.BOOLEAN && right == TugaTypes.BOOLEAN)
        {
            types.put(ctx, TugaTypes.BOOLEAN);
            return TugaTypes.BOOLEAN;
        }

        setError(ctx, op, left, right);
        return TugaTypes.ERROR;
    }

    @Override
    public TugaTypes visitAndOp(TugaParser.AndOpContext ctx)
    {
        String op = ctx.op.getText();
        TugaTypes left = visit(ctx.expr(0));
        TugaTypes right = visit(ctx.expr(1));

        if (left != TugaTypes.BOOLEAN || right != TugaTypes.BOOLEAN)
        {
            setError(ctx, op, left, right);
            return TugaTypes.ERROR;
        }

        types.put(ctx, TugaTypes.BOOLEAN);
        return TugaTypes.BOOLEAN;
    }

    @Override
    public TugaTypes visitOrOp(TugaParser.OrOpContext ctx)
    {
        String op = ctx.op.getText();
        TugaTypes left = visit(ctx.expr(0));
        TugaTypes right = visit(ctx.expr(1));

        if (left != TugaTypes.BOOLEAN || right != TugaTypes.BOOLEAN)
        {
            setError(ctx, op, left, right);
            return TugaTypes.ERROR;
        }

        types.put(ctx, TugaTypes.BOOLEAN);
        return TugaTypes.BOOLEAN;
    }

    @Override
    public TugaTypes visitDeclVar(TugaParser.DeclVarContext ctx)
    {
        switch (ctx.type.getType()) {
            case TugaParser.INT_TYPE:
                this.currentVisitingVarType = TugaTypes.INT;
                break;
            case TugaParser.DOUBLE_TYPE:
                this.currentVisitingVarType = TugaTypes.DOUBLE;
                break;
            case TugaParser.STRING_TYPE:
                this.currentVisitingVarType = TugaTypes.STRING;
                break;
            case TugaParser.BOOLEAN_TYPE:
                this.currentVisitingVarType = TugaTypes.BOOLEAN;
                break;
            default:
                throw new IllegalStateException("Invalid variable type.");
        }

        visit(ctx.variable());

        return this.currentVisitingVarType;
    }

    @Override
    public TugaTypes visitVars(TugaParser.VarsContext ctx)
    {
        if (outerScope.contains(ctx.VAR().getText()))
        {
            String msg = "'" + ctx.VAR().getText() + "' ja foi declarado";
            raiseError(ctx.start.getLine(), ctx.start.getCharPositionInLine(), msg);
        }
        else
        {
            outerScope.addSymbol(new VariableSymbol(ctx.VAR().getText(), this.currentVisitingVarType));
            if (currFunction == null)
                this.varTypes.put(ctx.VAR().getText(), this.currentVisitingVarType);
        }

        visit(ctx.variable());
        return this.currentVisitingVarType;

        // if (this.varTypes.containsKey(ctx.VAR().getText()))
        //     AlreadyDeclaredVarError(ctx, ctx.VAR().getText());
        // this.varTypes.put(ctx.VAR().getText(), this.currentVisitingVarType);
        // visit(ctx.variable());
        // return this.currentVisitingVarType;
    }

    @Override
    public TugaTypes visitVar(TugaParser.VarContext ctx)
    {
        if (outerScope.contains(ctx.VAR().getText()))
        {
            String msg = "'" + ctx.VAR().getText() + "' ja foi declarado";
            raiseError(ctx.start.getLine(), ctx.start.getCharPositionInLine(), msg);
        }
        else
        {
            outerScope.addSymbol(new VariableSymbol(ctx.VAR().getText(), this.currentVisitingVarType));
            if (currFunction == null)
                this.varTypes.put(ctx.VAR().getText(), this.currentVisitingVarType);
        }

        return this.currentVisitingVarType;

        // if (this.varTypes.containsKey(ctx.VAR().getText()))
        //    AlreadyDeclaredVarError(ctx, ctx.VAR().getText());
        // this.varTypes.put(ctx.VAR().getText(), this.currentVarType);
        // return this.currentVarType;
    }

    @Override
    public TugaTypes visitInt(TugaParser.IntContext ctx)
    {
        return TugaTypes.INT;
    }

    @Override
    public TugaTypes visitDouble(TugaParser.DoubleContext ctx)
    {
        return TugaTypes.DOUBLE;
    }

    @Override
    public TugaTypes visitString(TugaParser.StringContext ctx)
    {
        return TugaTypes.STRING;
    }

    @Override
    public TugaTypes visitTrue(TugaParser.TrueContext ctx)
    {
        return TugaTypes.BOOLEAN;
    }

    @Override
    public TugaTypes visitFalse(TugaParser.FalseContext ctx)
    {
        return TugaTypes.BOOLEAN;
    }

    @Override
    public TugaTypes visitVarExpr(TugaParser.VarExprContext ctx)
    {
        String id = ctx.VAR().getText();
        TugaTypes result = null;
        if (!outerScope.contains(id) || (outerScope.contains(id) && (outerScope.getSymbol(id) instanceof FunctionSymbol)))
        {
            String msg = "'" + ctx.VAR().getText() + "' nao eh variavel";
            raiseError(ctx.start.getLine(), ctx.start.getCharPositionInLine(), msg);
            result = TugaTypes.ERROR;
        }
        else
        {
            result = outerScope.getSymbol(id).type();
        }

        types.put(ctx, result);
        return result;

        // TugaTypes result = this.varTypes.get(ctx.VAR().getText());
        // if (result == null)
        //     UndeclaredVarError(ctx, ctx.VAR().getText());
        // types.put(ctx, result);
        // return result;
    }

    // fazer o comportamento que acontece no lexer e parser no CompileAndRun
    public void addErrorListener(MyErrorListener listener)
    {
        if (listener != null)
            this.listeners.add(listener);
        else
            throw new NullPointerException("Listener should not be null");
    }

    public void removeErrorListener()
    {
        if (!this.listeners.isEmpty())
            this.listeners = new ArrayList<MyErrorListener>();
    }

    private void UndeclaredVarError(ParserRuleContext ctx, String var)
    {
        int line = ctx.getStart().getLine();
        int charPositionInLine = ctx.getStart().getCharPositionInLine();
        // String text = "Variable \'" + ctx.ID().getText() + "\' hasn't been declared.";
        String text = "variavel \'" + var + "\' nao foi declarada";
        raiseError(line, charPositionInLine, text);
    }

    private void AlreadyDeclaredVarError(ParserRuleContext ctx, String var)
    {
        int line = ctx.getStart().getLine();
        int charPositionInLine = ctx.getStart().getCharPositionInLine();
        // String text = "Variable \'" + ctx.ID().getText() + "\' already declared.";
        String text = "variavel \'" + var + "\' ja foi declarada";
        raiseError(line, charPositionInLine, text);
    }

    private void ExpressionTypeError(ParserRuleContext node, String operator, TugaTypes type)
    {
        int line = node.getStart().getLine();
        int charPositionInLine = node.getStart().getCharPositionInLine();
        String msg = "expressao de \'" + operator + "\' nao eh do tipo " + type.toString();
        raiseError(line, charPositionInLine, msg);
    }

    private void TypeError(ParserRuleContext node, String operator, TugaTypes type)
    {
        int line = node.getStart().getLine();
        int charPositionInLine = node.getStart().getCharPositionInLine();
        raiseTypeError(line, charPositionInLine, operator, type);
    }

    private void TypeError(ParserRuleContext node, String operator, TugaTypes type1, TugaTypes type2)
    {
        int line = node.getStart().getLine();
        int charPositionInLine = node.getStart().getCharPositionInLine();
        raiseTypeError(line, charPositionInLine, operator, type1, type2);
    }

    private void raiseTypeError(int line, int charPositionInLine, String operator, TugaTypes type)
    {
        String msg = "operador \'" + operator + "\' eh invalido para o tipo " + type.toString();
        raiseError(line, charPositionInLine, msg);
    }

    private void raiseTypeError(int line, int charPositionInLine, String operator, TugaTypes type1, TugaTypes type2)
    {
        String msg = "operador \'" + operator + "\' eh invalido entre " + type1.toString() + " e " + type2.toString();
        raiseError(line, charPositionInLine, msg);
    }

    public boolean doChildrenHaveErrors(ParseTree ctx)
    {
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree child = ctx.getChild(i);
            if (this.types.get(child) == TugaTypes.ERROR && child instanceof TugaParser.ExprContext)
                return true;
        }
        return false;
    }

    private boolean isTerminalError(ParseTree node)
    {
        boolean result = this.types.get(node) == TugaTypes.ERROR &&
                         node instanceof TugaParser.ExprContext &&
                         !doChildrenHaveErrors(node);
        return result;
    }

    private void setError(ParserRuleContext node, String operator, TugaTypes type)
    {
        types.put(node, TugaTypes.ERROR);
        if (isTerminalError(node))
            TypeError(node, operator, type);
    }

    private void setError(ParserRuleContext node, String operator, TugaTypes type1, TugaTypes type2)
    {
        types.put(node, TugaTypes.ERROR);
        if (isTerminalError(node))
            TypeError(node, operator, type1, type2);
    }

    private void raiseError(TugaParser.ExprContext node)
    {
        int line = 0;
        int charPositionInLine = 0;
        String msg = "";
        String text = "";

        if (node instanceof TugaParser.ExprContext)
        {
            TugaParser.ExprContext ctx = (TugaParser.ExprContext)node;
            line = ctx.getStart().getLine();
            charPositionInLine = ctx.getStart().getCharPositionInLine();
            text = getOriginalText(ctx);
        }
        else
        {
            throw new IllegalStateException("Should not get an error of anything that isn't a binary or unary operator.");
        }

        raiseError(line, charPositionInLine, msg);
    }

    private void raiseError(int line, int charPositionInLine, String msg)
    {
        for (MyErrorListener listener : this.listeners)
            listener.syntaxError(MyErrorListener.ErrorTypes.SEMANTIC, line, charPositionInLine, msg);
    }

    private static String getOriginalText(ParserRuleContext ctx)
    {
        if (ctx.start == null || ctx.stop == null)
            return ctx.getText();
        CharStream charStream = ctx.start.getInputStream();

        int startIndex = ctx.start.getStartIndex();
        int stopIndex = ctx.stop.getStopIndex();

        return charStream.getText(Interval.of(startIndex, stopIndex));
    }
}
