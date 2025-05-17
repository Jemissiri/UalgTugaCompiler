import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

import Tuga.*;
import CodeGenerator.CodeGen;
import MyErrorListeners.*;
import SemanticErrorChecker.*;
import Types.*;
import Types.Symbols.*;
import Bytecode.*;
import VM.*;

public class TugaCompileAndRun
{
    public static final String BytecodesFile = "bytecodes.bc";

    public static boolean showLexerErrors;
    public static boolean showParserErrors;
    public static boolean showTypeCheckingErrors;
    public static boolean showTrace;
    public static boolean showAsm;
    public static boolean dumps; // para o mooshak

    public static void main(String[] args) throws Exception
    {
        showLexerErrors = false;
        showParserErrors = false;
        showTypeCheckingErrors = true;
        showTrace = false;
        showAsm = false;
        dumps= true;

        String inputFile = null;
        if (args.length > 0)
        {
            inputFile = args[0];
            for (int i = 0; i < args.length; i++)
            {
                if (args[i].equals("-trace"))
                    showTrace = true;
                if (args[i].equals("-asm"))
                    showAsm = true;
            }
        }
        InputStream is = System.in;
        try
        {
            if (inputFile != null)
                is = new FileInputStream(inputFile);
            CharStream input = CharStreams.fromStream(is);
            MyErrorListener errorListener = new MyErrorListener(showLexerErrors, showParserErrors, showTypeCheckingErrors);

            TugaLexer lexer = new TugaLexer(input);
            lexer.removeErrorListeners();
            lexer.addErrorListener( errorListener );
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            TugaParser parser = new TugaParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener( errorListener );
            ParseTree tree = parser.tuga();

            HashMap<String, FunctionSymbol> functions = new HashMap<String, FunctionSymbol>();
            new SymbolTableBuilder(functions).visit(tree);
            ParseTreeProperty<TugaTypes> types = new ParseTreeProperty<TugaTypes>();
            HashMap<String, TugaTypes> varTypes = new HashMap<String, TugaTypes>();
            SemanticErrorChecker semanticErrorChecker = new SemanticErrorChecker(types, varTypes, functions);
            semanticErrorChecker.removeErrorListener();
            semanticErrorChecker.addErrorListener(errorListener);
            semanticErrorChecker.visit(tree);

            errorListener.print();
            if (errorListener.getNumLexerErrors() > 0)
            {
                System.out.println("Input has lexical errors");
                return;
            }
            if (errorListener.getNumParsingErrors() > 0)
            {
                System.out.println("Input has parsing errors");
                return;
            }
            if (errorListener.numTypeCheckingErrors() > 0)
            {
                //System.out.println("Input has type checking errors");
                return;
            }

            CodeGen codeGen = new CodeGen(types, varTypes, functions);
            codeGen.visit(tree);
            if (showAsm)
                codeGen.dumpCode();
            byte[] bytecodes = codeGen.getBytecode();
            Bytes.write(bytecodes, BytecodesFile);
            bytecodes = Bytes.read(BytecodesFile);
            VirtualMachine vm = new VirtualMachine(bytecodes, showTrace);
            if (dumps)
            {
                System.out.println("*** Constant pool ***");
                vm.dumpConstantPool();
                System.out.println("*** Instructions ***");
                vm.dumpInstructions();
                System.out.println("*** VM output ***");
            }
            vm.run();

        }
        catch (java.io.IOException e)
        {
            System.out.println(e);
        }
    }
}
