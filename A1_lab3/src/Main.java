import Controller.Ctrl;
import Controller.ICtrl;
import Exceptions.RepoException;
import Model.DataStructures.Classes.MyDictionary;
import Model.DataStructures.Classes.MyQueue;
import Model.DataStructures.Classes.MyStack;
import Model.Expressions.ArithExp;
import Model.Expressions.ValueExp;
import Model.Expressions.VarExp;
import Model.PrgState;
import Model.Statements.*;
import Model.Types.Classes.IntType;
import Model.Types.Classes.StringType;
import Model.Values.Classes.IntValue;
import Model.Values.Classes.StringValue;
import Repository.IRepo;
import Repository.Repo;
import View.Commands.ExitCmd;
import View.Commands.RunExample;
import View.TextMenu;

import java.io.IOException;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {



    public static void main(String[] args) throws IOException, RepoException {


        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        PrgState prg1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyQueue<>(), ex1, new MyDictionary<>());
        IRepo repo1 = new Repo("log1.txt");
        repo1.addPrgState(prg1);
        Ctrl ctrl1 = new Ctrl(repo1);




        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),"+", new
                                ArithExp(new ValueExp(new IntValue(3)),"*",new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithExp(new VarExp("a"),"+", new ValueExp(new
                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        PrgState prg2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyQueue<>(), ex2, new MyDictionary<>());
        IRepo repo2 = new Repo("log2.txt");
        repo2.addPrgState(prg2);
        Ctrl ctrl2 = new Ctrl(repo2);


        IStmt ex3 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new VarDeclStmt("varc", new IntType()),
                                new CompStmt(new OpenRFileStmt(new VarExp("varf")),
                                        new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),new CloseRFileStmt(new VarExp("varf")))))))));

        PrgState prg3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyQueue<>(), ex3, new MyDictionary<>());
        IRepo repo3 = new Repo("log3.txt");
        repo3.addPrgState(prg3);
        Ctrl ctrl3 = new Ctrl(repo3);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCmd("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctrl1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctrl2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctrl3));
        menu.show();



        }
    }