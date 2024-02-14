package model.value;

import model.types.IType;
import model.types.RefType;

public class RefValue implements IValue{
    int address;
    IType locationType;
    public RefValue(int adr, IType loc){
        address=adr;
        locationType=loc;
    }
    public RefValue() {
    }

    public int getAddress(){
        return address;
    }

    @Override
    public String toString() {
        return " " + address + " : " + locationType ;
    }

    @Override
    public IType getType() {
       return new RefType(locationType);
    }
    public IType getLocationType(){
        return locationType;
    }
}
