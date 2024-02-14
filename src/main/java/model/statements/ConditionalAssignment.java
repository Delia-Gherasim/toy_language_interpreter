package model.statements;

import model.exceptions.ExceptionStatement;
import model.expresion.IExpression;
import model.expresion.VariableExpression;
import model.types.BoolType;
import model.types.IType;
import model.utils.IDictionary;
import model.utils.IStack;
import model.utils.ProgrState;

public class ConditionalAssignment implements IStatement{
    String variable;
    IExpression expression1;
    IExpression expression2;
    IExpression expression3;
    public ConditionalAssignment(String variable, IExpression expression1, IExpression expression2, IExpression expression3){
        this.variable=variable;
        this.expression1=expression1;
        this.expression2=expression2;
        this.expression3=expression3;
    }
    @Override
    public ProgrState execute(ProgrState state) throws Exception {
        IStack<IStatement> exeStack= state.getExeStack();
        IStatement statement=new IfStatement(expression1, new AssignmentStatement(variable, expression2), new AssignmentStatement(variable, expression3));
        exeStack.push(statement);
        state.setExeStack(exeStack);
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        IType variableType=new VariableExpression(variable).typecheck(typeEnv);
        IType type1=expression1.typecheck(typeEnv);
        IType type2=expression2.typecheck(typeEnv);
        IType type3=expression3.typecheck(typeEnv);
        if (type1.equals(new BoolType()) && type2.equals(variableType) && type3.equals(variableType))
            return typeEnv;
        else
            throw new ExceptionStatement("The conditional assignment operands have different types");
    }

    @Override
    public String toString() {
        return "ConditionalAssignment(" + variable + " = ( " + expression1 +  " ) ? "+ expression2 + " : " + expression3 + ")"+"\n";
    }
}
