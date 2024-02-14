package model.expresion;

import model.exceptions.ExceptionExpr;
import model.types.IType;
import model.types.IntType;
import model.utils.IDictionary;
import model.utils.IHeap;
import model.utils.SymbolTable;
import model.value.IValue;

public class MULExpression implements IExpression{
    private final IExpression expression1;
    private final IExpression expression2;

    public MULExpression(IExpression expression1, IExpression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        IType type1 = expression1.typecheck(typeEnv);
        IType type2 = expression2.typecheck(typeEnv);
        if (type1.equals(new IntType()) && type2.equals(new IntType()))
            return new IntType();
        else
            throw new ExceptionExpr("Expressions in the MUL should be int");
    }

    @Override
    public IValue eval(SymbolTable<String, IValue> symb, IHeap<Integer, IValue> heap) throws Exception {
        IExpression converted = new ArithmeticExpression(
                new ArithmeticExpression(expression1, ArithmOp.MULTIPLY, expression2),
                ArithmOp.MINUS,
                new ArithmeticExpression(expression1, ArithmOp.ADD, expression2));
        return converted.eval((SymbolTable<String, IValue>) symb, heap);
    }


    @Override
    public String toString() {
        return "MUL("+ expression1 +expression2+" )";
    }

}
