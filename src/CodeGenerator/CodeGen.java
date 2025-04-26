package CodeGenerator;

import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeProperty;

import Tuga.*;
import Bytecode.EnconderDecoder;
import Types.*;
import VM.*;

public class CodeGen extends TugaBaseVisitor<Void>
{
    private ParseTreeProperty<TugaTypes> types;
    private ArrayList<Instruction> code;
    private ArrayList<TugaValues> constantPool;
    private HashMap<TugaValues, Integer> constantPoolHash;

    private HashMap<String, TugaTypes> varTypes;
    private HashMap<String, Integer> globalVariableHash;
    // especifica quantos slots de memória consecutivos alocar
    private int globalVariableChainCounter;

    public CodeGen(ParseTreeProperty<TugaTypes> types, HashMap<String, TugaTypes> varTypes)
    {
        super();
        this.types = types;
        this.code = new ArrayList<Instruction>();
        this.constantPool = new ArrayList<TugaValues>();
        this.constantPoolHash = new HashMap<TugaValues, Integer>();

        this.varTypes = varTypes;
        this.globalVariableHash = new HashMap<String, Integer>();
        this.globalVariableChainCounter = 0;
    }

    public int emit(OpCode op, int... args)
    {
        code.add(new Instruction(op, args));
        return code.size() - 1;
    }

    public int emitConst(TugaTypes type, Object value)
    {
        TugaValues key = new TugaValues(type, value);
        int index;
        if (constantPoolHash.containsKey(key))
        {
            index = constantPoolHash.get(key);
        }
        else
        {
            constantPool.add(key);
            index = constantPool.size() - 1;
            constantPoolHash.put(key, index);
        }
        return index;
    }

    public void dumpCode()
    {
        System.out.println("Generated code in assembly format");
        for (int i = 0; i < code.size(); i++)
            System.out.println(i + ": " + code.get(i));
    }

    public byte[] getBytecode()
    {
        TugaValues[] constArr = new TugaValues[constantPool.size()];
        constantPool.toArray(constArr);
        Instruction[] instArr = new Instruction[code.size()];
        code.toArray(instArr);
        EnconderDecoder en = new EnconderDecoder(constArr, instArr);
        return en.encode();
    }

    private void convertToDoubleIfNeeded(TugaParser.ExprContext ctx0, TugaParser.ExprContext ctx1)
    {
        TugaTypes leftType = types.get(ctx0);
        TugaTypes rightType = types.get(ctx1);

        visit(ctx0);
        // se o da esquerda for int converter para double, caso o da direita for double.
        if (leftType == TugaTypes.INT && rightType == TugaTypes.DOUBLE)
        {
            emit(OpCode.itod);
            visit(ctx1);
        } // se o da direita for int converter para double, caso o da esquerda for double.
        else if (leftType == TugaTypes.DOUBLE && rightType == TugaTypes.INT)
        {
            visit(ctx1);
            emit(OpCode.itod);
        }
        else
            visit(ctx1);
    }

    private void convertToString(TugaTypes type)
    {
        switch (type)
        {
            case TugaTypes.INT:
                emit(OpCode.itos);
                break;
            case TugaTypes.DOUBLE:
                emit(OpCode.dtos);
                break;
            case TugaTypes.BOOLEAN:
                emit(OpCode.btos);
                break;
            default:
                break;
        }
    }

    public void patch(int index, OpCode op, int ...args)
    {
        code.set(index, new Instruction(op, args));
    }

    @Override
    public Void visitDeclVar(TugaParser.DeclVarContext ctx)
    {
        visit(ctx.variable());
        emit(OpCode.galloc, globalVariableChainCounter);
        // resetar o counter quando se aloca memoria
        globalVariableChainCounter = 0;
        return null;
    }

    @Override
    public Void visitVars(TugaParser.VarsContext ctx)
    {
        globalVariableHash.put(ctx.VAR().getText(), globalVariableHash.size());
        globalVariableChainCounter++;
        visit(ctx.variable());
        return null;
    }

    @Override
    public Void visitVar(TugaParser.VarContext ctx)
    {
        globalVariableHash.put(ctx.VAR().getText(), globalVariableHash.size());
        globalVariableChainCounter++;
        return null;
    }

    @Override
    public Void visitInstPrint(TugaParser.InstPrintContext ctx)
    {
        visit(ctx.expr());
        if (types.get(ctx.expr()) == TugaTypes.INT)
            emit(OpCode.iprint);
        else if (types.get(ctx.expr()) == TugaTypes.DOUBLE)
            emit(OpCode.dprint);
        else if (types.get(ctx.expr()) == TugaTypes.BOOLEAN)
            emit(OpCode.bprint);
        else if (types.get(ctx.expr()) == TugaTypes.STRING)
            emit(OpCode.sprint);

        return null;
    }

