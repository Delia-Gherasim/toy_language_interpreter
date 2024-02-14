package model.expresion;

import model.types.IType;
import model.utils.IDictionary;
import model.utils.IHeap;
import model.utils.SymbolTable;
import model.value.IValue;

public interface IExpression {

    String toString();

    IValue eval(SymbolTable<String, IValue> symb, IHeap<Integer, IValue> heap) throws Exception;
    IType typecheck(IDictionary<String, IType> typeEnv) throws Exception;
}
