package model.statements;

import model.exceptions.ExceptionStatement;
import model.expresion.IExpression;
import model.expresion.RelationEnum;
import model.expresion.RelationalExpression;
import model.types.IType;
import model.types.IntType;
import model.utils.*;
import model.value.IValue;

public class ForStatement implements IStatement{
    IExpression expression1;
    IExpression expression2;
    IExpression expression3;
    IExpression var;
    public ForStatement(IExpression var, IExpression expression1, IExpression expression2, IExpression expression3){
        this.expression1=expression1;
        this.expression2=expression2;
        this.expression3=expression3;
        this.var=var;
    }

    @Override
    public ProgrState execute(ProgrState state) throws Exception {
        IStack<IStatement> exeStack= state.getExeStack();
        String variable= var.toString();
        IStatement stmt=new CompoundStatement(
                new AssignmentStatement(variable, expression1),
                new WhileStatement(
                        new RelationalExpression(var, RelationEnum.LESS_THAN, expression2),
                        new AssignmentStatement(variable, expression3)));
        exeStack.push(stmt);
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "For(" +var+"="+expression1+" ; "+var+"<"+expression2+" ; "+var+"="+expression3+")"+"\n";
    }
}
