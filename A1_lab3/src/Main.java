import Controller.Ctrl;
import Controller.ICtrl;
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
import View.View;

import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {



    public static void main(String[] args) throws IOException {
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));

        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),"+", new
                                ArithExp(new ValueExp(new IntValue(3)),"*",new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithExp(new VarExp("a"),"+", new ValueExp(new
                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));



//        IStmt ex3 = new CompStmt(new VarDeclStmt("varf", new StringType()),
//                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue()))))

//        CloseRFileStmt st49 = new CloseRFileStmt(new VarExp("varf"));
//        CompStmt st48 = new CompStmt(new PrintStmt(new VarExp("varc")), st49);
//        CompStmt st47 = new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), st48);
//        CompStmt st46 = new CompStmt(new PrintStmt(new VarExp("varc")), st47);
//        CompStmt st45 = new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), st46);
//        CompStmt st44 = new CompStmt(new VarDeclStmt("varc", new IntType()), st45);
//        CompStmt st43 = new CompStmt(new OpenRFileStmt(new VarExp("varf")), st44);
//        CompStmt st42 = new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))), st43);
//        IStmt st4 = new CompStmt(new VarDeclStmt("varf", new StringType()), st42);
//        PrgState p = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyQueue<>(), st4, new MyDictionary<>());
//


        IStmt ex3 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new VarDeclStmt("varc", new IntType()),
                                new CompStmt(new OpenRFileStmt(new VarExp("varf")),
                                        new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),new CloseRFileStmt(new VarExp("varf")))))))));

        PrgState p = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyQueue<>(), ex3, new MyDictionary<>());


        PrgState st = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyQueue<>(), ex1);
        PrgState st2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyQueue<>(), ex2);
        //PrgState st3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyQueue<>(), ex3, new MyDictionary<>());


        IRepo repo = new Repo("log21.txt");
        repo.addPrgState(st);
        repo.addPrgState(st2);
        repo.addPrgState(p);
        //repo.addPrgState(st3);
        ICtrl ctrl = new Ctrl(repo, true);
        View view = new View(repo, ctrl);
        view.run();
        }
    }