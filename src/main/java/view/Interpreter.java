package view;

import controller.Controller;
import model.expresion.*;
import model.statements.*;
import model.types.*;
import model.utils.*;
import model.value.*;
import repository.IRepository;
import repository.Repository;

import java.io.BufferedReader;

public class Interpreter {
    static boolean flag=true;
    public static void main(String[] args) throws Exception {
        TextMenu menu = new TextMenu();
        ExitCommand exitCommand=new ExitCommand("0", "Exit");
        menu.addCommand(exitCommand);
        IStatement ex1 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        try{
            ex1.typecheck(new typeEnviorment<String, IType>());
            IStack<IStatement> stk1 = new ExecuteStack<IStatement>() {};
            IDictionary<String, IValue> symbolTable1 = new SymbolTable<>();
            IDictionary<IValue, BufferedReader> fileTable1=new FileTable<>();
            IList<IValue> out1 = new Output<IValue>();
            IHeap<Integer, IValue> heap1 = new Heap<>();
            ProgrState PrgState1 = new ProgrState(stk1, symbolTable1, out1, ex1, fileTable1, heap1);
            IRepository repo1 = new Repository("log1.txt");
            repo1.add(PrgState1);
            Controller controller1 = new Controller(repo1, flag);
            menu.addCommand(new RunExample("1",ex1.toString(),controller1, ex1));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        IStatement ex2 = new CompoundStatement(
                new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ArithmeticExpression(
                                        new ValueExpression(new IntValue(2)),
                                        ArithmOp.ADD,
                                        new ArithmeticExpression(
                                                new ValueExpression(new IntValue(3)),
                                                ArithmOp.MULTIPLY,
                                                new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(
                                        new AssignmentStatement("b", new ArithmeticExpression(
                                                new VariableExpression("a"),
                                                ArithmOp.ADD,
                                                new ValueExpression(new IntValue(1)))),
                                        new PrintStatement(new VariableExpression("b"))))));
        try{
            ex2.typecheck(new typeEnviorment<String, IType>());
            IStack<IStatement> stk1 = new ExecuteStack<IStatement>() {};
            IDictionary<String, IValue> symbolTable1 = new SymbolTable<>();
            IDictionary<IValue, BufferedReader> fileTable1=new FileTable<>();
            IList<IValue> out1 = new Output<IValue>();
            IHeap<Integer, IValue> heap1 = new Heap<>();
            ProgrState PrgState1 = new ProgrState(stk1, symbolTable1, out1, ex2, fileTable1, heap1);
            IRepository repo1 = new Repository("log2.txt");
            repo1.add(PrgState1);
            Controller controller1 = new Controller(repo1, flag);
            menu.addCommand(new RunExample("2",ex2.toString(),controller1, ex2));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        IStatement ex3=new CompoundStatement(
                new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(
                                        new IfStatement(
                                                new VariableExpression("a"),
                                                new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                                new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));
        try{
            ex3.typecheck(new typeEnviorment<String, IType>());
            IStack<IStatement> stk1 = new ExecuteStack<IStatement>() {};
            IDictionary<String, IValue> symbolTable1 = new SymbolTable<>();
            IDictionary<IValue, BufferedReader> fileTable1=new FileTable<>();
            IList<IValue> out1 = new Output<IValue>();
            IHeap<Integer, IValue> heap1 = new Heap<>();
            ProgrState PrgState1 = new ProgrState(stk1, symbolTable1, out1, ex3, fileTable1, heap1);
            IRepository repo1 = new Repository("log3.txt");
            repo1.add(PrgState1);
            Controller controller1 = new Controller(repo1, flag);
            menu.addCommand(new RunExample("3",ex3.toString(),controller1, ex3));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        IStatement ex4 =
                new CompoundStatement(
                        new VariableDeclarationStatement("varf", new StringType()),
                        new CompoundStatement(
                                new AssignmentStatement("varf", new ValueExpression(new StringValue("C:\\Facultation\\sem3\\map\\lab\\a3\\test.in"))),
                                new CompoundStatement(
                                        new openRFile(
                                                new VariableExpression("varf")),
                                        new CompoundStatement(
                                                new VariableDeclarationStatement("varc", new IntType()),
                                                new CompoundStatement(
                                                        new readFile(
                                                                new VariableExpression("varf"),
                                                                new StringValue("varc")),
                                                        new CompoundStatement(
                                                                new PrintStatement(new VariableExpression("varc")),
                                                                new CompoundStatement(
                                                                        new readFile(
                                                                                new VariableExpression("varf"),
                                                                                new StringValue("varc")),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new VariableExpression("varc")),
                                                                                new closeRFile(new VariableExpression("varf"))))))))));
        try{
            ex4.typecheck(new typeEnviorment<String, IType>());
            IStack<IStatement> stk1 = new ExecuteStack<IStatement>() {};
            IDictionary<String, IValue> symbolTable1 = new SymbolTable<>();
            IDictionary<IValue, BufferedReader> fileTable1=new FileTable<>();
            IList<IValue> out1 = new Output<IValue>();
            IHeap<Integer, IValue> heap1 = new Heap<>();
            ProgrState PrgState1 = new ProgrState(stk1, symbolTable1, out1, ex4, fileTable1, heap1);
            IRepository repo1 = new Repository("log4.txt");
            repo1.add(PrgState1);
            Controller controller1 = new Controller(repo1, flag);
            menu.addCommand(new RunExample("4",ex4.toString(),controller1, ex4));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        IStatement ex5=new CompoundStatement(
                new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                            new CompoundStatement(
                            new WhileStatement(
                                    new RelationalExpression(new VariableExpression("v"), RelationEnum.GREATER_THAN, new ValueExpression(new IntValue(0))),
                                    new CompoundStatement(
                                            new PrintStatement(new VariableExpression("v")),
                                            new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), ArithmOp.MINUS, new ValueExpression(new IntValue(1)))))),
                                    new PrintStatement(new VariableExpression("v")))));
        try{
            ex5.typecheck(new typeEnviorment<String, IType>());
            IStack<IStatement> stk1 = new ExecuteStack<IStatement>() {};
            IDictionary<String, IValue> symbolTable1 = new SymbolTable<>();
            IDictionary<IValue, BufferedReader> fileTable1=new FileTable<>();
            IList<IValue> out1 = new Output<IValue>();
            IHeap<Integer, IValue> heap1 = new Heap<>();
            ProgrState PrgState1 = new ProgrState(stk1, symbolTable1, out1, ex5, fileTable1, heap1);
            IRepository repo1 = new Repository("log5.txt");
            repo1.add(PrgState1);
            Controller controller1 = new Controller(repo1, flag);
            menu.addCommand(new RunExample("5",ex5.toString(),controller1, ex5));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        IStatement ex6=new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("a", new RefType(new IntType())),
                        new CompoundStatement(
                                new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(
                                        new New("a", new ValueExpression(new RefValue(777, new IntType()))),
                                        new CompoundStatement(
                                                new ForkStatement(new CompoundStatement(
                                                        new WriteHeap("a", new ValueExpression(new RefValue(30, new RefType(new IntType())))),
                                                        new CompoundStatement(
                                                                new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                                new CompoundStatement(
                                                                        new PrintStatement(new VariableExpression("a")),
                                                                        new PrintStatement(new ReadHeap(new VariableExpression("a"))))))),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new ReadHeap(new VariableExpression("a")))))))));
        try{
            ex6.typecheck(new typeEnviorment<String, IType>());
            IStack<IStatement> stk1 = new ExecuteStack<IStatement>() {};
            IDictionary<String, IValue> symbolTable1 = new SymbolTable<>();
            IDictionary<IValue, BufferedReader> fileTable1=new FileTable<>();
            IList<IValue> out1 = new Output<IValue>();
            IHeap<Integer, IValue> heap1 = new Heap<>();
            ProgrState PrgState1 = new ProgrState(stk1, symbolTable1, out1, ex6, fileTable1, heap1);
            IRepository repo1 = new Repository("log6.txt");
            repo1.add(PrgState1);
            Controller controller1 = new Controller(repo1, flag);
            menu.addCommand(new RunExample("6",ex6.toString(),controller1, ex6));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        IStatement ex7=new CompoundStatement(
                new VariableDeclarationStatement("counter", new IntType()),
                new CompoundStatement(
                    new VariableDeclarationStatement("a", new RefType(new IntType())),
                    new WhileStatement(
                        new RelationalExpression(new VariableExpression("counter"), RelationEnum.LESS_THAN, new ValueExpression(new IntValue(10))),
                        new CompoundStatement(
                                new ForkStatement(new ForkStatement(
                                        new CompoundStatement(
                                                new New("a", new VariableExpression("counter")),
                                                new PrintStatement(new ReadHeap(new VariableExpression("a")))))),
                                           // new VariableDeclarationStatement("a", new IntType()),
                                              //  new CompoundStatement(
                                                      //  new AssignmentStatement("a", new VariableExpression("counter")),
                                                     //   new PrintStatement(new VariableExpression("a")))))),
                                new AssignmentStatement("counter", new ArithmeticExpression(new VariableExpression("counter"), ArithmOp.ADD, new ValueExpression(new IntValue(1)))
                                )))));
        try{
            ex7.typecheck(new typeEnviorment<String, IType>());
            IStack<IStatement> stk1 = new ExecuteStack<IStatement>() {};
            IDictionary<String, IValue> symbolTable1 = new SymbolTable<>();
            IDictionary<IValue, BufferedReader> fileTable1=new FileTable<>();
            IList<IValue> out1 = new Output<IValue>();
            IHeap<Integer, IValue> heap1 = new Heap<>();
            ProgrState PrgState1 = new ProgrState(stk1, symbolTable1, out1, ex7, fileTable1, heap1);
            IRepository repo1 = new Repository("log7.txt");
            repo1.add(PrgState1);
            Controller controller1 = new Controller(repo1, flag);
            menu.addCommand(new RunExample("7",ex7.toString(),controller1, ex7));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        menu.show();
    }
}
