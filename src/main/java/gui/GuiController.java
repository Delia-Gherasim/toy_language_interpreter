package gui;

import controller.Controller;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.statements.IStatement;
import model.statements.PrintStatement;
import model.utils.*;
import model.value.IValue;
import repository.IRepository;
import repository.Repository;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;

public class GuiController {
    List<ProgrState> programStateList;
    ProgrState selected;
    Integer itemNr;
    Stage newStage = new Stage();
    Controller controller;
    IStatement statement;
    IRepository repo;
    private TableView<Map.Entry<String, IValue>> symbolTable;
    private ListView<String> exeStack;
    ListView<String> idProgramStates;

    GuiController(Integer itemNr, MainStage mainStage){
        this.itemNr=itemNr;
        statement=mainStage.getExamplesList()[itemNr];
        IStack<IStatement> stk1 = new ExecuteStack<>() {};
        IDictionary<String, IValue> symbolTable1 = new SymbolTable<>();
        IDictionary<IValue, BufferedReader> fileTable1=new FileTable<>();
        IList<IValue> out1 = new Output<IValue>();
        IHeap<Integer, IValue> heap= new Heap<>();
        ISemaphore semaphore=new SemaphoreTable();
        ProgrState PrgState1 = new ProgrState(stk1, symbolTable1, out1, statement, fileTable1, heap, semaphore);
        selected=PrgState1;
        itemNr++;
        String logfile="log"+itemNr+".txt";
        repo= new Repository(logfile);
        repo.add(PrgState1);
        controller = new Controller(repo, true);
    }
    @FXML
    void openNewWindow(String example) {
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("Execution Result");
        CheckBox cb=new CheckBox("See all steps");
        Button startExecution=new Button("Start execution");
        startExecution.setOnAction(event -> {
            if (cb.isSelected()) {
                allSteps();
            } else {
                finalStep();
            }
        });

        VBox vbox = new VBox(new javafx.scene.control.Label("Executing: " + example),cb, startExecution );
        vbox.alignmentProperty();
        Scene newScene = new Scene(vbox);
        newStage.setScene(newScene);
        newStage.show();
    }

    void finalStep(){
        programStateList=controller.getRepo().getPrgList();
        try {
            //statement.typecheck(new typeEnviorment<String, IType>());
            controller.allStep();
            Label outputLabel=new Label("Output: ");
            VBox vbox = new VBox(outputLabel, getOutput(programStateList.getFirst()));
            Scene newScene = new Scene(vbox);
            newStage.setScene(newScene);
            newStage.show();

        } catch (Exception e) {
            Label exceptionLabel=new Label(e.getMessage());
            Scene newScene = new Scene(exceptionLabel);
            newStage.setScene(newScene);
            newStage.setTitle("Error");
            newStage.show();
        }
    }
    GridPane grid1;
    void allSteps() {
        programStateList = controller.getRepo().getPrgList();
        Button buttonNext = new Button("Next step");
        HBox hbox = new HBox(5);
        hbox.setPadding(new Insets(10));
        hbox.setAlignment(Pos.BASELINE_RIGHT);
        Button buttonProgr=new Button("See Program State");
        programStateList = controller.removeCompletedPrg(repo.getPrgList());
        repo.setPrgList(programStateList);
        hbox.getChildren().addAll(buttonNext);

        buttonNext.setOnAction(event -> {
            Label nrProgramStates = new Label("Number of program states: ");
            idProgramStates=getProgramStates();
            buttonProgr.setOnAction(actionEvent ->
            {
                String selectedExample = idProgramStates.getSelectionModel().getSelectedItem();

                if (selectedExample != null) {
                        if (!programStateList.isEmpty()) {
                            for (ProgrState progrState : programStateList) {
                                if(progrState.getId()==Integer.valueOf(selectedExample)) {
                                    selected = progrState;
                                    grid1 = updateProgramState(selected);
                                }
                            }
                        }
                    hbox.getChildren().remove(3);
                    hbox.getChildren().add(grid1);
                }
            });
            VBox vbox = new VBox(nrProgramStates, idProgramStates);
            try {
                if (!programStateList.isEmpty()) {
                    grid1= oneStepInGui(selected);
                    hbox.getChildren().clear();
                    hbox.getChildren().addAll(vbox, buttonNext, buttonProgr, grid1);
                    controller.unsafeGarbageCollector(programStateList);
                }
                programStateList = controller.removeCompletedPrg(repo.getPrgList());
                repo.setPrgList(programStateList);

            } catch (Exception e) {
                Label exceptionLabel = new Label(e.getMessage());
                Scene newScene = new Scene(exceptionLabel);
                newStage.setScene(newScene);
                newStage.setTitle("Exception has been caught");
                newStage.show();
            }
        });

        hbox.setBackground(Background.fill(Color.PINK));
        Scene newScene = new Scene(hbox, 600, 600);
        newStage.getMaxHeight();
        newStage.getMaxWidth();
        newStage.setScene(newScene);
        newStage.show();
    }

