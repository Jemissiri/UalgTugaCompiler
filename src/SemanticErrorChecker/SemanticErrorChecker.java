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

    public SemanticErrorChecker(ParseTreeProperty<TugaTypes> types)
    {
        this.types = types;
        this.listeners = new ArrayList<MyErrorListener>();
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
        TugaTypes type = visit(ctx.expr());

        if (type == TugaTypes.INT || type == TugaTypes.DOUBLE)
        {
            types.put(ctx, type);
            return type;
        }

        error(ctx);
        return TugaTypes.ERROR;
    }

    @Override
    public TugaTypes visitLogicNegateOp(TugaParser.LogicNegateOpContext ctx)
    {
        TugaTypes type = visit(ctx.expr());

        if (type == TugaTypes.BOOLEAN)
        {
            types.put(ctx, type);
            return type;
        }

        error(ctx);
        return TugaTypes.ERROR;
    }

    @Override
    public TugaTypes visitMultDivModOp(TugaParser.MultDivModOpContext ctx)
    {
        TugaTypes left = visit(ctx.expr(0));
        TugaTypes right = visit(ctx.expr(1));

        if (!left.isNumeric() || !right.isNumeric())
        {
            error(ctx);
            return TugaTypes.ERROR;
        }

        if (left == TugaTypes.INT && right == TugaTypes.INT)
        {
            types.put(ctx, TugaTypes.INT);
            return TugaTypes.INT;
        }

        if (ctx.op.getType() == TugaParser.MOD)
        {
            error(ctx);
            return TugaTypes.ERROR;
        }

        types.put(ctx, TugaTypes.DOUBLE);
        return TugaTypes.DOUBLE;
    }

    @Override
    public TugaTypes visitSumSubOp(TugaParser.SumSubOpContext ctx)
    {
        TugaTypes left = visit(ctx.expr(0));
        TugaTypes right = visit(ctx.expr(1));

        if (ctx.op.getType() == TugaParser.SUM && (left == TugaTypes.STRING || right == TugaTypes.STRING))
        {
            types.put(ctx, TugaTypes.STRING);
            return TugaTypes.STRING;
        }

        if (!left.isNumeric() || !right.isNumeric())
        {
            error(ctx);
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
        TugaTypes left = visit(ctx.expr(0));
        TugaTypes right = visit(ctx.expr(1));

        if (!left.isNumeric() || !right.isNumeric())
        {
            error(ctx);
            return TugaTypes.ERROR;
        }

        types.put(ctx, TugaTypes.BOOLEAN);
        return TugaTypes.BOOLEAN;
    }

    @Override
    public TugaTypes visitEqualsOp(TugaParser.EqualsOpContext ctx)
    {
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

        error(ctx);
        return TugaTypes.ERROR;
    }

    @Override
    public TugaTypes visitAndOp(TugaParser.AndOpContext ctx)
    {
        TugaTypes left = visit(ctx.expr(0));
        TugaTypes right = visit(ctx.expr(1));

        if (left != TugaTypes.BOOLEAN || right != TugaTypes.BOOLEAN)
        {
            error(ctx);
            return TugaTypes.ERROR;
        }

        types.put(ctx, TugaTypes.BOOLEAN);
        return TugaTypes.BOOLEAN;
    }

    @Override
    public TugaTypes visitOrOp(TugaParser.OrOpContext ctx)
    {
        TugaTypes left = visit(ctx.expr(0));
        TugaTypes right = visit(ctx.expr(1));

        if (left != TugaTypes.BOOLEAN || right != TugaTypes.BOOLEAN)
        {
            error(ctx);
            return TugaTypes.ERROR;
        }

        types.put(ctx, TugaTypes.BOOLEAN);
        return TugaTypes.BOOLEAN;
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

    private void error(ParserRuleContext ctx)
    {
        types.put(ctx, TugaTypes.ERROR);
        if (this.types.get(ctx) == TugaTypes.ERROR && ctx instanceof TugaParser.ExprContext && !doChildrenHaveErrors(ctx))
            raiseError((TugaParser.ExprContext)ctx);
    }

    private void raiseError(TugaParser.ExprContext node) {
        int line = 0;
        int charPositionInLine = 0;
        String msg = "";

        if (node instanceof TugaParser.ExprContext) {
            TugaParser.ExprContext ctx = (TugaParser.ExprContext) node;
            line = ctx.getStart().getLine();
            charPositionInLine = ctx.getStart().getCharPositionInLine();
            msg = getOriginalText(ctx);
        }
        else
        {
            throw new IllegalStateException("Should not get an error of anything that isn't a binary or unary operator.");
        }

        for (MyErrorListener listener : this.listeners)
            listener.syntaxError(MyErrorListener.ErrorTypes.SEMANTIC, line, charPositionInLine, msg);
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
