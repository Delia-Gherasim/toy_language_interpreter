package model.statements;

import model.expresion.IExpression;
import model.expresion.NotExpression;
import model.types.IType;
import model.utils.ExecuteStack;
import model.utils.IDictionary;
import model.utils.IStack;
import model.utils.ProgrState;

public class RepeatUntillStatement implements IStatement{

    IStatement statement;
    IExpression expression;
    public RepeatUntillStatement(IStatement s, IExpression e){
        this.statement=s;
        this.expression=e;
    }
    @Override
    public ProgrState execute(ProgrState state) throws Exception {
        IStatement stmt=new CompoundStatement(
                statement,
                new WhileStatement(new NotExpression(expression), statement)
        );
        IStack<IStatement> exeStack=state.getExeStack();
        exeStack.push(stmt);
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "Repeat: " + statement +" untill: "+ expression +"\n";
    }
}
