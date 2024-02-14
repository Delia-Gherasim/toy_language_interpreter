package model.expresion;

import model.exceptions.ExceptionExpr;
import model.types.IType;
import model.types.IntType;
import model.utils.IDictionary;
import model.utils.IHeap;
import model.utils.SymbolTable;
import model.value.IValue;
import model.value.IntValue;

public class ArithmeticExpression implements IExpression {
    ArithmOp op;
    IExpression e1;
    IExpression e2;

    public ArithmeticExpression(IExpression value1, ArithmOp operator, IExpression value2) {
        e1 = value1;
        op = operator;
        e2 = value2;
    }

    @Override
    public String toString() {
        return " " + e1.toString() + " " + op + " " + e2.toString() + " ";
    }

    @Override
    public IValue eval(SymbolTable<String, IValue> symb, IHeap<Integer, IValue> heap) throws Exception {
        IValue v1 = e1.eval(symb, heap);
        IValue v2 = e2.eval(symb, heap);
        int n1, n2;
        if (!(v1.getType().equals(new IntType()))) {
            throw new ExceptionExpr("First operand is not an int");}
        if (!(v2.getType().equals(new IntType()))) {
            throw new Exception("Second operand is not an int");}
        n1 = ((IntValue) v1).getVal();
        n2 = ((IntValue) v2).getVal();

        switch (op) {
            case ADD:
                return new IntValue(n1 + n2);
            case MINUS:
                return new IntValue(n1 - n2);
            case DIVIDE:
                if (n2 == 0) {
                    throw new ExceptionExpr("cannot divide by 0");
                }
                return new IntValue(n1 / n2);
            case MULTIPLY:
                return new IntValue(n1 * n2);
            default:
                throw new ExceptionExpr("Operation isn't valid");

        }
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        IType t1, t2;
        t1=e1.typecheck(typeEnv);
        t2=e2.typecheck(typeEnv);
        if (t1.equals(new IntType())){
            if (t2.equals(new IntType())){
                return new IntType();
            }
            else
                throw new ExceptionExpr("typecheck: second operand is not an integer");
        }
        else  throw new ExceptionExpr("typecheck: first operand is not an integer");
    }


}