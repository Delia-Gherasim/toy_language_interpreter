package model.statements;

import model.types.IType;
import model.types.IntType;
import model.utils.IDictionary;
import model.utils.ProgrState;

public interface IStatement {
    String toString();
    ProgrState execute(ProgrState state) throws Exception;
    IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws Exception;
}
