package model.statements;

import model.exceptions.ExceptionStatement;
import model.expresion.IExpression;
import model.types.BoolType;
import model.types.IType;
import model.utils.*;
import model.value.BoolValue;
import model.value.IValue;

import java.util.Map;

public class WhileStatement implements  IStatement{
    IExpression expr;
    IStatement statement;
    public WhileStatement(IExpression e, IStatement s){
        this.expr=e;
        this.statement=s;
    }

    @Override
    public String toString() {
        return "While (" + expr +")"+"\n"+ statement ;
    }

    @Override
    public ProgrState execute(ProgrState state) throws Exception {
        SymbolTable<String, IValue> symTable= (SymbolTable<String, IValue>) state.getSymTable();
        IHeap<Integer, IValue> heap=state.getHeap();
        IStack<IStatement> exeStack= state.getExeStack();

        BoolValue bool= (BoolValue) expr.eval(symTable, heap);
        if (bool.getVal()){
            exeStack.push(this);
            exeStack.push(statement);
        }
        return null;
    }
    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        IType typeExpr=expr.typecheck(typeEnv);
        if(typeExpr.equals(new BoolType())){
            statement.typecheck(clone(typeEnv));
            return typeEnv;
        }
        else throw new ExceptionStatement("The condition of While has not the type bool");

    }
    private IDictionary<String, IType> clone(IDictionary<String, IType> original) {
        IDictionary<String, IType> cloned = new SymbolTable<>();
        for (Map.Entry<String, IType> entry : original.entrySet()) {
            cloned.put(entry.getKey(), entry.getValue());
        }
        return cloned;
    }
}
