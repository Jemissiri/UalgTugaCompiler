package MyErrorListeners;

import org.antlr.v4.runtime.*;

public class MyErrorListener extends BaseErrorListener {
    private boolean showLexerErrors;
    private boolean showParserErrors;
    private boolean showSemanticErrors;
    private int numLexerErrors;
    private int numParsingErrors;
    private int getNumTypeCheckingErrors;

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
        this.getNumTypeCheckingErrors = 0;
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
                    System.err.printf("line %d:%d \nerror: %s\n\n", line, charPositionInLine, msg);
                break;
            case ErrorTypes.PARSER:
                this.numParsingErrors++;
                if (this.showParserErrors)
                    System.err.printf("line %d:%d \nerror: %s\n\n", line, charPositionInLine, msg);
                break;
            case ErrorTypes.SEMANTIC:
                this.getNumTypeCheckingErrors++;
                if (this.showSemanticErrors)
                    System.err.printf("line %d:%d \nerror: %s\n\n", line, charPositionInLine, msg);
                break;
            default:
                throw new IllegalStateException("Syntax error cannot be of type UNKNOWN");
        }
    }

    public int getNumLexerErrors() { return this.numLexerErrors; }

    public int getNumParsingErrors() { return this.numParsingErrors; }

    public int getNumTypeCheckingErrors() { return this.getNumTypeCheckingErrors; }
}

