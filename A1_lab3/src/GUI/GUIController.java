package GUI;

import Controller.Ctrl;
import Exceptions.RepoException;
import Model.DataStructures.Classes.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import Model.PrgState;
import Model.Statements.IStmt;
import Repository.IRepo;
import Repository.Repo;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class GUIController{
    @FXML
    private TextField numPrgStates;
    @FXML
    private TableView<Pair<Integer, String>> heapTableView;
    @FXML
    private ListView<String> outListView;
    @FXML
    private ListView<String> fileTableListView;
    @FXML
    private ListView<Integer> prgStateListView;
    @FXML
    private TableView<Pair<String, String>> symTableView;
    @FXML
    private ListView<String> exeStackListView;
    @FXML
    private TableColumn<Pair<String, String>, String> symVarNameCol;
    @FXML
    private TableColumn<Pair<String, String>, String> symValueCol;
    @FXML
    private TableColumn<Pair<Integer, String>, String> heapAddressCol;
    @FXML
    private TableColumn<Pair<Integer, String>, String> heapValueCol;

    @FXML
    private Button runButton;

    private Ctrl controller;


    public void initializeExecution(IStmt program) throws RepoException {
        PrgState initialPrgState = new PrgState(
                new MyStack<>(), new MyDictionary<>(), new MyList<>(), program, new MyDictionary<>(), new MyHeap(), new MyLockTable()
        );



        IRepo repository = new Repo("logFilePath");
        repository.addPrgState(initialPrgState);
        controller = new Ctrl(repository);

        prgStateListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                PrgState selectedPrgState = getCurrentPrgState();
                if (selectedPrgState != null) {
                    updateSymTable(selectedPrgState);
                    updateExeStack(selectedPrgState);
                }
            }
        });

        updateAllViews();
    }

//    @FXML
//    private void handleRunOneStep() {
//        try {
//            controller.executor = Executors.newFixedThreadPool(2);
//
//            // Remove the completed programs
//            List<PrgState> prgList = controller.removeCompletedPrg(controller.getRepo().getPrgList());
//
//            // Ensure at least one program state exists
//            if (prgList.isEmpty()) {
//                showError("No more steps to execute.");
//                controller.executor.shutdownNow();
//                return;
//            }
//
//            // Garbage collection
//            List<PrgState> finalPrgList = prgList;
//            prgList.forEach(prg -> prg.getHeap().setContent(controller.conservativeGarbageCollector(finalPrgList)));
//
//            // Execute one step for all programs
//            controller.oneStepForAllPrg(prgList);
//
//            // Remove completed programs again
//            prgList = controller.removeCompletedPrg(controller.getRepo().getPrgList());
//            controller.getRepo().setPrgList(prgList);
//
//            // Check again if all are completed
    ////            if (prgList.isEmpty()) {
    ////                showError("No more steps to execute.");
    ////                controller.executor.shutdownNow();
    ////                return;
    ////            }
//
//            // Update the UI
//            updateAllViews();
//        } catch (Exception e) {
//            showError(e.getMessage());
//        }
//    }

    @FXML
    private void handleRunOneStep() {
        try {
            controller.executor = Executors.newFixedThreadPool(2);

            // Remove the completed programs
            List<PrgState> prgList = controller.removeCompletedPrg(controller.getRepo().getPrgList());
            //pr
            if (prgList.isEmpty()) {
                showError("No more steps to execute.");
                prgStateListView.getItems().clear();
                exeStackListView.getItems().clear();
                controller.executor.shutdownNow();
                return;
            }

            //Garbage collection
            prgList.forEach(prg -> prg.getHeap().setContent(
                    controller.conservativeGarbageCollector(prgList)
            ));


            //display program list on terminal
            prgList.forEach(System.out::println);

            // Execute one step for all programs
            controller.oneStepForAllPrg(prgList);

            // Update the program list and views
            updateAllViews();
        } catch (Exception e) {
            showError("HERE: " + e.getMessage());
            showError(e.getMessage());
        }
    }


    private void updateAllViews() {
        try {
            List<PrgState> prgStates = controller.getRepo().getPrgList();

            // Update number of program states
            numPrgStates.setText(String.valueOf(prgStates.size()));

            if (prgStates.isEmpty()) {
                // Clear all views if no program states exist
                exeStackListView.getItems().clear();
                prgStateListView.getItems().clear();

//                symTableView.getItems().clear();
//                heapTableView.getItems().clear();
//                outListView.getItems().clear();
//                fileTableListView.getItems().clear();
                return; // No need to update further
            }

            // Update program state identifiers
            ObservableList<Integer> prgStateIds = FXCollections.observableArrayList(
                    prgStates.stream().map(PrgState::getId).collect(Collectors.toList())
            );
            prgStateListView.setItems(prgStateIds);

            // Preserve the current selection
            Integer currentSelection = prgStateListView.getSelectionModel().getSelectedItem();
            if (currentSelection == null && !prgStateIds.isEmpty()) {
                prgStateListView.getSelectionModel().select(0);
            }

            PrgState selectedPrgState = getCurrentPrgState();
            if (selectedPrgState != null) {
                updateHeapTable(selectedPrgState);
                updateOutputList(selectedPrgState);
                updateFileTable(selectedPrgState);
                updateSymTable(selectedPrgState);
                updateExeStack(selectedPrgState);
            }
        } catch (Exception e) {
            showError("I AM HERE: " + e.getMessage());
            showError(e.getMessage());
        }
    }


