package Types;

public class TugaValues
{
    private final TugaTypes type;
    private final Object value;

    public TugaValues(TugaTypes type, Object value)
    {
        this.type = type;
        this.value = value;
    }

    public TugaTypes getType()
    {
        return this.type;
    }

    public Integer getIntValue()
    {
        if (this.type != TugaTypes.INT)
            throw new IllegalStateException("Could not get the INT value from " + this.type + ".");
        return (Integer) this.value;
    }

    public Double getDoubleValue()
    {
        if (this.type != TugaTypes.DOUBLE)
            throw new IllegalStateException("Could not get the DOUBLE value from " + this.type + ".");
        return (Double) this.value;
    }

    public String getStringValue()
    {
        if (this.type != TugaTypes.STRING)
            throw new IllegalStateException("Could not get the STRING value from " + this.type + ".");
        return (String) this.value;
    }

    public Boolean getBooleanValue()
    {
        if (this.type != TugaTypes.BOOLEAN)
            throw new IllegalStateException("Could not get the BOOLEAN value from " + this.type + ".");
        return (Boolean) this.value;
    }

    public String getErrorValue()
    {
        if (this.type != TugaTypes.BOOLEAN)
            throw new IllegalStateException("Could not get the ERROR value from " + this.type + ".");
        return (String) this.value;
    }
}
