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

    public int getIntValue()
    {
        if (this.type != TugaTypes.INT)
            throw new IllegalStateException("Could not get the INT value from " + this.type + ".");
        return (Integer) this.value;
    }

    public double getDoubleValue()
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

    public boolean getBooleanValue()
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

    @Override
    public String toString()
    {
        String result;
        switch (this.type)
        {
            case TugaTypes.INT:
                result = String.valueOf(getIntValue());
                break;
            case TugaTypes.DOUBLE:
                result = String.valueOf(getDoubleValue());
                break;
            case TugaTypes.STRING:
                result = "\"" + getStringValue() + "\"";
                break;
            case TugaTypes.BOOLEAN:
                result = String.valueOf(getBooleanValue());
                break;
            case TugaTypes.ERROR:
                result = "ERROR";
                break;
            case TugaTypes.NULL:
                result = "NULL";
                break;
            default:
                result = "UNKNOWN";
                break;
        }
        return result;
    }

    @Override
    public int hashCode() {
        switch (this.type) {
            case INT:
                return 31 * Integer.hashCode(this.getIntValue());
            case DOUBLE:
                return 127 * Double.hashCode(this.getDoubleValue());
            case STRING:
                return 257 * this.getStringValue().hashCode();
            case BOOLEAN:
                return 521 * Boolean.hashCode(this.getBooleanValue());
            default:
                throw new IllegalStateException("Unsupported type: " + this.type);
        }
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other) return true;
        if (other == null) return false;
        if (this.getClass() != other.getClass()) return false;
        TugaValues that = (TugaValues) other;
        if (this.type != that.type)
            return false;
        else if (this.type == TugaTypes.INT && this.getIntValue() != that.getIntValue())
            return false;
        else if (this.type == TugaTypes.DOUBLE && this.getDoubleValue() != that.getDoubleValue())
            return false;
        else if (this.type == TugaTypes.STRING && !this.getStringValue().equals(that.getStringValue()))
            return false;
        else if (this.type == TugaTypes.BOOLEAN && this.getBooleanValue() != that.getBooleanValue())
            return false;
        return true;
    }
}
