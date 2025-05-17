package SemanticErrorChecker;

import java.util.*;

import Tuga.*;
import Types.*;
import Types.Symbols.*;



public class SymbolTableBuilder extends TugaBaseVisitor<Void>
{
    private HashMap<String, FunctionSymbol> functions;
    private ArrayList<VariableSymbol> currArgs;

    // inicialza com um hashmap externo para guardar as funcoes
    public SymbolTableBuilder(HashMap<String, FunctionSymbol> functions)
    {
        this.functions = functions;
    }

    // extrair o nome da funcao
    // verifica por declaracoes duplicadas
    // determina o tipo do return(da default em void se nao for especificado)
    // visita a lista de argumentos para ter os parametros
    // cria uma FunctionSymbol e guarda na symbol table
    @Override
    public Void visitFunctionDecl(TugaParser.FunctionDeclContext ctx)
    {
        String name = ctx.VAR().getText();
        if (functions.containsKey(name))
            return null;
        TugaTypes retType = null;
        if (ctx.type != null)
            retType = antlrTypeConvert(ctx.type.getType());
        else
            retType = TugaTypes.VOID;


        currArgs = new ArrayList<VariableSymbol>();
        if (ctx.arg_list() != null)
            visit(ctx.arg_list());
        VariableSymbol[] args = new VariableSymbol[currArgs.size()];
        args = currArgs.toArray(args);

        FunctionSymbol fn = new FunctionSymbol(name, retType, args);
        fn.setLine(ctx.start.getLine());
        functions.put(name, fn);

        return null;
    }

    // extrai o nome do parametro(exemplo: "x: inteiro", vai extrair o x)
    // converte o tipo do ANTLR para um TugaType
    // cria um VariableSymbol e adiciona no currArgs.
    @Override
    public Void visitDeclArg(TugaParser.DeclArgContext ctx)
    {
        String name = ctx.VAR().getText();
        TugaTypes type = antlrTypeConvert(ctx.type.getType());

        VariableSymbol arg = new VariableSymbol(name, type);
        currArgs.add(arg);

        return null;
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

}