    private GridPane oneStepInGui(ProgrState prg) throws Exception {
        controller.oneStepForAllPrg(programStateList);
        programStateList = repo.getPrgList();

        return updateProgramState(prg);
    }
    private GridPane updateProgramState(ProgrState prg) {
        GridPane grid = new GridPane();
        grid.alignmentProperty();
        symbolTable = getSymbolTable(prg);
        exeStack=getExecutionStack(prg);

        grid.add(symbolTable, 0, 0);
        grid.add(exeStack, 1, 0);
        grid.add(getHeapTable(prg), 0, 1);
        grid.add(getFileTable(prg), 1, 1);
        grid.add(getOutput(prg), 0, 2);
        grid.add(getSemaphoreTable(prg), 1, 2);

        return grid;
    }

    private ListView getOutput(ProgrState prg){
        ObservableList<String> items = null;
        IList<IValue> listoutput = prg.getOut();
        if (!listoutput.isEmpty()) {
            items = FXCollections.observableArrayList(listoutput.toString());
        }
        ListView<String> list=new ListView<>();
        list.setItems(items);
        return list;
    }

    private ListView getFileTable(ProgrState prg) {
        ObservableList<String> items=FXCollections.observableArrayList();
        IDictionary<IValue, BufferedReader> fileTable = prg.getFileTable();
        for (Map.Entry<IValue, BufferedReader> entry : fileTable.entrySet()) {
               items.add(entry.getKey().toString()+" "+entry.getValue().toString());
        }
        ListView<String> list = new ListView<>();
        list.setItems(items);
        return list;
    }
    private TableView<Map.Entry<Integer, IValue>> getHeapTable(ProgrState prg) {
        IHeap<Integer, IValue> heap = prg.getHeap();
        ObservableList<Map.Entry<Integer, IValue>> items = FXCollections.observableArrayList();
        if (heap != null && !heap.isEmpty()) {
            items.addAll(heap.entrySet());
        }
        TableView<Map.Entry<Integer, IValue>> tableView = new TableView<>();
        tableView.setEditable(true);

        TableColumn<Map.Entry<Integer, IValue>, Integer> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getKey()));
        TableColumn<Map.Entry<Integer, IValue>, IValue> valueColumn = new TableColumn<>("Value");
        valueColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getValue()));

        tableView.getColumns().addAll(addressColumn, valueColumn);
        tableView.setItems(items);
        return tableView;
    }
    private TableView<Map.Entry<String, IValue>> getSymbolTable(ProgrState prg) {
        IDictionary<String, IValue> smbltable = prg.getSymTable();
        ObservableList<Map.Entry<String, IValue>> items = FXCollections.observableArrayList();

        if (smbltable != null && !smbltable.isEmpty()) {
            for (Map.Entry<String, IValue> entry : smbltable.entrySet()) {
                items.add(entry);
            }
        }

        TableView<Map.Entry<String, IValue>> tableView = new TableView<>();
        tableView.setEditable(true);

        TableColumn<Map.Entry<String, IValue>, String> nameColumn = new TableColumn<>("Variable");
        nameColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getKey()));

        TableColumn<Map.Entry<String, IValue>, IValue> valueColumn = new TableColumn<>("Value");
        valueColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getValue()));

        tableView.getColumns().addAll(nameColumn, valueColumn);
        tableView.setItems(items);

        return tableView;
    }

    private ListView getExecutionStack(ProgrState prg){
        ListView<String> list=new ListView<>();
        IStack<IStatement> stack = prg.getExeStack();
        IStack<IStatement> tempStack = new ExecuteStack<IStatement>() {};
        while (!stack.isEmpty()) {
            tempStack.push(stack.pop());
        }
        ObservableList<String> items = FXCollections.observableArrayList();
        while (!tempStack.isEmpty()) {
            IStatement statement = tempStack.pop();
            items.add(statement.toString());
            stack.push(statement);
        }
        list.setItems(items);
        return list;
    }

    ObservableList<String> items = FXCollections.observableArrayList();
    private ListView<String> getProgramStates(){
        idProgramStates= new ListView<>();
        //System.out.println(controller.getRepo().getPrgList());
        items.clear();
        for (ProgrState progrState : programStateList) {
            if (!items.contains(String.valueOf(progrState.getId()) )&& !progrState.getExeStack().isEmpty()){
                items.add(String.valueOf(progrState.getId()));
            }
        }
        idProgramStates.setItems(items);
        return idProgramStates;
    }

    private TableView<Map.Entry<Integer, Pair<Integer, List<Integer>>>> getSemaphoreTable(ProgrState prg) {
        ISemaphore semaphore = prg.getSemaphore();
        ObservableList<Map.Entry<Integer, Pair<Integer, List<Integer>>>> items = FXCollections.observableArrayList();
        if (semaphore != null && !semaphore.isEmpty()) {
            items.addAll(semaphore.entrySet());
        }
        TableView<Map.Entry<Integer, Pair<Integer, List<Integer>>>> tableView = new TableView<>();
        tableView.setEditable(true);

        TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, Integer> indexColumn = new TableColumn<>("Index");
        indexColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getKey()));

        TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, Integer> valueColumn = new TableColumn<>("Value");
        valueColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getValue().getKey()));

        TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, List<Integer>> listColumn = new TableColumn<>("List");
        listColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getValue().getValue()));

        tableView.getColumns().addAll(indexColumn, valueColumn, listColumn);
        tableView.setItems(items);
        return tableView;
    }

}
