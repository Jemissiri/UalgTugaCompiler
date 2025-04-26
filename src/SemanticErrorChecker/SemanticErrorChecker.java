package SemanticErrorChecker;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.*;

import java.util.*;

import Tuga.*;
import Types.*;
import MyErrorListeners.*;

public class SemanticErrorChecker extends TugaBaseVisitor<TugaTypes>
{
    private ParseTreeProperty<TugaTypes> types;
    private ArrayList<MyErrorListener> listeners;
    private TugaTypes currentVarType;
    private HashMap<String, TugaTypes> varTypes;

    public SemanticErrorChecker(ParseTreeProperty<TugaTypes> types, HashMap<String, TugaTypes> varTypes)
    {
        this.types = types;
        this.listeners = new ArrayList<MyErrorListener>();
        this.currentVarType = null;
        this.varTypes = varTypes;
    }

    @Override
    public TugaTypes visitInstAssign(TugaParser.InstAssignContext ctx)
    {
        String var = ctx.VAR().getText();
        if (!this.varTypes.containsKey(var))
        {
            UndeclaredVarError(ctx, var);
            types.put(ctx, TugaTypes.ERROR);
            return TugaTypes.ERROR;
        }

        final String operator = "<-";
        TugaTypes varType = this.varTypes.get(var);
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
                this.currentVarType = TugaTypes.INT;
                break;
            case TugaParser.DOUBLE_TYPE:
                this.currentVarType = TugaTypes.DOUBLE;
                break;
            case TugaParser.STRING_TYPE:
                this.currentVarType = TugaTypes.STRING;
                break;
            case TugaParser.BOOLEAN_TYPE:
                this.currentVarType = TugaTypes.BOOLEAN;
                break;
            default:
                throw new IllegalStateException("Invalid variable type.");
        }

        visit(ctx.variable());

        return this.currentVarType;
    }

    @Override
    public TugaTypes visitVars(TugaParser.VarsContext ctx)
    {
        if (this.varTypes.containsKey(ctx.VAR().getText()))
            AlreadyDeclaredVarError(ctx, ctx.VAR().getText());

        this.varTypes.put(ctx.VAR().getText(), this.currentVarType);
        visit(ctx.variable());
        return this.currentVarType;
    }

    @Override
    public TugaTypes visitVar(TugaParser.VarContext ctx)
    {
        if (this.varTypes.containsKey(ctx.VAR().getText()))
            AlreadyDeclaredVarError(ctx, ctx.VAR().getText());

        this.varTypes.put(ctx.VAR().getText(), this.currentVarType);
        return this.currentVarType;
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
        TugaTypes result = this.varTypes.get(ctx.VAR().getText());
        if (result == null)
            UndeclaredVarError(ctx, ctx.VAR().getText());
        types.put(ctx, result);
        return result;
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
