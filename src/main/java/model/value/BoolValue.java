package model.value;

import model.types.BoolType;
import model.types.IType;

import java.util.Objects;

public class BoolValue implements IValue {
    boolean val;

    public BoolValue(boolean value) {
        this.val = value;
    }

    public boolean getVal() {
        return val;
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoolValue boolValue)) return false;
        return Objects.equals(val, boolValue.val);
    }*/
   @Override
   public boolean equals(Object obj) {
       if (!(obj instanceof BoolValue))
           return false;
       BoolValue castObj = (BoolValue)obj;
       return val == castObj.val;
   }

    @Override
    public String toString() {
        return " " + val + " ";
    }

    @Override
    public IType getType() {
        return new BoolType();
    }

}
