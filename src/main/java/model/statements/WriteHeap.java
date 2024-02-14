package model.statements;

import model.exceptions.ExceptionStatement;
import model.expresion.IExpression;
import model.types.IType;
import model.types.RefType;
import model.utils.IDictionary;
import model.utils.IHeap;
import model.utils.ProgrState;
import model.utils.SymbolTable;
import model.value.IValue;
import model.value.RefValue;

public class WriteHeap implements IStatement {
    String var_name;
    IExpression expr;
    public WriteHeap(String name, IExpression exp) {
        this.var_name = name;
        this.expr = exp;
    }
    @Override
    public String toString() {
        return var_name + "->" + expr +"\n";
    }
    @Override
    public ProgrState execute(ProgrState state) throws Exception {
        IDictionary<String, IValue> symbolTable = state.getSymTable();
        IHeap<Integer, IValue> heap = state.getHeap();

        if (!symbolTable.isDefined(var_name)) {
            throw new ExceptionStatement("WriteHeap: Variable Name is not defined");
        } else {
            SymbolTable<String, IValue> symb = (SymbolTable<String, IValue>) state.getSymTable();
            IValue varValue = symbolTable.lookUp(var_name);

            if (!(varValue.getType() instanceof RefType)) {
                throw new ExceptionStatement("WriteHeap: Variable is not of type RefType");
            } else {
                int address = ((RefValue) varValue).getAddress();
                if (!heap.isDefined(address)) {
                    throw new ExceptionStatement("WriteHeap: Invalid heap address");
                } else {
                    IValue exprValue = expr.eval(symb, heap);
                    RefValue referenceValue = (RefValue)varValue;
                    heap.update(address, exprValue);
                }
            }
        }
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        IType typeVar=typeEnv.lookUp(var_name);
        IType typeExpr= expr.typecheck(typeEnv);
        if(!(typeVar instanceof RefType))
            throw new ExceptionStatement("Write heap aoleo");
        if (!typeExpr.equals(((RefType) typeVar).getInner()))
            throw new ExceptionStatement("Write heap aoleo 2");
        /*
        if (typeVar.equals(new RefType(typeExpr))){
            return typeEnv;
        }
        else
            throw new ExceptionStatement("Write heap stmt fail");
        }*/
        return typeEnv;
    }
}
