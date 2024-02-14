package model.statements;

import javafx.util.Pair;
import model.exceptions.ExceptionStatement;
import model.types.IType;
import model.types.IntType;
import model.utils.*;
import model.value.IValue;
import model.value.IntValue;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReleaseStatement implements IStatement{
    private final String var;
    private static final Lock lock = new ReentrantLock();
    public ReleaseStatement(String var) {
        this.var = var;
    }
    @Override
    public ProgrState execute(ProgrState state) throws Exception {
        lock.lock();
        IDictionary<String, IValue> symTable = state.getSymTable();
        ISemaphore semaphoreTable = state.getSemaphore();
        if (symTable.isDefined(var)) {
            if (symTable.lookUp(var).getType().equals(new IntType())) {
                IntValue fi = (IntValue) symTable.lookUp(var);
                int foundIndex = fi.getVal();
                if (semaphoreTable.getSemaphoreTable().containsKey(foundIndex)) {
                    Pair<Integer, List<Integer>> foundSemaphore = semaphoreTable.get(foundIndex);
                    if (foundSemaphore.getValue().contains(state.getId()))
                        foundSemaphore.getValue().remove((Integer) state.getId());
                    semaphoreTable.update(foundIndex, new Pair<>(foundSemaphore.getKey(), foundSemaphore.getValue()));
                }
                else {
                    throw new ExceptionStatement("Index not in the semaphore table");
                }
            } else {
                throw new ExceptionStatement("Index must be of int type");
            }
        } else {
            throw new ExceptionStatement("Index not in symbol table");
        }
        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnv) throws Exception{
        if (typeEnv.lookUp(var).equals(new IntType())) {
            return typeEnv;
        } else {
            throw new ExceptionStatement(var+" is not int");
        }
    }

    @Override
    public String toString() {
        return "Release(" + var +")"+"\n";
    }
}