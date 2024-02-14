package model.statements;

import model.exceptions.ExceptionStatement;
import model.expresion.IExpression;
import model.expresion.RelationEnum;
import model.expresion.RelationalExpression;
import model.types.IType;
import model.utils.*;
import model.value.BoolValue;
import model.value.IValue;

public class SwitchStatement implements IStatement{
    IStatement s1;
    IStatement s2;
    IStatement s3;
    IExpression expr;
    IExpression e1;
    IExpression e2;
    public SwitchStatement(IExpression expression, IExpression expression1,  IStatement statement1, IExpression expression2, IStatement statement2, IStatement statement3 ){
        this.s1=statement1;
        this.s2=statement2;
        this.s3=statement3;
        this.expr=expression;
        this.e1=expression1;
        this.e2=expression2;
    }
    @Override
    public ProgrState execute(ProgrState state) throws Exception {
        IDictionary<String, IValue> symbolTbl = state.getSymTable();
        IHeap<Integer, IValue> heap = state.getHeap();
        IStack<IStatement> exeStack=state.getExeStack();
        SymbolTable<String, IValue> symbolTable= (SymbolTable<String, IValue>) symbolTbl;
        IStatement stmt= new IfStatement(
                new RelationalExpression(expr, RelationEnum.EQUAL, e1), s1, new IfStatement(
                        new RelationalExpression(expr, RelationEnum.EQUAL, e2),s2, s3));
        exeStack.push(stmt);
        /*
        BoolValue boolVal= (BoolValue) new RelationalExpression(expr, RelationEnum.EQUAL, e1).eval(symbolTable, heap);
        if (boolVal.equals(new BoolValue(true)))
            exeStack.push(s1);
        else {
            boolVal= (BoolValue) new RelationalExpression(expr, RelationEnum.EQUAL, e2).eval(symbolTable, heap);
            if (boolVal.equals(new BoolValue(true)))
                exeStack.push(s2);
            else
                exeStack.push(s3);
        }*/
        return null;
    }

    @Override
    public String toString() {
        return "Switch(" +expr+")"+"\n"+
                "case "+e1+": "+s1+
                "case "+e2+": "+s3+
                "default: "+s3;

    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        if(expr.typecheck(typeEnv).equals(e1.typecheck(typeEnv)) && e1.typecheck(typeEnv).equals(e2.typecheck(typeEnv))){
            s1.typecheck(typeEnv);
            s2.typecheck(typeEnv);
            s3.typecheck(typeEnv);
        }
        else{
            throw new ExceptionStatement("The expressions"+expr+ " "+expr.typecheck(typeEnv)+" \n "
                    +e1+" "+e1.typecheck(typeEnv)+" \n "+
                    e2+" "+e2.typecheck(typeEnv)+"\n"+
                    " have different types");
        }
        return typeEnv;
    }
}
