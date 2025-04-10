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
    private Set<ParserRuleContext> reportedErrors;

    public SemanticErrorChecker(ParseTreeProperty<TugaTypes> types)
    {
        this.types = types;
        this.listeners = new ArrayList<MyErrorListener>();
        this.reportedErrors = new HashSet<>();
    }

    @Override
    public TugaTypes visitParenExpr(TugaParser.ParenExprContext ctx)
    {
        TugaTypes result = visit(ctx.expr());
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

        return error(ctx);
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

        return error(ctx);
    }

    @Override
    public TugaTypes visitMultDivModOp(TugaParser.MultDivModOpContext ctx)
    {
        TugaTypes left = visit(ctx.expr(0));
        TugaTypes right = visit(ctx.expr(1));

        if (left == TugaTypes.ERROR || right == TugaTypes.ERROR)
        {
            types.put(ctx, TugaTypes.ERROR);
            return TugaTypes.ERROR;
        }

        if (!left.isNumeric() || !right.isNumeric())
            return error(ctx);

        if (left == TugaTypes.INT && right == TugaTypes.INT)
        {
            types.put(ctx, TugaTypes.INT);
            return TugaTypes.INT;
        }

        if (ctx.op.getType() == TugaParser.MOD)
            return error(ctx);

        types.put(ctx, TugaTypes.DOUBLE);
        return TugaTypes.DOUBLE;
    }

    @Override
    public TugaTypes visitSumSubOp(TugaParser.SumSubOpContext ctx)
    {
        TugaTypes left = visit(ctx.expr(0));
        TugaTypes right = visit(ctx.expr(1));

        if (left == TugaTypes.ERROR || right == TugaTypes.ERROR)
        {
            types.put(ctx, TugaTypes.ERROR);
            return TugaTypes.ERROR;
        }

        if (ctx.op.getType() == TugaParser.SUM && (left == TugaTypes.STRING || right == TugaTypes.STRING))
        {
            types.put(ctx, TugaTypes.STRING);
            return TugaTypes.STRING;
        }

        if (!left.isNumeric() || !right.isNumeric())
            return error(ctx);

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

        if (left == TugaTypes.ERROR || right == TugaTypes.ERROR)
        {
            types.put(ctx, TugaTypes.ERROR);
            return TugaTypes.ERROR;
        }

        if (!left.isNumeric() || !right.isNumeric())
            return error(ctx);

        types.put(ctx, TugaTypes.BOOLEAN);
        return TugaTypes.BOOLEAN;
    }

    @Override
    public TugaTypes visitEqualsOp(TugaParser.EqualsOpContext ctx)
    {
        TugaTypes left = visit(ctx.expr(0));
        TugaTypes right = visit(ctx.expr(1));

        if (left == TugaTypes.ERROR || right == TugaTypes.ERROR)
        {
            types.put(ctx, TugaTypes.ERROR);
            return TugaTypes.ERROR;
        }

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

        return error(ctx);
    }

    @Override
    public TugaTypes visitAndOp(TugaParser.AndOpContext ctx)
    {
        TugaTypes left = visit(ctx.expr(0));
        TugaTypes right = visit(ctx.expr(1));

        if (left == TugaTypes.ERROR || right == TugaTypes.ERROR)
        {
            types.put(ctx, TugaTypes.ERROR);
            return TugaTypes.ERROR;
        }

        if (left != TugaTypes.BOOLEAN || right != TugaTypes.BOOLEAN)
            error(ctx);

        types.put(ctx, TugaTypes.BOOLEAN);
        return TugaTypes.BOOLEAN;
    }

    @Override
    public TugaTypes visitOrOp(TugaParser.OrOpContext ctx)
    {
        TugaTypes left = visit(ctx.expr(0));
        TugaTypes right = visit(ctx.expr(1));

        if (left == TugaTypes.ERROR || right == TugaTypes.ERROR)
        {
            types.put(ctx, TugaTypes.ERROR);
            return TugaTypes.ERROR;
        }

        if (left != TugaTypes.BOOLEAN || right != TugaTypes.BOOLEAN)
            error(ctx);

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
            listeners.add(listener);
        else
            throw new NullPointerException("Listener should not be null");
    }

    public void removeErrorListener()
    {
        if (!this.listeners.isEmpty())
            this.listeners = new ArrayList<MyErrorListener>();
    }

    private TugaTypes error(ParserRuleContext ctx)
    {
        if (!reportedErrors.contains(ctx))
        {
            reportedErrors.add(ctx);
            int line = ctx.getStart().getLine();
            int charPosition = ctx.getStart().getCharPositionInLine();
            for (MyErrorListener listener : listeners)
                listener.syntaxError(MyErrorListener.ErrorTypes.SEMANTIC, line, charPosition, getOriginalText(ctx));
            //System.err.printf("Type error at line %d:%d - %s\n", line, charPosition, message);
        }
        return TugaTypes.ERROR;
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
