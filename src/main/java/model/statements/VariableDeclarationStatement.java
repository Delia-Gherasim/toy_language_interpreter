package model.statements;

import model.exceptions.ExceptionDict;
import model.types.IType;
import model.utils.IDictionary;
import model.utils.ProgrState;
import model.value.IValue;

public class VariableDeclarationStatement implements IStatement {
    String name;
    IType type;

    public VariableDeclarationStatement(String name, IType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString() + " " + name+"\n";
    }

    @Override
    public ProgrState execute(ProgrState state) throws Exception {
        IDictionary<String, IValue> symbolTable = state.getSymTable();
        if (!symbolTable.isDefined(name)) {
            symbolTable.put(name, type.defaultValue());
        } else throw new ExceptionDict("symbol is not defined");
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        typeEnv.put(name, type);
        return typeEnv;
    }
}

