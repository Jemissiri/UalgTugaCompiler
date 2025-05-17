package CodeGenerator;

import java.util.*;

import org.antlr.v4.runtime.tree.ParseTreeProperty;

import Tuga.*;
import Bytecode.EnconderDecoder;
import Types.*;
import Types.Symbols.*;
import VM.*;

public class CodeGen extends TugaBaseVisitor<Void>
{
    private ParseTreeProperty<TugaTypes> types;
    private ArrayList<Instruction> code;
    private ArrayList<TugaValues> constantPool;
    private HashMap<TugaValues, Integer> constantPoolHash;

    private HashMap<String, TugaTypes> globalVarTypes;
    private HashMap<String, Integer> globalVariableHash;
    // especifica quantos slots de memória consecutivos alocar
    private int globalVariableChainCounter;

    private boolean isFirstFunctionDeclaration;
    private HashMap<String, FunctionSymbol> functions;
    private FunctionSymbol currFunction;
    private ArrayList<String> functionsArr;
    private HashMap<String, Integer> functionsArrIndexMapping;
    private HashMap<String, Integer> functionCodeIndex;
    private Stack<FunctionSymbol> currFunctionCalls;
    private Stack<Integer> currFunctionCallIndex;
    private int localVariableFrameCounter;
    private int localVariableChainCounter;
    private HashMap<String, Integer> currLocalVarFrameIndex;
    private HashMap<String, TugaTypes> currLocalVarTypes;
    private TugaTypes currLocalVarType;
    private HashMap<String, Integer> globalVariableMapping;
    private int currArgCounter;

