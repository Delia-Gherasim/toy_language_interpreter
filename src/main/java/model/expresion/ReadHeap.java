package model.expresion;

import model.exceptions.ExceptionExpr;
import model.types.IType;
import model.types.RefType;
import model.utils.IDictionary;
import model.utils.IHeap;
import model.utils.SymbolTable;
import model.value.IValue;
import model.value.RefValue;


public class ReadHeap implements IExpression{
    IExpression exp;
    public ReadHeap(IExpression expression){
        this.exp=expression;
    }
    @Override
    public String toString() {
        return exp.toString();
    }

    @Override
    public IValue eval(SymbolTable<String, IValue> symb, IHeap<Integer, IValue> heap) throws Exception {
        IValue expressionValue = exp.eval(symb, heap);
        if (!(expressionValue instanceof RefValue)) {
            throw new ExceptionExpr("Expression is not RefValue");
        }
        else{
        RefValue value = (RefValue) expressionValue;
        int address = value.getAddress();

        if (!heap.isDefined(address)) {
            System.out.println(value);
            System.out.println(address);
            System.out.println(expressionValue);
            System.out.println(heap);
         throw new ExceptionExpr("The address is not a key in the heap table");
        }
        else
            return heap.lookUp(address);
        }
    }
    @Override
    public IType typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        IType type=exp.typecheck(typeEnv);
        if (type instanceof RefType){
            RefType reft=(RefType) type;
            return reft.getInner();
        }
        else
            throw new ExceptionExpr("the readHeap argument is not a Ref Type");
    }
}
