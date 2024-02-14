package model.statements;

import model.expresion.IExpression;
import model.types.IType;
import model.utils.*;
import model.value.IValue;

public class PrintStatement implements IStatement {
    IExpression expr;

    public PrintStatement(IExpression expression) {
        expr = expression;
    }

    @Override
    public String toString() {
        return "Print(" + expr.toString() + ")"+"\n";
    }

    @Override
    public ProgrState execute(ProgrState state) throws Exception {
        IHeap<Integer,IValue> heap=state.getHeap();
        IList<IValue> output = state.getOut();
        IValue value = expr.eval((SymbolTable<String, IValue >) state.getSymTable(),heap );
        output.add(value);
        state.setOut(output);
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        expr.typecheck(typeEnv);
        return typeEnv;
    }
}
