package model.statements;

import model.exceptions.ExceptionStatement;
import model.expresion.IExpression;
import model.types.IType;
import model.types.RefType;
import model.utils.*;
import model.value.IValue;
import model.value.RefValue;

public class New implements IStatement {
    String var_name;
    IExpression expr;
    public New(String var, IExpression expression) {
        var_name = var;
        expr = expression;
    }
    @Override
    public String toString() {
        return "New( " + var_name + " " + expr +" )"+"\n";
    }
    @Override
    public ProgrState execute(ProgrState state) throws Exception {
        IDictionary<String, IValue> symbolTable = state.getSymTable();
        IHeap<Integer, IValue> heap = state.getHeap();
        SymbolTable<String, IValue> symb = (SymbolTable<String,  IValue>) state.getSymTable();
        if (!symbolTable.isDefined(var_name)) {
            throw new ExceptionStatement("New: Variable Name is not defined");
        } else {
            IValue varValue = symbolTable.lookUp(var_name);
            if (!(varValue instanceof RefValue)) {
                throw new ExceptionStatement("New: Variable "+var_name+" is not of type RefType");
            } else {
                IValue expresionValue = expr.eval(symb, heap);
                RefType type =(RefType) varValue.getType();
                IType tipul=((RefType) varValue.getType()).getInner();
                if(!(tipul.equals(type.getInner())))
                    throw new ExceptionStatement("plm");
                int currentAdr=heap.getLastAddress();
                currentAdr++;
                RefType nouRefType=new RefType(expresionValue.getType());
                RefValue nouRefVal=new RefValue(currentAdr, nouRefType);
                symbolTable.update(var_name, nouRefVal);
                heap.put(currentAdr, expresionValue);
                heap.updateLastAddress(currentAdr);
            }
        }
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        IType typeVar=typeEnv.lookUp(var_name);
        IType typeExpr= expr.typecheck(typeEnv);
        if (typeVar.equals(new RefType(typeExpr))){
                return typeEnv;
        }
        else {
            System.out.println(var_name);
            System.out.println(expr);
            throw new ExceptionStatement("New stmt: right hand side " + typeVar + " and left hand " + typeExpr + " side have different types");
        }
    }
}


