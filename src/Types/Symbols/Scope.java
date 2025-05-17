package Types.Symbols;

import java.util.*;

public class Scope
{
    Scope parent;
    HashMap<String, Symbol> vars; // guarda symbols (variaveis, funcoes etc) declaradas no scope

    // cria um scope sem parente, ou seja, um scope global
    public Scope() { this(null); }

    // cria um nested scope com o especifico parente
    public Scope(Scope parent)
    {
        this.parent = parent;
        this.vars = new HashMap<String, Symbol>();
    }

    public void addSymbol(Symbol symbol)
    {
        if (this.vars.containsKey(symbol.name()))
            throw new IllegalArgumentException("Scope already contains variable.");

        this.vars.put(symbol.name(), symbol);
    }

    public boolean contains(String name)
    {
        if (this.vars.containsKey(name))
            return true;
        if (this.parent == null)
            return false;
        return this.parent.contains(name);
    }

    public Symbol getSymbol(String name)
    {
        if (this.vars.containsKey(name))
            return this.vars.get(name);
        if (this.parent == null)
            return null;
        return this.parent.getSymbol(name);
    }

    public Scope parent()
    {
        return this.parent;
    }
}
