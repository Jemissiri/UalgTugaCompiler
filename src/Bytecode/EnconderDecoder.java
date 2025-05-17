package Bytecode;

import java.io.*;

import Types.TugaTypes;
import Types.TugaValues;
import Types.TugaConstTypes;
import VM.Instruction;

public class EnconderDecoder
{
    private TugaValues[] constantPool;
    private Instruction[] instructions;

    public EnconderDecoder(byte[] bytecodes)
    {
        decode(bytecodes);
    }

    public EnconderDecoder(TugaValues[] constantPool, Instruction[] instructions)
    {
        this.constantPool = constantPool;
        this.instructions = instructions;
    }

    public TugaValues[] getConstantPool()
    {
        return this.constantPool;
    }

    public Instruction[] getInstructions()
    {
        return this.instructions;
    }

    private void decode(byte[] bytecodes)
    {
        try(DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytecodes)))
        {
            decodeConstantPool(in);
            decodeInstructions(in);
        }
        catch (IOException e)
        {
            System.out.println("Error on decoding bytecodes:\n" + e.getMessage());
        }
    }

    private void decodeConstantPool(DataInputStream in) throws IOException
    {
        int numConstants = in.readInt();
        this.constantPool = new TugaValues[numConstants];

        for (int i = 0; i < numConstants; i++)
        {
            TugaValues value;

            TugaConstTypes constType = TugaConstTypes.values()[in.readByte()];
            if (constType == TugaConstTypes.DOUBLE)
            {
                double d = in.readDouble();
                value = new TugaValues(TugaTypes.DOUBLE, d);
            }
            else if (constType == TugaConstTypes.STRING)
            {
                int strLength = in.readInt();
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < strLength; j++)
                    builder.append(in.readChar());
                String str = builder.toString();
                value = new TugaValues(TugaTypes.STRING, str);
            }
            else
                throw new IllegalStateException("Unknown constant type.");

            this.constantPool[i] = value;
        }
    }

    private void decodeInstructions(DataInputStream in) throws IOException
    {
        int numInstructions = in.readInt();
        this.instructions = new Instruction[numInstructions];
        for (int i = 0; i < numInstructions; i++)
            this.instructions[i] = Instruction.readFrom(in);
    }

    public byte[] encode()
    {
        byte[] bytes = null;
        try(ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(byteOut))
        {
            encodeConstantPool(out);
            encodeInstructions(out);
            bytes = byteOut.toByteArray();
        }
        catch (IOException e)
        {
            System.out.println("Error on decoding bytecodes:\n" + e.getMessage());
        }
        return bytes;
    }

    public void encodeConstantPool(DataOutputStream out) throws IOException
    {
        out.writeInt(constantPool.length);
        for (int i = 0; i < constantPool.length; i++)
        {
            if (constantPool[i].getType() == TugaTypes.DOUBLE)
            {
                out.writeByte(TugaConstTypes.DOUBLE.ordinal());
                out.writeDouble(constantPool[i].getDoubleValue());
            }
            else if (constantPool[i].getType() == TugaTypes.STRING)
            {
                out.writeByte(TugaConstTypes.STRING.ordinal());
                String str = constantPool[i].getStringValue();
                out.writeInt(str.length());
                for (int j = 0; j < str.length(); j++)
                    out.writeChar(str.charAt(j));
            }
            else
                throw new IllegalStateException("Cannot assign this type to a constant.");
        }
    }

    public void encodeInstructions(DataOutputStream out) throws IOException
    {
        out.writeInt(this.instructions.length);
        for (Instruction inst : instructions)
            inst.writeTo(out);
    }


}
