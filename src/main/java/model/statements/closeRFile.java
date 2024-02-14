package model.statements;

import model.exceptions.ExceptionStatement;
import model.expresion.IExpression;
import model.types.BoolType;
import model.types.IType;
import model.types.StringType;
import model.utils.*;
import model.value.IValue;
import model.value.StringValue;

import java.io.BufferedReader;

public class closeRFile implements IStatement {
    IExpression expr;

    public closeRFile(IExpression expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "close file "  + expr.toString() +"\n" ;
    }

    @Override
    public ProgrState execute(ProgrState state) throws Exception {
        IDictionary<String, IValue> symbolTable = state.getSymTable();
        FileTable<IValue, BufferedReader> fileTable = (FileTable<IValue, BufferedReader>) state.getFileTable();

        IValue value = expr.eval((SymbolTable<String, IValue>) symbolTable, state.getHeap());
        if (!(value instanceof StringValue stringValue)) {
            throw new ExceptionStatement("Expression does not evaluate to StringType");
        }
        String fileName = stringValue.getValue();
        if (!fileTable.isDefined(value)) {
            throw new ExceptionStatement("File '" + fileName + "' is not opened");
        }
        BufferedReader br = fileTable.lookUp(value);
        fileTable.delete(value);
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        IType typeExpr=expr.typecheck(typeEnv);
        if(!typeExpr.equals(new StringType())){
            throw new ExceptionStatement("closeRFile: Expression does not evaluate to StringType");
        }
        return typeEnv;
    }

}
