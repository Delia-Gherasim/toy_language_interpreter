package model.types;

import model.value.IValue;
import model.value.IntValue;

public class IntType implements IType {

    public IntType() {
    }

   @Override
   public boolean equals(Object obj) {
      /* if (this == obj)
           return true;
       if (obj == null || getClass() != obj.getClass())
           return false;

       return true;*/
       return obj instanceof IntType;
   }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public IValue defaultValue() {
        return new IntValue(0);
    }
}
