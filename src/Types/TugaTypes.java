package Types;

public enum TugaTypes
{
    INT()
    {
        @Override
        public boolean isNumeric() { return true; }
        @Override
        public String toString() { return "inteiro"; }
    },
    DOUBLE()
    {
        @Override
        public boolean isNumeric() { return true; }
        @Override
        public String toString() { return "real"; }
    },
    STRING()
    {
        @Override
        public boolean isNumeric() { return false; }
        @Override
        public String toString() { return "string"; }
    },
    BOOLEAN()
    {
        @Override
        public boolean isNumeric() { return false; }
        @Override
        public String toString() { return "booleano"; }
    },
    ERROR()
    {
        @Override
        public boolean isNumeric() { return false; }
        @Override
        public String toString() { return "error"; }
    },
    NULL()
    {
        @Override
        public boolean isNumeric() { return false; }
        @Override
        public String toString() { return "nulo"; }
    },
    VOID()
    {
        @Override
        public boolean isNumeric() { return false; }

        @Override
        public String toString() { return "vazio"; }
    };

    public abstract boolean isNumeric();
}
