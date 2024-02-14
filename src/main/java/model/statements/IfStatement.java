package model.statements;

import model.exceptions.ExceptionStatement;
import model.expresion.IExpression;
import model.types.BoolType;
import model.types.IType;
import model.utils.IDictionary;
import model.utils.IStack;
import model.utils.ProgrState;
import model.utils.SymbolTable;
import model.value.BoolValue;
import model.value.IValue;

import java.util.Map;

public class IfStatement implements IStatement {
    IExpression expr;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement(IExpression expression, IStatement t, IStatement e) {
        expr = expression;
        thenStatement = t;
        elseStatement = e;
    }

    @Override
    public String toString() {
        return "IF(" + expr.toString() + ")"+"\n"+" THEN " + thenStatement.toString() + " ELSE" + elseStatement.toString() ;
    }

    @Override
    public ProgrState execute(ProgrState state) throws Exception {
        IStack<IStatement> stack = state.getExeStack();
        SymbolTable<String, IValue> symb = (SymbolTable<String, IValue>) state.getSymTable();
        IValue conditionValue = expr.eval(symb, state.getHeap());
        if (conditionValue instanceof BoolValue boolValue) {
            boolean conditionResult = boolValue.getVal();

            if (conditionResult) {
                stack.push(thenStatement);
            } else {
                if (elseStatement != null) {
                    stack.push(elseStatement);
                }
            }
        } else {
            throw new ExceptionStatement("Condition expression is not of type Bool");
        }

        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        IType typeExpr=expr.typecheck(typeEnv);
        if(typeExpr.equals(new BoolType())){
            thenStatement.typecheck(clone(typeEnv));
            elseStatement.typecheck(clone(typeEnv));
            return typeEnv;
        }
        else throw new ExceptionStatement("The condition of IF has not the type bool");

    }
    private IDictionary<String, IType> clone(IDictionary<String, IType> original) {
        IDictionary<String, IType> cloned = new SymbolTable<>();
        for (Map.Entry<String, IType> entry : original.entrySet()) {
            cloned.put(entry.getKey(), entry.getValue());
        }
        return cloned;
    }
}


