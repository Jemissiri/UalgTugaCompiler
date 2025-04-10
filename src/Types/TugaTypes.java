package Types;

public enum TugaTypes
{
    INT()
    {
        @Override
        public boolean isNumeric() { return true; }
    },
    DOUBLE()
    {
        @Override
        public boolean isNumeric() { return true; }
    },
    STRING()
    {
        @Override
        public boolean isNumeric() { return false; }
    },
    BOOLEAN()
    {
        @Override
        public boolean isNumeric() { return false; }
    },
    ERROR()
    {
        @Override
        public boolean isNumeric() { return false; }
    };

    public abstract boolean isNumeric();
}
