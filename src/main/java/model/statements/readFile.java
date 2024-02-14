package model.statements;

import model.exceptions.ExceptionStatement;
import model.expresion.IExpression;
import model.types.IType;
import model.types.IntType;
import model.types.RefType;
import model.utils.IDictionary;
import model.utils.ProgrState;
import model.utils.SymbolTable;
import model.value.IValue;
import model.value.IntValue;
import model.value.StringValue;

import java.io.BufferedReader;

public class readFile implements IStatement {
    IExpression expr;
    StringValue var_name;

    @Override
    public String toString() {
        return "read file:" + expr.toString() + " , "+ var_name.toString()+"\n" ;
    }

    public readFile(IExpression expr, StringValue varName) {
        this.expr = expr;
        this.var_name = varName;
    }

    @Override
    public ProgrState execute(ProgrState state) throws Exception {
        IDictionary<String, IValue> symbolTable = state.getSymTable();
        IDictionary<IValue, BufferedReader> fileTable = state.getFileTable();

        if (!symbolTable.isDefined(var_name.getValue())) {
            throw new ExceptionStatement("Variable Name is not defined in symbol table");
        }
        IValue v = symbolTable.lookUp(var_name.getValue());
        if (!(v instanceof IntValue)) {
            throw new ExceptionStatement("Variable is not IntType");
        }

        IValue value = expr.eval((SymbolTable<String, IValue>) symbolTable, state.getHeap());
        if (!(value instanceof StringValue)) {
            throw new ExceptionStatement("Expression is not StringType");
        }
        if (!fileTable.isDefined(value)) {
            throw new ExceptionStatement("File is not opened");
        }

        BufferedReader br = fileTable.lookUp(value) ;
        String line = br.readLine();
        IValue newInt;
        if (line == null) {
            newInt = new IntValue(0);
        } else {
            newInt = new IntValue(Integer.parseInt(line));
        }
        if (symbolTable.isDefined(var_name.getValue())) {
            symbolTable.update(var_name.getValue(), newInt);
        } else {
            symbolTable.put(var_name.getValue(), newInt);
        }
        state.setSymTable(symbolTable);
        return null;

    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        IType typeVar=typeEnv.lookUp(var_name.getValue());
        IType typeExpr= expr.typecheck(typeEnv);
        if (typeVar.equals(new IntType())){
            return typeEnv;
        }
        else
            throw new ExceptionStatement("Read File typecheck fail");
    }

}
