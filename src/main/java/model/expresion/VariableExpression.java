package model.expresion;

import model.types.IType;
import model.utils.IDictionary;
import model.utils.IHeap;
import model.utils.SymbolTable;
import model.value.IValue;
import model.value.IntValue;

public class VariableExpression implements IExpression {
    String id;
    public VariableExpression(String variable) {
        id = variable;
    }
    @Override
    public String toString() {
        return id;
    }
    @Override
    public IValue eval(SymbolTable<String, IValue> symb, IHeap<Integer, IValue> heap) throws Exception {
        if (symb.isDefined(id))
            return symb.lookUp(id);
        else
            symb.put(id, new IntValue(0));
        return symb.lookUp(id);
    }
    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        return typeEnv.lookUp(id);
    }

}
