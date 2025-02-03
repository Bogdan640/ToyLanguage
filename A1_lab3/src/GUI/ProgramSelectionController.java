package GUI;

import Model.Types.Classes.IntType;
import Model.Types.Classes.RefType;
import Model.Values.Classes.IntValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import Model.Statements.IStmt;
import Model.Statements.VarDeclStmt;



import java.util.ArrayList;




import Model.Statements.*;
import Model.Expressions.*;




import java.util.List;
public class ProgramSelectionController {
    @FXML
    private ListView<String> programListView;
    @FXML
    private Button selectProgramButton;

    private List<IStmt> programs; // List of IStmt instances

    public void initialize() {
        programs = loadPrograms();
        programListView.setItems(FXCollections.observableArrayList(
                programs.stream().map(IStmt::toString).toList()
        ));
    }

    // Write the program examples
    private List<IStmt> loadPrograms() {
        List<IStmt> programExamples = new ArrayList<>();

        // example 1
        // int v; Ref int a; v=10;new(a,22);
        // fork(wH(a,30);v=32;print(v);print(rH(a)));
        // print(v);print(rH(a))
        VarDeclStmt varDeclStmt1 = new VarDeclStmt("v", new IntType());
        VarDeclStmt varDeclStmt2 = new VarDeclStmt("a", new RefType(new IntType()));
        AssignStmt assignStmt1 = new AssignStmt("v", new ValueExp(new IntValue(10)));
        HeapAllocationStmt memoryStmt1 = new HeapAllocationStmt("a", new ValueExp(new IntValue(22)));
        HeapWritingStmt writeHeapStmt1 = new HeapWritingStmt("a", new ValueExp(new IntValue(30)));
        AssignStmt assignStmt2 = new AssignStmt("v", new ValueExp(new IntValue(32)));
        PrintStmt printStmt1 = new PrintStmt(new VarExp("v"));
        PrintStmt printStmt2 = new PrintStmt(new HeapReadingExp(new VarExp("a")));
        ForkStmt forkStmt1 = new ForkStmt(new CompStmt(writeHeapStmt1, new CompStmt(assignStmt2, new CompStmt(printStmt1, printStmt2))));
        PrintStmt printStmt3 = new PrintStmt(new VarExp("v"));
        PrintStmt printStmt4 = new PrintStmt(new HeapReadingExp(new VarExp("a")));

        IStmt ex1 = new CompStmt(varDeclStmt1, new CompStmt(varDeclStmt2, new CompStmt(assignStmt1, new CompStmt(memoryStmt1, new CompStmt(forkStmt1, new CompStmt(printStmt3, printStmt4))))));

        programExamples.add(ex1);

        return programExamples;
    }

    @FXML
    private void handleSelectProgram() {
        int selectedIndex = programListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            IStmt selectedProgram = programs.get(selectedIndex);
            Stage currentStage = (Stage) selectProgramButton.getScene().getWindow();
            currentStage.close();

            MainExecutionWindow.launch(selectedProgram);
        }
    }
}