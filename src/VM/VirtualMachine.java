package VM;

import Types.TugaTypes;
import Types.TugaValues;

import Bytecode.*;
import java.util.*;

public class VirtualMachine
{
    private boolean trace;       // trace flag
    private boolean halt;
    private final byte[] bytecodes;    // the bytecodes, storing just for displaying them. Not really needed
    private final TugaValues[] constantPool;
    private Instruction[] code;        // instructions (converted from the bytecodes)
    private final Stack<TugaValues> stack;    // runtime stack
    private int ip;                    // instruction pointer


    public VirtualMachine(byte [] bytecodes, boolean trace ) {
        this.trace = trace;
        this.halt = false;
        this.bytecodes = bytecodes;
        EnconderDecoder en = new EnconderDecoder(bytecodes);
        this.constantPool = en.getConstantPool();
        this.code = en.getInstructions();
        this.stack = new Stack<TugaValues>();
        this.ip = 0;
    }

    /*
    // dump the instructions, along with the corresponding bytecodes
    public void dumpInstructionsAndBytecodes() {
        System.out.println("Disassembled instructions");
        int idx = 0;
        for (int i=0; i< code.length; i++) {
            StringBuilder s = new StringBuilder();
            s.append(String.format("%02X ", bytecodes[idx++]));
            if (code[i].nArgs() == 1)
                for (int k=0; k<4; k++)
                    s.append(String.format("%02X ", bytecodes[idx++]));
            System.out.println( String.format("%5s: %-15s // %s", i, code[i], s) );
        }
    }
     */

    // dump the instructions to the screen
    public void dumpInstructions()
    {
        for (int i=0; i< code.length; i++)
            System.out.println( i + ": " + code[i] );
    }

    public void dumpConstantPool()
    {
        for (int i = 0; i < constantPool.length; i++)
            System.out.println(i + ": " + constantPool[i]);
    }

    public void run()
    {
        if (trace)
        {
            System.out.println("Trace while running the code");
            System.out.println("Execution starts at instrution " + ip);
        }

        halt = false;
        while (ip < code.length && !halt)
            exec_inst(code[ip++]);

        if (trace)
        {
            System.out.println(String.format("%22s Stack: %s", "", stack));
        }
    }

    private void runtimeError(String msg)
    {
        System.err.println("runtime error: " + msg);
        if (trace)
            System.err.println(String.format("%22s Stack: %s", "", stack));
        System.exit(1);
    }

    // funcao para sempre verificar se o tipo e correto
    private void checkType(TugaValues value, TugaTypes expectedType)
    {
        if (value.getType() != expectedType)
        {
            runtimeError("Instruction type " + value.getType().name() +
                              " Expected type " + expectedType.name());
        }
    }

    private void exec_iconst(int arg)
    {
        TugaValues value = new TugaValues(TugaTypes.INT, arg);
        stack.push(value);
    }

    private void exec_dconst(int arg)
    {
        TugaValues value = constantPool[arg];
        checkType(value, TugaTypes.DOUBLE);
        stack.push(value);
    }

    private void exec_sconst(int arg)
    {
        TugaValues value = constantPool[arg];
        checkType(value, TugaTypes.STRING);
        stack.push(value);
    }

    private void exec_iprint()
    {
        TugaValues value = stack.pop();
        checkType(value, TugaTypes.INT);
        System.out.println(value.getIntValue());
    }

    private void exec_iuminus()
    {
        TugaValues value = stack.pop();
        checkType(value, TugaTypes.INT);
        TugaValues result = new TugaValues(TugaTypes.INT, -value.getIntValue());
        stack.push(result);
    }

