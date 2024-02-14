package model.types;

import model.value.BoolValue;
import model.value.IValue;

public class BoolType implements IType {
    public BoolType() {
    }

    @Override
    public boolean equals(Object obj) {
       /* if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        return true;*/
        return obj instanceof BoolType;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public IValue defaultValue() {
        return new BoolValue(false);
    }
}
