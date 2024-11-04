import Controller.Ctrl;
import Controller.ICtrl;
import Model.DataStructures.Classes.MyDictionary;
import Model.DataStructures.Classes.MyQueue;
import Model.DataStructures.Classes.MyStack;
import Model.Expressions.ValueExp;
import Model.Expressions.VarExp;
import Model.PrgState;
import Model.Statements.*;
import Model.Types.Classes.IntType;
import Model.Values.Classes.IntValue;
import Repository.IRepo;
import Repository.Repo;
import View.View;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {



    public static void main(String[] args) {
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        PrgState st = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyQueue<>(), ex1);
        IRepo repo = new Repo();
        repo.addPrgState(st);
        ICtrl ctrl = new Ctrl(repo, true);
        View view = new View(repo, ctrl);
        view.run();
        }
    }