    private void exec_iadd()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.INT);
        checkType(left, TugaTypes.INT);
        TugaValues result = new TugaValues(TugaTypes.INT, left.getIntValue() + right.getIntValue());
        stack.push(result);
    }

    private void exec_isub()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.INT);
        checkType(left, TugaTypes.INT);
        TugaValues result = new TugaValues(TugaTypes.INT, left.getIntValue() - right.getIntValue());
        stack.push(result);
    }

    private void exec_imult()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.INT);
        checkType(left, TugaTypes.INT);
        TugaValues result = new TugaValues(TugaTypes.INT, left.getIntValue() * right.getIntValue());
        stack.push(result);
    }

    private void exec_idiv()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.INT);
        checkType(left, TugaTypes.INT);
        if (right.getIntValue() == 0)
            runtimeError("division by 0");
        TugaValues result = new TugaValues(TugaTypes.INT, left.getIntValue() / right.getIntValue());
        stack.push(result);
    }

    private void exec_imod()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.INT);
        checkType(left, TugaTypes.INT);
        if (right.getIntValue() == 0)
            runtimeError("modulo by 0");
        TugaValues result = new TugaValues(TugaTypes.INT, left.getIntValue() % right.getIntValue());
        stack.push(result);
    }

    private void exec_ieq()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.INT);
        checkType(left, TugaTypes.INT);
        TugaValues result = new TugaValues(TugaTypes.BOOLEAN, left.getIntValue() == right.getIntValue());
        stack.push(result);
    }

    private void exec_ineq()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.INT);
        checkType(left, TugaTypes.INT);
        TugaValues result = new TugaValues(TugaTypes.BOOLEAN, left.getIntValue() != right.getIntValue());
        stack.push(result);
    }

    private void exec_ilt()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.INT);
        checkType(left, TugaTypes.INT);
        TugaValues result = new TugaValues(TugaTypes.BOOLEAN, left.getIntValue() < right.getIntValue());
        stack.push(result);
    }

    private void exec_ileq()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.INT);
        checkType(left, TugaTypes.INT);
        TugaValues result = new TugaValues(TugaTypes.BOOLEAN, left.getIntValue() <= right.getIntValue());
        stack.push(result);
    }

    private void exec_itod()
    {
        TugaValues value = stack.pop();
        checkType(value, TugaTypes.INT);
        TugaValues result = new TugaValues(TugaTypes.DOUBLE, (double)value.getIntValue());
        stack.push(result);
    }

    private void exec_itos()
    {
        TugaValues value = stack.pop();
        checkType(value, TugaTypes.INT);
        TugaValues result = new TugaValues(TugaTypes.STRING, String.valueOf(value.getIntValue()));
        stack.push(result);
    }

    private void exec_dprint()
    {
        TugaValues value = stack.pop();
        checkType(value, TugaTypes.DOUBLE);
        System.out.println(value.getDoubleValue());
    }

    private void exec_duminus()
    {
        TugaValues value = stack.pop();
        checkType(value, TugaTypes.DOUBLE);
        TugaValues result = new TugaValues(TugaTypes.DOUBLE, -value.getDoubleValue());
        stack.push(result);
    }

    private void exec_dadd()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.DOUBLE);
        checkType(left, TugaTypes.DOUBLE);
        TugaValues result = new TugaValues(TugaTypes.DOUBLE, left.getDoubleValue() + right.getDoubleValue() );
        stack.push(result);
    }

    private void exec_dsub()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.DOUBLE);
        checkType(left, TugaTypes.DOUBLE);
        TugaValues result = new TugaValues(TugaTypes.DOUBLE, left.getDoubleValue() - right.getDoubleValue());
        stack.push(result);
    }

    private void exec_dmult()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.DOUBLE);
        checkType(left, TugaTypes.DOUBLE);
        TugaValues result = new TugaValues(TugaTypes.DOUBLE, left.getDoubleValue() * right.getDoubleValue());
        stack.push(result);
    }

    private void exec_ddiv()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.DOUBLE);
        checkType(left, TugaTypes.DOUBLE);
        if (right.getDoubleValue() == 0)
            runtimeError("division by 0");
        TugaValues result = new TugaValues(TugaTypes.DOUBLE, left.getDoubleValue() / right.getDoubleValue());
        stack.push(result);
    }

    private void exec_deq()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.DOUBLE);
        checkType(left, TugaTypes.DOUBLE);
        TugaValues result = new TugaValues(TugaTypes.BOOLEAN, left.getDoubleValue() == right.getDoubleValue());
        stack.push(result);
    }

    private void exec_dneq()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.DOUBLE);
        checkType(left, TugaTypes.DOUBLE);
        TugaValues result = new TugaValues(TugaTypes.BOOLEAN, left.getDoubleValue() != right.getDoubleValue());
        stack.push(result);
    }

    private void exec_dlt()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.DOUBLE);
        checkType(left, TugaTypes.DOUBLE);
        TugaValues result = new TugaValues(TugaTypes.BOOLEAN, left.getDoubleValue() < right.getDoubleValue());
        stack.push(result);
    }

    private void exec_dleq()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.DOUBLE);
        checkType(left, TugaTypes.DOUBLE);
        TugaValues result = new TugaValues(TugaTypes.BOOLEAN, left.getDoubleValue() <= right.getDoubleValue());
        stack.push(result);
    }

    private void exec_dtos()
    {
        TugaValues value = stack.pop();
        checkType(value, TugaTypes.DOUBLE);
        TugaValues result = new TugaValues(TugaTypes.STRING, String.valueOf(value.getDoubleValue()));
        stack.push(result);
    }

    private void exec_sprint()
    {
        TugaValues value = stack.pop();
        checkType(value, TugaTypes.STRING);
        System.out.println(value.getStringValue());
    }

    private void exec_sconcat()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.STRING);
        checkType(left, TugaTypes.STRING);
        TugaValues result = new TugaValues(TugaTypes.STRING, left.getStringValue().concat(right.getStringValue()));
        stack.push(result);
    }

    private void exec_seq()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.STRING);
        checkType(left, TugaTypes.STRING);
        TugaValues result = new TugaValues(TugaTypes.BOOLEAN, left.getStringValue().equals(right.getStringValue()));
        stack.push(result);
    }

    private void exec_sneq()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.STRING);
        checkType(left, TugaTypes.STRING);
        TugaValues result = new TugaValues(TugaTypes.BOOLEAN, !left.getStringValue().equals(right.getStringValue()));
        stack.push(result);
    }

    private void exec_tconst()
    {
        stack.push(new TugaValues(TugaTypes.BOOLEAN, true));
    }

    private void exec_fconst()
    {
        stack.push(new TugaValues(TugaTypes.BOOLEAN, false));
    }

    private void exec_bprint()
    {
        TugaValues value = stack.pop();
        checkType(value, TugaTypes.BOOLEAN);
        if (value.getBooleanValue())
            System.out.println("verdadeiro");
        else
            System.out.println("falso");
    }

    private void exec_beq()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.BOOLEAN);
        checkType(left, TugaTypes.BOOLEAN);
        TugaValues result = new TugaValues(TugaTypes.BOOLEAN, left.getBooleanValue() == right.getBooleanValue());
        stack.push(result);
    }

    private void exec_bneq()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.BOOLEAN);
        checkType(left, TugaTypes.BOOLEAN);
        TugaValues result = new TugaValues(TugaTypes.BOOLEAN, left.getBooleanValue() != right.getBooleanValue());
        stack.push(result);
    }

    private void exec_and()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.BOOLEAN);
        checkType(left, TugaTypes.BOOLEAN);
        TugaValues result = new TugaValues(TugaTypes.BOOLEAN, left.getBooleanValue() && right.getBooleanValue());
        stack.push(result);
    }

    private void exec_or()
    {
        TugaValues right = stack.pop();
        TugaValues left = stack.pop();
        checkType(right, TugaTypes.BOOLEAN);
        checkType(left, TugaTypes.BOOLEAN);
        TugaValues result = new TugaValues(TugaTypes.BOOLEAN, left.getBooleanValue() || right.getBooleanValue());
        stack.push(result);
    }

    private void exec_not()
    {
        TugaValues value = stack.pop();
        checkType(value, TugaTypes.BOOLEAN);
        TugaValues result = new TugaValues(TugaTypes.BOOLEAN, !value.getBooleanValue());
        stack.push(result);
    }

    private void exec_btos()
    {
        TugaValues value = stack.pop();
        checkType(value, TugaTypes.BOOLEAN);
        TugaValues result;
        if (value.getBooleanValue())
            result = new TugaValues(TugaTypes.STRING, "verdadeiro");
        else
            result = new TugaValues(TugaTypes.STRING, "falso");
        stack.push(result);
    }

    private void exec_halt()
    {
        halt = true;
    }

    public void exec_inst(Instruction inst)
    {
        if (trace)
            System.out.println( String.format("%5s: %-15s Stack: %s", ip, inst, stack ) );
        OpCode opc = inst.getOpCode();
        int v;
        switch (opc)
        {
            case iconst:
                v = inst.args()[0];
                exec_iconst(v);
                break;
            case dconst:
                v = inst.args()[0];
                exec_dconst(v);
                break;
            case sconst:
                v = inst.args()[0];
                exec_sconst(v);
                break;
            case iprint:
                exec_iprint();
                break;
            case iuminus:
                exec_iuminus();
                break;
            case iadd:
                exec_iadd();
                break;
            case isub:
                exec_isub();
                break;
            case imult:
                exec_imult();
                break;
            case idiv:
                exec_idiv();
                break;
            case imod:
                exec_imod();
                break;
            case ieq:
                exec_ieq();
                break;
            case ineq:
                exec_ineq();
                break;
            case ilt:
                exec_ilt();
                break;
            case ileq:
                exec_ileq();
                break;
            case itod:
                exec_itod();
                break;
            case itos:
                exec_itos();
                break;
            case dprint:
                exec_dprint();
                break;
            case duminus:
                exec_duminus();
                break;
            case dadd:
                exec_dadd();
                break;
            case dsub:
                exec_dsub();
                break;
            case dmult:
                exec_dmult();
                break;
            case ddiv:
                exec_ddiv();
                break;
            case deq:
                exec_deq();
                break;
            case dneq:
                exec_dneq();
                break;
            case dlt:
                exec_dlt();
                break;
            case dleq:
                exec_dleq();
                break;
            case dtos:
                exec_dtos();
                break;
            case sprint:
                exec_sprint();
                break;
            case sconcat:
                exec_sconcat();
                break;
            case seq:
                exec_seq();
                break;
            case sneq:
                exec_sneq();
                break;
            case tconst:
                exec_tconst();
                break;
            case fconst:
                exec_fconst();
                break;
            case bprint:
                exec_bprint();
                break;
            case beq:
                exec_beq();
                break;
            case bneq:
                exec_bneq();
                break;
            case and:
                exec_and();
                break;
            case or:
                exec_or();
                break;
            case not:
                exec_not();
                break;
            case btos:
                exec_btos();
                break;
            case halt:
                exec_halt();
                break;
            default:
                System.out.println("This should never happen! In file vm.java, method exec_inst()");
                System.exit(1);
        }
    }
}
