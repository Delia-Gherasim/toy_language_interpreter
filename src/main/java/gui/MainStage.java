package gui;

import com.example.a7.HelloApplication;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.expresion.*;
import model.statements.*;
import model.types.*;
import model.utils.typeEnviorment;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.RefValue;
import model.value.StringValue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MainStage extends Application {
    private ListView<String> list;
    private Button runButton;
    GuiController guiController;

    @Override
    public void start(Stage stage) throws IOException {
        list=new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList();
        for (IStatement stmt:getExamplesList()) {
            items.add(stmt.toString());
        }
        list.setItems(items);
        Label lbl=new Label("Examples List");
        runButton = new Button("Run Selected Item");
        runButton.setOnAction(event -> {
            String selectedExample = list.getSelectionModel().getSelectedItem();
            Integer itemNr=list.getSelectionModel().getSelectedIndex();
            if (selectedExample != null) {
                guiController=new GuiController(itemNr, this);
                guiController.openNewWindow(selectedExample);
            } else {
                System.out.println("No example selected.");
            }
        });
        VBox vbox = new VBox(lbl, list, runButton);
        vbox.setBackground(Background.fill(Color.PINK));
        Scene scene = new Scene(vbox, 600, 600, Color.PINK);
        stage.setTitle("Welcome to my toy language interpreter!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public IStatement[] getExamplesList() {
        IStatement[] statements=new IStatement[]{};
        IStatement ex1 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        try{
            ex1.typecheck(new typeEnviorment<String, IType>());
            IStatement[] newStatements = Arrays.copyOf(statements, statements.length + 1);
            newStatements[newStatements.length - 1] = ex1;
            statements = newStatements;
        }
        catch (Exception e){
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
            IStatement[] newStatements = Arrays.copyOf(statements, statements.length + 1);
            newStatements[newStatements.length - 1] = ex2;
            statements = newStatements;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        IStatement ex3 = new CompoundStatement(
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
            IStatement[] newStatements = Arrays.copyOf(statements, statements.length + 1);
            newStatements[newStatements.length - 1] = ex3;
            statements = newStatements;}
        catch (Exception e){
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
            IStatement[] newStatements = Arrays.copyOf(statements, statements.length + 1);
            newStatements[newStatements.length - 1] = ex4;
            statements = newStatements;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        IStatement ex5 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
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
            IStatement[] newStatements = Arrays.copyOf(statements, statements.length + 1);
            newStatements[newStatements.length - 1] = ex5;
            statements = newStatements;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        IStatement ex6 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("a", new RefType(new IntType())),
                        new CompoundStatement(
                                new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(
                                        new New("a", new ValueExpression(new RefValue(22, new IntType()))),
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
            IStatement[] newStatements = Arrays.copyOf(statements, statements.length + 1);
            newStatements[newStatements.length - 1] = ex6;
            statements = newStatements;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        IStatement ex7 = new CompoundStatement(
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
                                        new AssignmentStatement("counter", new ArithmeticExpression(new VariableExpression("counter"), ArithmOp.ADD, new ValueExpression(new IntValue(1))))))));
        try{
            ex7.typecheck(new typeEnviorment<String, IType>());
            IStatement[] newStatements = Arrays.copyOf(statements, statements.length + 1);
            newStatements[newStatements.length - 1] = ex7;
            statements = newStatements;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        //switch
        IStatement ex8=new CompoundStatement(
                new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(
                                new VariableDeclarationStatement("c", new IntType()),
                                new CompoundStatement(
                                        new AssignmentStatement("a", new ValueExpression(new IntValue(1))),
                                        new CompoundStatement(
                                                new AssignmentStatement("b", new ValueExpression(new IntValue(2))),
                                                new CompoundStatement(
                                                        new AssignmentStatement("c", new ValueExpression(new IntValue(5))),
                                                        new CompoundStatement(
                                                                new SwitchStatement(
                                                                        new ArithmeticExpression(new VariableExpression("a"), ArithmOp.MULTIPLY, new ValueExpression(new IntValue(10))),
                                                                        new ArithmeticExpression(new VariableExpression("b"), ArithmOp.MULTIPLY, new VariableExpression("c")),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new VariableExpression("a")),
                                                                                new PrintStatement(new VariableExpression("b"))
                                                                        ),
                                                                        new ValueExpression(new IntValue(10)),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new ValueExpression(new IntValue(100))),
                                                                                new PrintStatement(new ValueExpression(new IntValue(200)))

                                                                        ),
                                                                        new PrintStatement(new ValueExpression(new IntValue(300)))
                                                                ),
                                                                new PrintStatement(new ValueExpression(new IntValue(300)))
                                                        )
                                                )
                                        )
                                ))

                )
        );
        try{
            ex8.typecheck(new typeEnviorment<String, IType>());
            IStatement[] newStatements = Arrays.copyOf(statements, statements.length + 1);
            newStatements[newStatements.length - 1] = ex8;
            statements = newStatements;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        //CountSemaphore
        IStatement ex9=new CompoundStatement(
                new VariableDeclarationStatement("v1", new RefType(new IntType())),
                new CompoundStatement(
                        new VariableDeclarationStatement("cnt", new IntType()),
                        new CompoundStatement(
                                new New("v1", new ValueExpression(new IntValue(1))),
                                new CompoundStatement(
                                        new CreateSemaphore("cnt", new ReadHeap(new VariableExpression("v1"))),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(
                                                                new AcquireStatement("cnt"),
                                                                new CompoundStatement(new WriteHeap("v1", new ArithmeticExpression(new ReadHeap(new VariableExpression("v1")), ArithmOp.MULTIPLY, new ValueExpression(new IntValue(10)))),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new ReadHeap(new VariableExpression("v1"))),
                                                                                new ReleaseStatement("cnt"))))),
                                                new CompoundStatement(
                                                        new ForkStatement(
                                                                new CompoundStatement(
                                                                        new AcquireStatement("cnt"),
                                                                       new CompoundStatement(new WriteHeap("v1", new ArithmeticExpression(new ReadHeap(new VariableExpression("v1")), ArithmOp.MULTIPLY, new ValueExpression(new IntValue(10)))),
                                                                                new CompoundStatement(
                                                                                        new WriteHeap("v1", new ArithmeticExpression(new ReadHeap(new VariableExpression("v1")), ArithmOp.MULTIPLY, new ValueExpression(new IntValue(2)))),
                                                                                        new CompoundStatement(
                                                                                                new PrintStatement(new ReadHeap(new VariableExpression("v1"))),
                                                                                                new ReleaseStatement("cnt")))))),
                                                        new CompoundStatement(
                                                                new AcquireStatement("cnt"),
                                                                new CompoundStatement(
                                                                        new PrintStatement(new ArithmeticExpression(new ReadHeap(new VariableExpression("v1")), ArithmOp.MINUS, new ValueExpression(new IntValue(1)))),
                                                                        new ReleaseStatement("cnt")))))))));

        try{
            ex9.typecheck(new typeEnviorment<String, IType>());
            IStatement[] newStatements = Arrays.copyOf(statements, statements.length + 1);
            newStatements[newStatements.length - 1] = ex9;
            statements = newStatements;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return statements;
    }
}
