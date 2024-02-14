package model.expresion;


import model.exceptions.ExceptionExpr;
import model.types.BoolType;
import model.types.IType;
import model.types.IntType;
import model.utils.IDictionary;
import model.utils.IHeap;
import model.utils.SymbolTable;
import model.value.BoolValue;
import model.value.IValue;

public class LogicExpression implements IExpression {
    IExpression e1, e2;
    LogicOperator operator;

    public LogicExpression(IExpression operand1, LogicOperator operator, IExpression operand2) {
        e1 = operand1;
        e2 = operand2;

    }

    @Override
    public String toString() {
        return " " + e1 + " " + operator + " " + e2 + " ";
    }

    @Override
    public IValue eval(SymbolTable<String, IValue> symb, IHeap<Integer, IValue> heap) throws Exception {
        IValue v1;
        IValue v2;
        v1 = e1.eval(symb, heap);
        v2 = e2.eval(symb, heap);
        if (!(v1.getType().equals(new BoolType()))) {
            throw new ExceptionExpr("first operand is not boolean");}
        if (!(v2.getType().equals(new BoolType()))) {
            throw new ExceptionExpr("second operand is not boolean");}
        BoolValue b1 = (BoolValue) v1;
        BoolValue b2 = (BoolValue) v2;
        boolean n1 = b1.getVal();
        boolean n2 = b2.getVal();
        switch (operator) {
            case OR:
                return new BoolValue(n1||n2);
            case AND:
                return new BoolValue(n1 & n2);
            default:
                throw new ExceptionExpr("operation isn't valid");
        }
    }

    public IType typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        IType t1, t2;
        t1=e1.typecheck(typeEnv);
        t2=e2.typecheck(typeEnv);
        if (t1.equals(new BoolType())){
            if (t2.equals(new BoolType())){
                return new BoolType();
            }
            else
                throw new ExceptionExpr("typecheck: second operand is not a boolean");
        }
        else  throw new ExceptionExpr("typecheck: first operand is not a boolean");
    }

}


