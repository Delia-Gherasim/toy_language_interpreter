package model.expresion;

import model.types.IType;
import model.utils.IDictionary;
import model.utils.IHeap;
import model.utils.SymbolTable;
import model.value.BoolValue;
import model.value.IValue;

public class NotExpression implements IExpression{
    private final IExpression expression;

    public NotExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        return expression.typecheck(typeEnv);
    }

    @Override
    public IValue eval(SymbolTable<String, IValue> symb, IHeap<Integer, IValue> heap) throws Exception {
        BoolValue value = (BoolValue) expression.eval(symb, heap);
        if (!value.getVal())
            return new BoolValue(true);
        else
            return new BoolValue(false);
    }

    @Override
    public String toString() {
        return "!"+ expression;
    }


}