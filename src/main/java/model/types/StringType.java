package model.types;

import model.value.IValue;
import model.value.StringValue;

public class StringType implements IType {
    public StringType() {
    }

    @Override
    public boolean equals(Object obj) {
       /* if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        return true;*/
        return obj instanceof StringType;
    }


    @Override
    public String toString() {
        return "String";
    }

    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }
}