    @Override
    public Void visitInstAssign(TugaParser.InstAssignContext ctx)
    {
        visit(ctx.expr());
        TugaTypes varType = varTypes.get(ctx.VAR().getText());
        TugaTypes exprType = types.get(ctx.expr());
        if (varType == TugaTypes.DOUBLE && exprType == TugaTypes.INT)
            emit(OpCode.itod);
        emit(OpCode.gstore, globalVariableHash.get(ctx.VAR().getText()));

        return null;
    }

    // Ao compilar uma instrucao if,
    // o jumpf deve pular o bloco then se a condição for falsa.
    // No momento do emit do jump (emit(OpCode.jumpf, -1)),
    // ainda não sabemos onde o bloco then termina
    // entao emitimos o salto com um valor de espaço reservado (-1)
    // e posteriormente o corrigimos com o adereco correto com o patch.
    @Override
    public Void visitInstIf(TugaParser.InstIfContext ctx)
    {
        visit(ctx.expr());
        int elseJumpIndex = emit(OpCode.jumpf, -1);
        visit(ctx.scopeOrInst());
        // posicao depois do bloco else
        int end = code.size();

        patch(elseJumpIndex, OpCode.jumpf, end);
        return null;
    }

    @Override
    public Void visitInstIfElse(TugaParser.InstIfElseContext ctx)
    {
        visit(ctx.expr());
        int elseJumpIndex = emit(OpCode.jumpf, -1);
        visit(ctx.scopeOrInst(0));
        int elseSkipIndex = emit(OpCode.jump, -1);
        // posicao no inicio do bloco else
        int middle = code.size();
        visit(ctx.scopeOrInst(1));
        // posicao depois do bloco else
        int end = code.size();

        patch(elseJumpIndex, OpCode.jumpf, middle);
        patch(elseSkipIndex, OpCode.jump, end);
        return null;
    }

    @Override
    public Void visitInstWhile(TugaParser.InstWhileContext ctx)
    {
        int start = code.size();
        visit(ctx.expr());
        int conditionJumpIndex = emit(OpCode.jumpf, -1);
        visit(ctx.scopeOrInst());
        emit(OpCode.jump, start);
        int end = code.size();

        patch(conditionJumpIndex, OpCode.jumpf, end);
        return null;
    }

    @Override
    public Void visitMultDivModOp(TugaParser.MultDivModOpContext ctx)
    {
        TugaTypes leftType = types.get(ctx.expr(0));
        TugaTypes rightType = types.get(ctx.expr(1));

        OpCode op = OpCode.imult;
        if (ctx.op.getType() == TugaParser.MULT)
            op = OpCode.imult;
        else if (ctx.op.getType() == TugaParser.DIV)
            op = OpCode.idiv;
        else if (ctx.op.getType() == TugaParser.MOD)
            op = OpCode.imod;

        if (leftType == TugaTypes.DOUBLE || rightType == TugaTypes.DOUBLE)
        {
            if (op == OpCode.imult)
                op = OpCode.dmult;
            else if (op == OpCode.idiv)
                op = OpCode.ddiv;
        }

        convertToDoubleIfNeeded(ctx.expr(0), ctx.expr(1));
        emit(op);
        return null;
    }

    @Override
    public Void visitSumSubOp(TugaParser.SumSubOpContext ctx)
    {
        TugaTypes leftType = types.get(ctx.expr(0));
        TugaTypes rightType = types.get(ctx.expr(1));

        OpCode op = OpCode.iadd;
        if (ctx.op.getType() == TugaParser.SUM)
            op = OpCode.iadd;
        else if (ctx.op.getType() == TugaParser.SUB)
            op = OpCode.isub;

        if ((leftType == TugaTypes.STRING || rightType == TugaTypes.STRING) &&
             ctx.op.getType() == TugaParser.SUM)
        {
            op = OpCode.sconcat;
            visit(ctx.expr(0));
            convertToString(leftType);
            visit(ctx.expr(1));
            convertToString(rightType);
        }

        if (leftType == TugaTypes.DOUBLE || rightType == TugaTypes.DOUBLE)
        {
            if (op == OpCode.iadd)
                op = OpCode.dadd;
            else if (op == OpCode.isub)
                op = OpCode.dsub;
        }

        if (op != OpCode.sconcat)
            convertToDoubleIfNeeded(ctx.expr(0), ctx.expr(1));
        emit(op);
        return null;
    }

