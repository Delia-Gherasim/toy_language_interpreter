package model.value;

import model.types.IType;
import model.types.IntType;

import java.util.Objects;

public class IntValue implements IValue {
    int val;

    public IntValue(int value) {
        this.val = value;
    }

    @Override
    public String toString() {
        return " " + val + " ";
    }

    public int getVal() {
        return val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntValue intValue)) return false;
        return Objects.equals(val, intValue.val);
    }


    @Override
    public IType getType() {
        return new IntType();
    }
}
