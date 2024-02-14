package model.utils;

import model.exceptions.ExceptionStack;
import model.statements.IStatement;
import model.value.IValue;

import java.io.BufferedReader;

public class ProgrState {
    IStack<IStatement> exeStack;
    IDictionary<String, IValue> symTable;
    IList<IValue> out;
    IDictionary<IValue, BufferedReader> fileTable;
    IHeap<Integer, IValue> heap;
    ISemaphore semaphore;
    private int id;
    private static int globalId;
    public int getId() {
        return id;
    }
    private int nextGlobalId(){
        globalId++;
        return globalId;}


    public ProgrState(IStack<IStatement> stack, IDictionary<String, IValue> symbolTable, IList<IValue> out, IStatement prog, IDictionary<IValue, BufferedReader> fileT, IHeap<Integer, IValue> heap, ISemaphore semaphore) {
        this.exeStack = stack;
        this.symTable = symbolTable;
        this.out = out;
        this.fileTable = fileT;
        this.heap=heap;
        this.semaphore=semaphore;
        id=nextGlobalId();
        stack.push(prog);
    }
    public ProgrState(IStack<IStatement> stack, IDictionary<String, IValue> symbolTable, IList<IValue> out, IStatement prog, IDictionary<IValue, BufferedReader> fileT, IHeap<Integer, IValue> heap) {
        this.exeStack = stack;
        this.symTable = symbolTable;
        this.out = out;
        this.fileTable = fileT;
        this.heap=heap;
        id=nextGlobalId();
        stack.push(prog);
    }
    public boolean isNotCompleted(){
        if (!this.exeStack.isEmpty())
            return true;
        return false;
    }

    public ProgrState oneStep() throws Exception {
         if (exeStack.isEmpty())
            throw new ExceptionStack("programstate stack is empty");
        IStatement crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    public IStack<IStatement> getExeStack() {
        return exeStack;
    }

    public void setExeStack(IStack<IStatement> stack) {
        this.exeStack = stack;
    }

    public IDictionary<String, IValue> getSymTable() {
        return symTable;
    }

    public void setSymTable(IDictionary<String, IValue> symtable) {
        this.symTable = symtable;
    }

    public IList<IValue> getOut() {
        return out;
    }

    public void setOut(IList<IValue> out1) {
        this.out = out1;
    }

    public IDictionary<IValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(IDictionary<IValue, BufferedReader> table) {
        this.fileTable = table;
    }

    public IHeap<Integer, IValue> getHeap(){
        return heap;
    }
    public void setHeap(IHeap<Integer, IValue> newHeap){
        this.heap=newHeap;
    }
    public ISemaphore getSemaphore() {
        return semaphore;
    }
    public void setSemaphore(ISemaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public String toString() {
        return "Program State: "+ id+"\n" +
                "Execution Stack: " + exeStack + "\n" +
                "Symbol Table: " + symTable + "\n" +
                "Output: " + out+"\n"+
                "File Table: "+fileTable+"\n"+
                "Heap: "+heap+"\n";
    }

}