    @Override
    public Void visitRelOp(TugaParser.RelOpContext ctx)
    {
        TugaTypes leftType = types.get(ctx.expr(0));
        TugaTypes rightType = types.get(ctx.expr(1));

        boolean swap; // serve para caso precice-se inverter, neste caso, para os greaters.
        OpCode op;
        if (ctx.op.getType() == TugaParser.LESS)
        {
            swap = false;
            op = OpCode.ilt;
        }
        else if (ctx.op.getType() == TugaParser.LESS_EQ)
        {
            swap = false;
            op = OpCode.ileq;
        }
        else if (ctx.op.getType() == TugaParser.GREATER)
        {
            swap = true;
            op = OpCode.ilt;
        }
        else if (ctx.op.getType() == TugaParser.GREATER_EQ)
        {
            swap = true;
            op = OpCode.ileq;
        }
        else
            throw new IllegalStateException("Unknown operator.");

        if (leftType == TugaTypes.DOUBLE || rightType == TugaTypes.DOUBLE)
        {
            if (op == OpCode.ilt)
                op = OpCode.dlt;
            else if (op == OpCode.ileq)
                op = OpCode.dleq;
        }

        if (!swap)
            convertToDoubleIfNeeded(ctx.expr(0), ctx.expr(1));
        else
            convertToDoubleIfNeeded(ctx.expr(1), ctx.expr(0));
        emit(op);
        return null;
    }

    @Override
    public Void visitEqualsOp(TugaParser.EqualsOpContext ctx)
    {
        TugaTypes leftType = types.get(ctx.expr(0));
        TugaTypes rightType = types.get(ctx.expr(1));

        OpCode op;
        if (ctx.op.getType() == TugaParser.EQUALS)
            op = OpCode.ieq;
        else if (ctx.op.getType() == TugaParser.N_EQUALS)
            op = OpCode.ineq;
        else
            throw new IllegalStateException("Unknown operator.");

        if (leftType == TugaTypes.DOUBLE || rightType == TugaTypes.DOUBLE)
        {
            if (op == OpCode.ieq)
                op = OpCode.deq;
            else if (op == OpCode.ineq)
                op = OpCode.dneq;
        }
        else if (leftType == TugaTypes.STRING && rightType == TugaTypes.STRING)
        {
            if (op == OpCode.ieq)
                op = OpCode.seq;
            else if (op == OpCode.ineq)
                op = OpCode.sneq;
        }
        else if (leftType == TugaTypes.BOOLEAN && rightType == TugaTypes.BOOLEAN)
        {
            if (op == OpCode.ieq)
                op = OpCode.beq;
            else if (op == OpCode.ineq)
                op = OpCode.bneq;
        }

        convertToDoubleIfNeeded(ctx.expr(0), ctx.expr(1));
        emit(op);
        return null;
    }

    @Override
    public Void visitAndOp(TugaParser.AndOpContext ctx)
    {
        visit(ctx.expr(0));
        visit(ctx.expr(1));
        emit(OpCode.and);
        return null;
    }

    @Override
    public Void visitOrOp(TugaParser.OrOpContext ctx)
    {
        visit(ctx.expr(0));
        visit(ctx.expr(1));
        emit(OpCode.or);
        return null;
    }

    @Override
    public Void visitNegateOp(TugaParser.NegateOpContext ctx)
    {
        visit(ctx.expr());
        if (types.get(ctx.expr()) == TugaTypes.INT)
            emit(OpCode.iuminus);
        else if (types.get(ctx.expr()) == TugaTypes.DOUBLE)
            emit(OpCode.duminus);
        return null;
    }

    @Override
    public Void visitLogicNegateOp(TugaParser.LogicNegateOpContext ctx)
    {
        visit(ctx.expr());
        emit(OpCode.not);
        return null;
    }

    @Override
    public Void visitParenExpr(TugaParser.ParenExprContext ctx)
    {
        visit(ctx.expr());
        return null;
    }

    @Override
    public Void visitInt(TugaParser.IntContext ctx)
    {
        int value = Integer.valueOf(ctx.INT().getText());
        emit(OpCode.iconst, value);
        return null;
    }

    @Override
    public Void visitDouble(TugaParser.DoubleContext ctx)
    {
        double value = Double.valueOf(ctx.DOUBLE().getText());
        int index = emitConst(TugaTypes.DOUBLE, value);
        emit(OpCode.dconst, index);
        return null;
    }

    @Override
    public Void visitString(TugaParser.StringContext ctx)
    {
        String text = ctx.STRING().getText();
        String value = text.substring(1, text.length() - 1);
        int index = emitConst(TugaTypes.STRING, value);
        emit(OpCode.sconst, index);
        return null;
    }

    @Override
    public Void visitTrue(TugaParser.TrueContext ctx)
    {
        emit(OpCode.tconst);
        return null;
    }

    @Override
    public Void visitFalse(TugaParser.FalseContext ctx)
    {
        emit(OpCode.fconst);
        return null;
    }

    @Override
    public Void visitVarExpr(TugaParser.VarExprContext ctx)
    {
        emit(OpCode.gload, globalVariableHash.get(ctx.VAR().getText()));
        return null;
    }

    @Override
    public Void visitTuga(TugaParser.TugaContext ctx)
    {
        super.visitTuga(ctx);
        emit(OpCode.halt);
        return null;
    }
}
