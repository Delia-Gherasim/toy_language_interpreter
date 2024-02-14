/*package view;

import controller.Controller;
import model.expresion.*;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.StringType;
import model.utils.*;
import model.value.BoolValue;
import model.value.IValue;
import model.value.IntValue;
import model.value.StringValue;
import repository.IRepository;
import repository.Repository;

import java.io.BufferedReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class View {

    static boolean flag;
    static boolean file;
    static String path;
    private static IStatement ex1 = new CompoundStatement(
            new VariableDeclarationStatement("v", new IntType()),
            new CompoundStatement(
                    new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                    new PrintStatement(new VariableExpression("v"))
            )
    );

    private static IStatement ex2 = new CompoundStatement(
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
                                            new ValueExpression(new IntValue(5))
                                    )
                            )),
                            new CompoundStatement(
                                    new AssignmentStatement("b", new ArithmeticExpression(
                                            new VariableExpression("a"),
                                            ArithmOp.ADD,
                                            new ValueExpression(new IntValue(1))
                                    )),
                                    new PrintStatement(new VariableExpression("b"))
                            )
                    )
            )
    );

    private static IStatement ex3=new CompoundStatement(
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

    private static final IStatement ex4 =
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
                                                                    new StringValue("varc")
                                                            ),
                                                            new CompoundStatement(
                                                                    new PrintStatement(new VariableExpression("varc")),
                                                                    new CompoundStatement(
                                                                            new readFile(
                                                                                    new VariableExpression("varf"),
                                                                                    new StringValue("varc")
                                                                            ),
                                                                            new CompoundStatement(
                                                                                    new PrintStatement(new VariableExpression("varc")),
                                                                                    new closeRFile(new VariableExpression("varf"))
                                                                            ))))))));



    private static void printMenu() {
        System.out.println("0 - exit");
        System.out.println("1 " + ex1.toString());
        System.out.println("2 " + ex2.toString());
        System.out.println("3 " + ex3.toString());
        System.out.println("4 " + ex4.toString());

    }

    private static void executeEx1() {
        IStack<IStatement> stk = new ExecuteStack<IStatement>() {
        };
        IDictionary<String, IValue> symtbl = new SymbolTable<>();
        IDictionary<IValue, BufferedReader> fileTable=new FileTable<>();
        IListoutput<IValue> out = new Output<IValue>();
        ProgrState crtPrgState = new ProgrState(stk, symtbl, out, ex1, fileTable);

        if (file) {
            IRepository repo = new Repository(path);
            repo.add(crtPrgState);
            Controller controller = new Controller(repo, flag, file);
            try {
                controller.allStepFile();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            IRepository repo = new Repository();
            repo.add(crtPrgState);
            Controller controller = new Controller(repo, flag);
            try {
                controller.allStep();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    private static void executeEx2() {
        IStack<IStatement> stk = new ExecuteStack<IStatement>() {
        };
        IDictionary<String, IValue> symbolTable = new SymbolTable<>();
        IDictionary<IValue, BufferedReader> fileTable=new FileTable<>();
        IListoutput<IValue> out = new Output<IValue>();
        ProgrState crtPrgState = new ProgrState(stk, symbolTable, out, ex2, fileTable);
        if (file) {
            IRepository repo = new Repository(path);
            repo.add(crtPrgState);
            Controller controller = new Controller(repo, flag, file);
            try {
                controller.allStepFile();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            IRepository repo = new Repository();
            repo.add(crtPrgState);
            Controller controller = new Controller(repo, flag);
            try {
                controller.allStep();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    private static void executeEx3() {
        IStack<IStatement> stk = new ExecuteStack<IStatement>() {
        };
        IDictionary<String, IValue> symbolTable = new SymbolTable<>();
        IDictionary<IValue, BufferedReader> fileTable=new FileTable<>();
        IListoutput<IValue> out = new Output<IValue>();
        ProgrState crtPrgState = new ProgrState(stk, symbolTable, out, ex3, fileTable);
        if (file) {
            IRepository repo = new Repository(path);
            repo.add(crtPrgState);
            Controller controller = new Controller(repo, flag, file);
            try {
                controller.allStepFile();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            IRepository repo = new Repository();
            repo.add(crtPrgState);
            Controller controller = new Controller(repo, flag);
            try {
                controller.allStep();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public static void executeEx4() {
        IStack<IStatement> stk = new ExecuteStack<IStatement>() {
        };
        IDictionary<String, IValue> symbolTable = new SymbolTable<>();
        IDictionary<IValue, BufferedReader> fileTable=new FileTable<>();
        IListoutput<IValue> out = new Output<IValue>();
        ProgrState crtPrgState = new ProgrState(stk, symbolTable, out,  ex4, fileTable);
        if (file) {
            IRepository repo = new Repository(path);
            repo.add(crtPrgState);
            Controller controller = new Controller(repo, flag, file);
            try {
                controller.allStepFile();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            IRepository repo = new Repository();
            repo.add(crtPrgState);
            Controller controller = new Controller(repo, flag);
            try {
                controller.allStep();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public void runOptions() {
        printMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.println("flag?");
        try {
            flag = scanner.nextBoolean();
        } catch (InputMismatchException e) {
            System.out.println("Custom Exception caught: Please input a boolean");
            return;
        }
        System.out.println("do you want to log the values in a file? ");
        try {
            file = scanner.nextBoolean();
        } catch (InputMismatchException e) {
            System.out.println("Custom Exception caught: Please input true or false");
            return;
        }
        if (file) {
            System.out.println("What is the file path?");
            path = scanner.next();
        }
        System.out.println("what option do you choose? ");
        int option;
        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Custom Exception caught: Please input an integer");
            return;
        }
        while (option != 0) {
            if (option == 1) {
                executeEx1();
            } else if (option == 2) {
                executeEx2();
            } else if (option == 3) {
                executeEx3();
            } else if (option == 4) {
                executeEx4();
            }
            printMenu();
            scanner = new Scanner(System.in);
            System.out.println("what option do you choose? ");
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Custom Exception caught: Please input an integer");
                return;
            }
        }
    }

}
*/