package model.statements;

import model.types.IType;
import model.utils.IDictionary;
import model.utils.IStack;
import model.utils.ProgrState;

public class CompoundStatement implements IStatement {
    IStatement statement1;
    IStatement statement2;

    public CompoundStatement(IStatement stm1, IStatement stm2) {
        statement1 = stm1;
        statement2 = stm2;
    }

    @Override
    public String toString() {
        return statement1.toString() + statement2.toString() ;
    }

    @Override
    public ProgrState execute(ProgrState state) throws Exception {
        IStack<IStatement> stack = state.getExeStack();
        stack.push(statement2);
        stack.push(statement1);
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        //IDictionary<String,IType> typEnv1 = statement1.typecheck(typeEnv);
        //IDictionary<String,IType> typEnv2 = statement2.typecheck(typEnv1);
        //return typEnv2;
        return statement2.typecheck(statement1.typecheck(typeEnv));
    }
}
