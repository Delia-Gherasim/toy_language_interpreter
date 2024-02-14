package repository;

import javafx.util.Pair;
import model.statements.IStatement;
import model.utils.ProgrState;
import model.utils.*;
import model.value.IValue;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Repository implements IRepository {
    List<ProgrState> programStateList;
    String logFilePath;
    public Repository(String logFile) {
        this.programStateList = new ArrayList<>();
        this.logFilePath = logFile;
    }
    @Override
    public List<ProgrState> getPrgList() {
        return programStateList;
    }
    @Override
    public void setPrgList(List<ProgrState> prgList) {
        this.programStateList=prgList;
    }

    @Override
    public void add(ProgrState programState) {
        programStateList.add(programState);
    }
    @Override
    public void logProgrState(ProgrState prg) throws Exception {
        Path path = Paths.get(logFilePath);
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        String s="Program State "+ prg.getId();
        logFile.println(s);

        logFile.println("ExeStack: ");
        IStack<IStatement> stack = prg.getExeStack();
        IStack<IStatement> tempStack = new ExecuteStack<IStatement>() {};
        while (!stack.isEmpty()) {
            tempStack.push(stack.pop());
        }
        while (!tempStack.isEmpty()) {
            IStatement statement = tempStack.pop();
            logFile.println(statement.toString());
            stack.push(statement);
        }

        logFile.println("SymTable: ");
        IDictionary<String, IValue> simbTable = prg.getSymTable();
        if (!simbTable.isEmpty()) {
            for (Map.Entry<String, IValue> entry : simbTable.entrySet()) {
                logFile.println(entry.getKey() + ": " + entry.getValue());
            }
        }

        logFile.println("FileTable: ");
        IDictionary<IValue, BufferedReader> fileTable = prg.getFileTable();
        if (!fileTable.isEmpty()) {
            for (Map.Entry<IValue, BufferedReader> entry : fileTable.entrySet()) {
                logFile.println(entry.getKey() + ": " + entry.getValue());
            }
        }

        IList<IValue> list = prg.getOut();
        if (!list.isEmpty()) {
            logFile.println(list);
        }

        logFile.println("Heap: ");
        IHeap<Integer, IValue> heap= prg.getHeap();
        if (heap!=null){
            if (!heap.isEmpty()){
                for (Map.Entry<Integer, IValue> entry : heap.entrySet()) {
                    logFile.println(entry.getKey() + ": " + entry.getValue());
                }
            }}

        logFile.println("Semaphore: ");
        ISemaphore semaphore= prg.getSemaphore();;
        if(semaphore!=null){
            if(!semaphore.isEmpty()){
                for(Map.Entry<Integer, Pair<Integer, List<Integer>>> entry: semaphore.entrySet()){
                    logFile.println(entry.getKey()+" : "+entry.getValue().toString());
                }
            }
        }

        logFile.println();
        logFile.flush();
        logFile.close();
    }

    @Override
    public String toString() {
        return "programStateList=" + programStateList;
    }

}