//    private void updateAllViews() {
//        try {
//            List<PrgState> prgStates = controller.getRepo().getPrgList();
//
//            // Update number of program states
//            numPrgStates.setText(String.valueOf(prgStates.size()));
//
//            // Update program state identifiers
//            ObservableList<Integer> prgStateIds = FXCollections.observableArrayList(
//                    prgStates.stream().map(PrgState::getId).collect(Collectors.toList())
//            );
//            prgStateListView.setItems(prgStateIds);
//
//            // Select the first program state by default if none is selected
//            if (prgStateListView.getSelectionModel().isEmpty() && !prgStateIds.isEmpty()) {
//                prgStateListView.getSelectionModel().select(0);
//            }
//
//            PrgState selectedPrgState = getCurrentPrgState();
//
//            if (selectedPrgState != null) {
//                updateHeapTable(selectedPrgState);
//                updateOutputList(selectedPrgState);
//                updateFileTable(selectedPrgState);
//                updateSymTable(selectedPrgState);
//                updateExeStack(selectedPrgState);
//            }
//        } catch (Exception e) {
//            showError(e.getMessage());
//        }
//    }

    private void updateHeapTable(PrgState prgState) {
        ObservableList<Pair<Integer, String>> heapTableItems = FXCollections.observableArrayList(
                prgState.getHeap().getContent().entrySet().stream()
                        .map(entry -> new Pair<>(entry.getKey(), entry.getValue().toString()))
                        .collect(Collectors.toList())
        );
        // Bind Heap Table columns
        heapAddressCol.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getKey())));
        heapValueCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValue()));
        heapTableView.setItems(heapTableItems);
    }

    private void updateSymTable(PrgState prgState) {
        ObservableList<Pair<String, String>> symTableItems = FXCollections.observableArrayList(
                prgState.getSymTable().getContent().entrySet().stream()
                        .map(entry -> new Pair<>(entry.getKey(), entry.getValue().toString()))
                        .collect(Collectors.toList())
        );
        // Bind Sym Table columns
        symVarNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKey()));
        symValueCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValue()));

        symTableView.setItems(symTableItems);
    }

    private void updateOutputList(PrgState prgState) {
        ObservableList<String> outputItems = FXCollections.observableArrayList(
                prgState.getOutput().getAll().stream().map(Object::toString).collect(Collectors.toList())
        );
        outListView.setItems(outputItems);
    }

    private void updateFileTable(PrgState prgState) {
        ObservableList<String> fileTableItems = FXCollections.observableArrayList(
                prgState.getFileTable().getContent().keySet().stream().map(Object::toString).collect(Collectors.toList())
        );
        fileTableListView.setItems(fileTableItems);
    }


    private void updateExeStack(PrgState prgState) {
        ObservableList<String> exeStackItems = FXCollections.observableArrayList(
                prgState.getExeStack().getStack().stream().map(Object::toString).collect(Collectors.toList())
        );
        exeStackListView.setItems(exeStackItems);
    }

    private PrgState getCurrentPrgState() {
        if (prgStateListView.getSelectionModel().isEmpty()) {
            return null;
        }
        int currentId = prgStateListView.getSelectionModel().getSelectedItem();
        return controller.getRepo().getPrgList().stream()
                .filter(prgState -> prgState.getId() == currentId)
                .findFirst()
                .orElse(null);
    }

    @FXML
    public void handlePrgStateSelection(MouseEvent mouseEvent) {
        updateAllViews();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}