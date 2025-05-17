package Types.Symbols;

import Types.*;

public class Symbol
{
    private String name;
    private TugaTypes type;

    public Symbol(String name, TugaTypes type)
    {
        this.name = name;
        this.type = type;
    }

    public TugaTypes type() { return this.type; }
    public String name() { return this.name; }
}
