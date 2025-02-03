package GUI;

import Exceptions.MyException;
import Model.DataStructures.Classes.MyDictionary;
import Model.Types.Classes.IntType;
import Model.Types.Classes.RefType;
import Model.Types.Interfaces.IType;
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

    public void initialize() throws MyException {
        programs = loadPrograms();
        programListView.setItems(FXCollections.observableArrayList(
                programs.stream().map(IStmt::toString).toList()
        ));
    }

    // Write the program examples
    private List<IStmt> loadPrograms() throws MyException {
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

        // example 2
        //Ref int a;  new (a, 20); (for (v=0; v<3; v = v+1) fork(print (v); v=v*rH(a))); print(rH(a))
        //VarDeclStmt v1 = new VarDeclStmt("v", new IntType());
        VarDeclStmt v1 = new VarDeclStmt("v", new IntType());
        VarDeclStmt v2 = new VarDeclStmt("a", new RefType(new IntType()));
        HeapAllocationStmt h1 = new HeapAllocationStmt("a", new ValueExp(new IntValue(20)));
        PrintStmt p1 = new PrintStmt(new VarExp("v"));
        AssignStmt a1 = new AssignStmt("v", new ArithExp(new VarExp("v"), "*", new HeapReadingExp(new VarExp("a"))));
        ForkStmt f1 = new ForkStmt(new CompStmt(p1, a1));
        ForStmt forStmt = new ForStmt("v", new ValueExp(new IntValue(0)), new ValueExp(new IntValue(3)), new ArithExp(new VarExp("v"), "+", new ValueExp(new IntValue(1))),f1);
        PrintStmt p2 = new PrintStmt(new HeapReadingExp(new VarExp("a")));
        IStmt ex2 = new CompStmt(v2, new CompStmt(h1, new CompStmt(forStmt, p2)));


        programExamples.add(ex2);
//        try{
//            ex2.typecheck(new MyDictionary<String, IType>());
//        }catch (MyException e){
//            System.out.println(e.getMessage());
//        }


        // example 3
        IStmt ex3 = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("v2", new RefType(new IntType())),
                        new CompStmt(new VarDeclStmt("x", new IntType()),
                                new CompStmt(new VarDeclStmt("q", new IntType()),
                                        new CompStmt(new HeapAllocationStmt("v1", new ValueExp(new IntValue(20))),
                                                new CompStmt(new HeapAllocationStmt("v2", new ValueExp(new IntValue(30))),
                                                        new CompStmt(new NewLockStmt("x"),
                                                                new CompStmt(new ForkStmt(
                                                                        new CompStmt(new ForkStmt(
                                                                                new CompStmt(new LockStmt("x"),
                                                                                        new CompStmt(new HeapWritingStmt("v1", new ArithExp( new HeapReadingExp(new VarExp("v1")),"-", new ValueExp(new IntValue(1)))),
                                                                                                new UnlockStmt("x")))
                                                                        ),
                                                                                new CompStmt(new LockStmt("x"),
                                                                                        new CompStmt(new HeapWritingStmt("v1", new ArithExp( new HeapReadingExp(new VarExp("v1")),"*", new ValueExp(new IntValue(10)))),
                                                                                                new UnlockStmt("x"))))
                                                                ),
                                                                        new CompStmt( new NewLockStmt("q"),
                                                                                new CompStmt(new ForkStmt(
                                                                                        new CompStmt( new ForkStmt(
                                                                                                new CompStmt(new LockStmt("q"),
                                                                                                        new CompStmt(new HeapWritingStmt("v2", new ArithExp( new HeapReadingExp(new VarExp("v2")),"+", new ValueExp(new IntValue(5)))),
                                                                                                                new UnlockStmt("q")))
                                                                                        ),
                                                                                                new CompStmt(new LockStmt("q"),
                                                                                                        new CompStmt(new HeapWritingStmt("v2", new ArithExp( new HeapReadingExp(new VarExp("v2")),"*", new ValueExp(new IntValue(10)))),
                                                                                                                new UnlockStmt("q"))))
                                                                                ),
                                                                                        new CompStmt(new NoopStmt(),
                                                                                                new CompStmt(new NoopStmt(),
                                                                                                        new CompStmt(new NoopStmt(),
                                                                                                                new CompStmt(new NoopStmt(),
                                                                                                                        new CompStmt(new LockStmt("x"),
                                                                                                                                new CompStmt(new PrintStmt(new HeapReadingExp(new VarExp("v1"))),
                                                                                                                                        new CompStmt(new UnlockStmt("x"),
                                                                                                                                                new CompStmt(new LockStmt("q"),
                                                                                                                                                        new CompStmt(new PrintStmt(new HeapReadingExp(new VarExp("v2"))),
                                                                                                                                                                new UnlockStmt("q"))))))))))))))))))));

        programExamples.add(ex3);


        // example 4
        // int v; sleep(10) Ref int a; v=10;new(a,22);
        IStmt ex4 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new SleepStmt(10),
                        new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                        new HeapAllocationStmt("a", new ValueExp(new IntValue(22)))))));

        programExamples.add(ex4);

        // example 5
        // int v; Ref int a; v=(0==0)?1:2;new(a,22);
        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new CondAssignedStmt("v", new ArithExp(new ValueExp(new IntValue(11)), "==",new ValueExp(new IntValue(10))), new ValueExp(new IntValue(0)), new ValueExp(new IntValue(1))),
                                new HeapAllocationStmt("a", new ValueExp(new IntValue(22))))));

        programExamples.add(ex5);


        IStmt ex6 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new VarDeclStmt("c", new IntType()),
                                new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(1))),
                                        new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(2))),
                                                new CompStmt(new AssignStmt("c", new ValueExp(new IntValue(5))),
                                                        new CompStmt(new SwitchStmt(new ArithExp( new VarExp("a"),"*", new ValueExp(new IntValue(10))),
                                                                new ArithExp( new VarExp("b"),"*", new VarExp("c")),
                                                                new ValueExp(new IntValue(10)),
                                                                new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b"))),
                                                                new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))), new PrintStmt(new ValueExp(new IntValue(200)))),
                                                                new PrintStmt(new ValueExp(new IntValue(300)))),
                                                                new PrintStmt(new ValueExp(new IntValue(300))))))))));
        programExamples.add(ex6);
        
        
        // example 7
        IStmt ex7 = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new VarDeclStmt("x", new IntType()),
                        new CompStmt(
                                new VarDeclStmt("y", new IntType()),
                                new CompStmt(
                                        new AssignStmt("v", new ValueExp(new IntValue(0))),
                                        new CompStmt(
                                                new RepTillStmt(
                                                        new CompStmt(
                                                                new ForkStmt(
                                                                        new CompStmt(
                                                                                new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp( new VarExp("v"),"-", new ValueExp(new IntValue(1)))))), new AssignStmt("v", new ArithExp( new VarExp("v"),"+", new ValueExp(new IntValue(1))))), new RelationalExp( new VarExp("v"),"==", new ValueExp(new IntValue(3)))),
                                                new CompStmt(
                                                        new AssignStmt("x", new ValueExp(new IntValue(1))),
                                                        new CompStmt(
                                                                new NoopStmt(),
                                                                new CompStmt(
                                                                        new AssignStmt("y", new ValueExp(new IntValue(3))),
                                                                        new CompStmt(
                                                                                new NoopStmt(),
                                                                                new PrintStmt(new ArithExp( new VarExp("v"),"*", new ValueExp(new IntValue(10))))))))))
                        )));
        programExamples.add(ex7);
        
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