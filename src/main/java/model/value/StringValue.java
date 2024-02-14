package model.value;

import model.types.IType;
import model.types.StringType;

import java.util.Objects;

public class StringValue implements IValue {

    String value;

    public StringValue(String val) {
        value = val;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return " " + value + " ";
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringValue stringValue)) return false;
        return Objects.equals(value, stringValue.value);
    }
}
