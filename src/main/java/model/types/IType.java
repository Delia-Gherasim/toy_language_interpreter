package model.types;

import model.value.IValue;

public interface IType {

    String toString();

    IValue defaultValue();

}
