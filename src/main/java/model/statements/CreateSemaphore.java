package model.statements;
import javafx.util.Pair;
import model.exceptions.ExceptionStatement;
import model.expresion.IExpression;
import model.types.IType;
import model.types.IntType;
import model.utils.*;
import model.value.IValue;
import model.value.IntValue;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CreateSemaphore implements IStatement {
    private final String var;
    private final IExpression expression;
    private static final Lock lock = new ReentrantLock();

    public CreateSemaphore(String var, IExpression expr) {
        this.var = var;
        this.expression = expr;
    }

    @Override
    public ProgrState execute(ProgrState state) throws Exception {
        lock.lock();
        IDictionary<String, IValue> symTable = state.getSymTable();
        IHeap heap = state.getHeap();
        ISemaphore semaphoreTable = state.getSemaphore();
        IntValue nr = (IntValue) (expression.eval((SymbolTable<String, IValue>) symTable, heap));
        int number = nr.getVal();
        int freeAddress = semaphoreTable.getFreeAddress();
        semaphoreTable.put(freeAddress, new Pair<>(number, new ArrayList<>()));
        if (symTable.isDefined(var) && symTable.lookUp(var).getType().equals(new IntType()))
            symTable.update(var, new IntValue(freeAddress));
        else
            throw new ExceptionStatement("Error for variable: " + var + " not defined/does not have int type!");
        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws Exception {
        if (typeEnv.lookUp(var).equals(new IntType())) {
            if (expression.typecheck(typeEnv).equals(new IntType()))
                return typeEnv;
            else
                throw new ExceptionStatement("Expression is not of int type!");
        } else {
            throw new ExceptionStatement(var + " is not of type int!");
        }
    }

    @Override
    public String toString() {
        return "CreateSemaphore( " +" var= " + var + " expression=" + expression +')' +"\n";
    }
}