    public CodeGen(ParseTreeProperty<TugaTypes> types, HashMap<String, TugaTypes> varTypes, HashMap<String, FunctionSymbol> functions)
    {
        super();
        this.types = types;
        this.code = new ArrayList<Instruction>();
        this.constantPool = new ArrayList<TugaValues>();
        this.constantPoolHash = new HashMap<TugaValues, Integer>();

        this.globalVarTypes = varTypes;
        this.globalVariableHash = new HashMap<String, Integer>();
        this.globalVariableChainCounter = 0;
        this.globalVariableMapping = new HashMap<String, Integer>();

        this.isFirstFunctionDeclaration = true;
        this.functions = functions;
        this.functionsArr = new ArrayList<String>();
        this.functionsArrIndexMapping = new HashMap<String, Integer>();
        this.functionCodeIndex = new HashMap<String, Integer>();
        this.currFunctionCalls = new Stack<FunctionSymbol>();
        this.currFunctionCallIndex = new Stack<Integer>();
        for (FunctionSymbol fn : functions.values())
        {
            functionsArr.add(fn.name());
            functionsArrIndexMapping.put(fn.name(), functionsArr.size() - 1);
        }
        this.localVariableFrameCounter = 0;
        this.localVariableChainCounter = 0;

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
    public Void visitFunctionDecl(TugaParser.FunctionDeclContext ctx)
    {
        // fazer com que o programa comece executando "principal()" automaticamente
        if (isFirstFunctionDeclaration)
        {
            int mainId = functionsArrIndexMapping.get("principal");
            emit(OpCode.call, mainId);
            emit(OpCode.halt);
            isFirstFunctionDeclaration = false;
        }

        String name = ctx.VAR().getText();
        FunctionSymbol fn = functions.get(name);
        int start = code.size();
        functionCodeIndex.put(name, start);

        localVariableFrameCounter = 0;
        currLocalVarFrameIndex = new HashMap<String, Integer>();
        currLocalVarTypes = new HashMap<String, TugaTypes>();
        currFunction = fn;
        currArgCounter = 0;
        visit(ctx.VAR());           // Visita o nome da função
        if (ctx.arg_list() != null)
            visit(ctx.arg_list());  // Visita argumentos (ex: 'a, b')
        visit(ctx.scope());         // Visita o corpo da função '{ ... }'
        currFunction = null;

        // Se a função nao retorna valor (void), insere um ret (return) explícito.
        if (fn.type() == TugaTypes.VOID)
            emit(OpCode.ret, fn.argNum());

        return null;
    }

    @Override
    public Void visitFunctionCall(TugaParser.FunctionCallContext ctx)
    {
        String name = ctx.VAR().getText();
        int id = functionsArrIndexMapping.get(name);

        visit(ctx.VAR());
        currFunctionCallIndex.push(0); // Inicia contador de argumentos
        currFunctionCalls.push(functions.get(name)); // Empilha a função atual
        if (ctx.expr_list() != null)
            visit(ctx.expr_list()); // Gera bytecode para cada argumento
        currFunctionCalls.pop(); // Remove a função da pilha
        currFunctionCallIndex.pop(); // Remove o contador de argumentos

        emit(OpCode.call, id);

        return null;
    }

    @Override
    public Void visitInstReturn(TugaParser.InstReturnContext ctx)
    {
        // funções void sem expressão de retorno (return;)
        if (ctx.expr() == null)
        {
            emit(OpCode.ret, currFunction.argNum());
        }
        else
        {
            visit(ctx.expr()); // Gera código para avaliar a expressão
            // Se necessario, converte int para double
            if (currFunction.type() == TugaTypes.DOUBLE && types.get(ctx.expr()) == TugaTypes.INT)
                emit(OpCode.itod);
            emit(OpCode.retval, currFunction.argNum());
        }

        return null;
    }

    @Override
    public Void visitDeclArg(TugaParser.DeclArgContext ctx)
    {
        // obtem o nome do argumento , Calcula a posição relativa no stack frame
        currLocalVarFrameIndex.put(ctx.VAR().getText(), -currFunction.argNum() + currArgCounter);
        // converte o tipo do argumento do formato ANTLR para o tipo
        // e armazena no currLocalVarTypes (nome -> tipo)
        currLocalVarTypes.put(ctx.VAR().getText(), antlrTypeConvert(ctx.type.getType()));
        // incrementa o counter para o proximo argumento
        currArgCounter++;

        return null;
    }

    @Override
    public Void visitExpression(TugaParser.ExpressionContext ctx)
    {
        FunctionSymbol fn = currFunctionCalls.peek();
        int index = currFunctionCallIndex.pop();  // Índice do argumento atual

        visit(ctx.expr());

        TugaTypes exprType = types.get(ctx.expr());
        TugaTypes argType = fn.getArg(index).type();
        if (exprType == TugaTypes.INT && argType == TugaTypes.DOUBLE)
            emit(OpCode.itod);

        // Prepara para o próximo argumento (se houver)
        currFunctionCallIndex.push(index + 1);
        return null;
    }

    @Override
    public Void visitExpressions(TugaParser.ExpressionsContext ctx)
    {
        FunctionSymbol fn = currFunctionCalls.peek();
        int index = currFunctionCallIndex.pop();

        visit(ctx.expr());

        TugaTypes exprType = types.get(ctx.expr());
        TugaTypes argType = fn.getArg(index).type();
        if (exprType == TugaTypes.INT && argType == TugaTypes.DOUBLE)
            emit(OpCode.itod);

        currFunctionCallIndex.push(index + 1);
        visit(ctx.expr_list()); // Processa os argumentos restantes recursivamente

        return null;
    }

    @Override
    public Void visitInstScope(TugaParser.InstScopeContext ctx)
    {
        // armazena o contador atual de variaveis locais no inicio do scope.
        int startLocalVarFrame = localVariableFrameCounter;
        // visita cada declaracao de variavel e incrementa localVariableFrameCounter para cada variavel declarada
        for (int i = 0; i < ctx.declare_var().size(); i++)
            visit(ctx.declare_var(i));
        int endLocalVarFrame = localVariableFrameCounter;

        for (int i = 0; i < ctx.inst().size(); i++)
            visit(ctx.inst(i));

        // Se houver variaveis novas e a funcao eh void entao faz pop para remover N variaveis da stack
        // eh so em funcoes void, pois as nao voids usam retval
        int frameDiff = endLocalVarFrame - startLocalVarFrame;
        if (frameDiff > 0 && currFunction.type() == TugaTypes.VOID)
            emit(OpCode.pop, frameDiff);
        // restaura o counter de variaveis para o valor anterior ao do scope
        localVariableFrameCounter = startLocalVarFrame;

        return null;
    }

    @Override
    public Void visitDeclVar(TugaParser.DeclVarContext ctx)
    {
        currLocalVarType = antlrTypeConvert(ctx.type.getType());
        visit(ctx.variable());

        if (currFunction == null) // variavel global
        {
            emit(OpCode.galloc, globalVariableChainCounter);
            globalVariableChainCounter = 0;
        }
        else // variavel local
        {
            emit(OpCode.lalloc, localVariableChainCounter);
            localVariableChainCounter = 0;
        }

        return null;

        // visit(ctx.variable());
        // emit(OpCode.galloc, globalVariableChainCounter);
        // resetar o counter quando se aloca memoria
        // globalVariableChainCounter = 0;
        // return null;
    }

    @Override
    public Void visitVars(TugaParser.VarsContext ctx)
    {
        if (currFunction == null) // variavel global
        {
            globalVariableMapping.put(ctx.VAR().getText(), globalVariableMapping.size());
            globalVariableChainCounter++;
        }
        else // variavel local
        {
            //+2 eh o espaço para endereço de retorno e frame pointer.
            currLocalVarFrameIndex.put(ctx.VAR().getText(), 2 + localVariableFrameCounter);
            currLocalVarTypes.put(ctx.VAR().getText(), currLocalVarType);
            localVariableFrameCounter++;
            localVariableChainCounter++;
        }
        visit(ctx.variable());

        return null;

        // globalVariableHash.put(ctx.VAR().getText(), globalVariableHash.size());
        // globalVariableChainCounter++;
        // visit(ctx.variable());
        // return null;
    }

    @Override
    public Void visitVar(TugaParser.VarContext ctx)
    {
        if (currFunction == null) // variavel global
        {
            globalVariableMapping.put(ctx.VAR().getText(), globalVariableMapping.size());
            globalVariableChainCounter++;
        }
        else // variavel local
        {
            //+2 eh o espaço para endereço de retorno e frame pointer.
            currLocalVarFrameIndex.put(ctx.VAR().getText(), 2 + localVariableFrameCounter);
            currLocalVarTypes.put(ctx.VAR().getText(), currLocalVarType);
            localVariableFrameCounter++;
            localVariableChainCounter++;
        }

        return null;

        // globalVariableHash.put(ctx.VAR().getText(), globalVariableHash.size());
        // globalVariableChainCounter++;
        // return null;
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
    public Void visitInstAssign(TugaParser.InstAssignContext ctx) {
        visit(ctx.expr());
        String name = ctx.VAR().getText();
        boolean isLocalVar;
        TugaTypes varType = null;

        if (globalVariableMapping.containsKey(name))
            isLocalVar = false;
        else if (currLocalVarTypes.containsKey(name))
            isLocalVar = true;
        else
            throw new IllegalStateException("Variable should either be in global or local mapping.");

        if (isLocalVar)
            varType = currLocalVarTypes.get(name);
        else
            varType = globalVarTypes.get(name);

        TugaTypes exprType = types.get(ctx.expr());
        if (varType == TugaTypes.DOUBLE && exprType == TugaTypes.INT)
            emit(OpCode.itod);

        if (isLocalVar)
            emit(OpCode.lstore, currLocalVarFrameIndex.get(name));
        else
            emit(OpCode.gstore, globalVariableMapping.get(name));

        return null;

        // TugaTypes varType = varTypes.get(ctx.VAR().getText());
        // TugaTypes exprType = types.get(ctx.expr());
        // if (varType == TugaTypes.DOUBLE && exprType == TugaTypes.INT)
        //     emit(OpCode.itod);
        // emit(OpCode.gstore, globalVariableHash.get(ctx.VAR().getText()));

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

        if ((leftType == TugaTypes.STRING || rightType == TugaTypes.STRING) && ctx.op.getType() == TugaParser.SUM)
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

        boolean swap = false; // serve para caso precice-se inverter, neste caso, para os greaters.
        OpCode op = OpCode.ilt;
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

        OpCode op = OpCode.ieq;
        if (ctx.op.getType() == TugaParser.EQUALS)
            op = OpCode.ieq;
        else if (ctx.op.getType() == TugaParser.N_EQUALS)
            op = OpCode.ineq;

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
        String name = ctx.VAR().getText();
        if (globalVariableMapping.containsKey(name)) // variavel global
            emit(OpCode.gload, globalVariableMapping.get(name));
        else if (currLocalVarTypes.containsKey(name)) // variavel local
            emit(OpCode.lload, currLocalVarFrameIndex.get(name));
        else
            throw new IllegalStateException("Variable should either be in global or local mapping.");
        return null;

        // emit(OpCode.gload, globalVariableHash.get(ctx.VAR().getText()));
        // return null;
    }

    @Override
    public Void visitTuga(TugaParser.TugaContext ctx)
    {
        super.visitTuga(ctx);

        for (int i = 0; i < code.size(); i++)
        {
            Instruction inst = code.get(i);
            if (inst.getOpCode() == OpCode.call)
            {
                int arg = inst.args()[0];
                String name = functionsArr.get(arg);
                int codeIndex = functionCodeIndex.get(name);
                patch(i, OpCode.call, codeIndex);
            }
        }

        // emit(OpCode.halt);
        return null;
    }
}
