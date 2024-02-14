package view;

import controller.Controller;
import model.statements.IStatement;
import model.types.IType;
import model.utils.*;
import model.value.IValue;
import repository.IRepository;
import repository.Repository;

import java.io.BufferedReader;

public class RunExample extends Command {
    private Controller ctr;
    IStatement statement;
    public RunExample(String id, String descr, Controller ctr) {
        super(id, descr);
        this.ctr=ctr;
    }
    public RunExample(String id, String descr, Controller ctr, IStatement stm) {
        super(id, descr);
        this.ctr=ctr;
        this.statement=stm;
    }

    @Override
    public void execute() throws Exception {
        try{
            statement.typecheck(new typeEnviorment<String, IType>());
            ctr.allStep();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
