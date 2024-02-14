package model.statements;

import model.types.IType;
import model.utils.IDictionary;
import model.utils.ProgrState;

public class NoOperationStatement implements IStatement {
    public NoOperationStatement() {

    }

    @Override
    public ProgrState execute(ProgrState state) throws Exception {
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        return null;
    }

    @Override
    public String toString() {
        return "NoOperation()"+"\n";
    }
}
