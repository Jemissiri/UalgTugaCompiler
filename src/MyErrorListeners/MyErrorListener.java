package MyErrorListeners;

import org.antlr.v4.runtime.*;
import java.util.*;

public class MyErrorListener extends BaseErrorListener {
    private boolean showLexerErrors;
    private boolean showParserErrors;
    private boolean showSemanticErrors;
    private int numLexerErrors;
    private int numParsingErrors;
    private int numTypeCheckingErrors;
    private PriorityQueue<Integer> lines; // linhas onde tem erros
    private HashMap<Integer, ArrayList<String>> errors; // hashmap conectando as linhas com as mensagens

    public enum ErrorTypes
    {
        LEXER,
        PARSER,
        SEMANTIC,
        UNKNOWN,
    }

    public MyErrorListener(boolean showLexerErrors, boolean showParserErrors, boolean showSemanticErrors) {
        super();
        this.showLexerErrors = showLexerErrors;
        this.showParserErrors = showParserErrors;
        this.showSemanticErrors = showSemanticErrors;
        this.numLexerErrors = 0;
        this.numParsingErrors = 0;
        this.numTypeCheckingErrors = 0;
        this.lines = new PriorityQueue<Integer>();
        this.errors = new HashMap<Integer, ArrayList<String>>();
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e)
    {
        //System.out.println(msg);
        if (recognizer instanceof Lexer) {
            this.numLexerErrors++;
            if (this.showLexerErrors)
                System.err.printf("line %d:%d error: %s\n", line, charPositionInLine, msg);
        }
        if (recognizer instanceof Parser) {
            this.numParsingErrors++;
            if (this.showParserErrors)
                System.err.printf("line %d:%d error: %s\n", line, charPositionInLine, msg);
        }
    }

    // criar um novo syntaxError para usar no SemanticErrorChecker
    public void syntaxError(ErrorTypes e, int line, int charPositionInLine, String msg)
    {
        switch (e)
        {
            case ErrorTypes.LEXER:
                this.numLexerErrors++;
                if (this.showLexerErrors)
                    addError(line, "erro na linha " + line + ": " + msg);
                    // System.out.println("erro na linha " + line + ": " + msg);
                    // System.err.printf("line %d:%d \nerror: %s\n\n", line, charPositionInLine, msg);
                break;
            case ErrorTypes.PARSER:
                this.numParsingErrors++;
                if (this.showParserErrors)
                    addError(line, "erro na linha " + line + ": " + msg);
                    // System.out.println("erro na linha " + line + ": " + msg);
                    // System.err.printf("line %d:%d \nerror: %s\n\n", line, charPositionInLine, msg);
                break;
            case ErrorTypes.SEMANTIC:
                this.numTypeCheckingErrors++;
                if (this.showSemanticErrors)
                    addError(line, "erro na linha " + line + ": " + msg);
                    // System.out.println("erro na linha " + line + ": " + msg);
                    // System.err.printf("line %d:%d \nerror: %s\n\n", line, charPositionInLine, msg);
                break;
            default:
                throw new IllegalStateException("Syntax error cannot be of type UNKNOWN");
        }
    }

    private void addError(int line, String msg)
    {
        this.lines.add(line);
        if (this.errors.get(line) == null)
            this.errors.put(line, new ArrayList<String>());

        ArrayList<String> lineErrors = this.errors.get(line);
        lineErrors.add(msg);
    }

    public void print()
    {
        while (lines.size() > 0)
        {
            int line = this.lines.remove();
            // remover linhas duplicatas consecutivas do mesmo numero de linha na fila
            while (this.lines.peek() != null && this.lines.peek() == line)
                this.lines.remove();
            ArrayList<String> lineErrors = this.errors.get(line);
            for (String error : lineErrors)
                System.out.println(error);
        }
    }

    public int getNumLexerErrors() { return this.numLexerErrors; }

    public int getNumParsingErrors() { return this.numParsingErrors; }

    public int numTypeCheckingErrors() { return this.numTypeCheckingErrors; }
}

