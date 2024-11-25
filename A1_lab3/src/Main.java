import Controller.Ctrl;
import Exceptions.RepoException;
import Model.DataStructures.Classes.MyDictionary;
import Model.DataStructures.Classes.MyHeap;
import Model.DataStructures.Classes.MyQueue;
import Model.DataStructures.Classes.MyStack;
import Model.Expressions.*;
import Model.PrgState;
import Model.Statements.*;
import Model.Types.Classes.IntType;
import Model.Types.Classes.RefType;
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
        PrgState prg1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyQueue<>(), ex1, new MyDictionary<>(), new MyHeap());
        IRepo repo1 = new Repo("log1.txt");
        repo1.addPrgState(prg1);
        Ctrl ctrl1 = new Ctrl(repo1);




        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),"+", new
                                ArithExp(new ValueExp(new IntValue(3)),"*",new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithExp(new VarExp("a"),"+", new ValueExp(new
                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        PrgState prg2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyQueue<>(), ex2, new MyDictionary<>(), new MyHeap());
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

        PrgState prg3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyQueue<>(), ex3, new MyDictionary<>(), new MyHeap());
        IRepo repo3 = new Repo("log3.txt");
        repo3.addPrgState(prg3);
        Ctrl ctrl3 = new Ctrl(repo3);


        IStmt ex4 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(2))),
                                new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(5))),
                                        new PrintStmt(new RelationalExp(new VarExp("a"),"<=", new VarExp("b")))))));

        PrgState prg4 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyQueue<>(), ex4, new MyDictionary<>(), new MyHeap());
        IRepo repo4 = new Repo("log4.txt");
        repo4.addPrgState(prg4);
        Ctrl ctrl4 = new Ctrl(repo4);


        IStmt ex5 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(2))),
                                new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(5))),
                                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("a"),"<=", new VarExp("b")),new AssignStmt("a",new ArithExp(new VarExp("a"), "+", new ValueExp(new IntValue(1))))),
                                                new PrintStmt(new VarExp("a")))))));

        PrgState prg5 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyQueue<>(), ex5, new MyDictionary<>(), new MyHeap());
        IRepo repo5 = new Repo("log5.txt");
        repo5.addPrgState(prg5);
        Ctrl ctrl5 = new Ctrl(repo5);


        //new statement that allocates, writes and reads from the heap
        // Example 1: test new heap memory
        // Ref int v; new(v,20); Ref Ref int a; new(a,v); print(v); print(a);
        VarDeclStmt varDeclStmt1 = new VarDeclStmt("v", new RefType(new IntType()));
        HeapAllocationStmt allocStmt1 = new HeapAllocationStmt("v", new ValueExp(new IntValue(20)));
        VarDeclStmt varDeclStmt2 = new VarDeclStmt("a", new RefType(new RefType(new IntType())));
        HeapAllocationStmt allocStmt2 = new HeapAllocationStmt("a", new VarExp("v"));
        PrintStmt printStmt1 = new PrintStmt(new VarExp("v"));
        PrintStmt printStmt2 = new PrintStmt(new VarExp("a"));

        IStmt ex6 = new CompStmt(varDeclStmt1,
                new CompStmt(allocStmt1,
                        new CompStmt(varDeclStmt2,
                                new CompStmt(allocStmt2,
                                        new CompStmt(printStmt1, printStmt2)))));
        
        PrgState prg6 = new PrgState(new MyStack<IStmt>(), new MyDictionary<>(), new MyQueue<>(), ex6, new MyDictionary<>(), new MyHeap());
        IRepo repo6 = new Repo("log6.txt");
        repo6.addPrgState(prg6);
        Ctrl ctrl6 = new Ctrl(repo6);

        
        
        
        // Example 2: test read heap
        // Ref int v; new(v,20); Ref Ref int a; new(a,v); print(rH(v)); print(rH(rH(a)) + 5);
        VarDeclStmt varDeclStmt3 = new VarDeclStmt("v", new RefType(new IntType()));
        HeapAllocationStmt allocStmt3 = new HeapAllocationStmt("v", new ValueExp(new IntValue(20)));
        VarDeclStmt varDeclStmt4 = new VarDeclStmt("a", new RefType(new RefType(new IntType())));
        HeapAllocationStmt allocStmt4 = new HeapAllocationStmt("a", new VarExp("v"));
        PrintStmt printStmt3 = new PrintStmt(new HeapReadingExp(new VarExp("v")));
        PrintStmt printStmt4 = new PrintStmt(
                new ArithExp(new HeapReadingExp(new HeapReadingExp(new VarExp("a"))),"+", new ValueExp(new IntValue(5))));

        IStmt ex7 = new CompStmt(varDeclStmt3,
                new CompStmt(allocStmt3,
                        new CompStmt(varDeclStmt4,
                                new CompStmt(allocStmt4,
                                        new CompStmt(printStmt3, printStmt4)))));

        PrgState prg7 = new PrgState(new MyStack<IStmt>(), new MyDictionary<>(), new MyQueue<>(), ex7, new MyDictionary<>(), new MyHeap());
        IRepo repo7 = new Repo("log7.txt");
        repo7.addPrgState(prg7);
        Ctrl ctrl7 = new Ctrl(repo7);
        
        

        // Example 3: test write heap
        // Ref int v; new(v,20); print(rH(v)); wH(v,30); print(rH(v) + 5);
        VarDeclStmt varDeclStmt5 = new VarDeclStmt("v", new RefType(new IntType()));
        HeapAllocationStmt allocStmt5 = new HeapAllocationStmt("v", new ValueExp(new IntValue(20)));
        PrintStmt printStmt5 = new PrintStmt(new HeapReadingExp(new VarExp("v")));
        HeapWritingStmt writeHeap1 = new HeapWritingStmt("v", new ValueExp(new IntValue(30)));
        PrintStmt printStmt6 = new PrintStmt(
                new ArithExp(new HeapReadingExp(new VarExp("v")),"+", new ValueExp(new IntValue(5))));

        IStmt ex8 = new CompStmt(varDeclStmt5,
                new CompStmt(allocStmt5,
                        new CompStmt(printStmt5,
                                new CompStmt(writeHeap1, printStmt6))));

        PrgState prg8 = new PrgState(new MyStack<IStmt>(), new MyDictionary<>(), new MyQueue<>(), ex8, new MyDictionary<>(), new MyHeap());
        IRepo repo8 = new Repo("log8.txt");
        repo8.addPrgState(prg8);
        Ctrl ctrl8 = new Ctrl(repo8);

        // Example 4: test garbage collector
        // Ref int v; new(v,20) ;Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)));

        VarDeclStmt varDeclStmt6 = new VarDeclStmt("v", new RefType(new IntType()));
        HeapAllocationStmt allocStmt6 = new HeapAllocationStmt("v", new ValueExp(new IntValue(20)));
        VarDeclStmt varDeclStmt7 = new VarDeclStmt("a", new RefType(new RefType(new IntType())));
        HeapAllocationStmt allocStmt7 = new HeapAllocationStmt("a", new VarExp("v"));
        HeapAllocationStmt allocStmt8 = new HeapAllocationStmt("v", new ValueExp(new IntValue(30)));
        PrintStmt printStmt7 = new PrintStmt(new HeapReadingExp(new HeapReadingExp(new VarExp("a"))));

        IStmt ex9 = new CompStmt(varDeclStmt6,
                new CompStmt(allocStmt6,
                        new CompStmt(varDeclStmt7,
                                new CompStmt(allocStmt7,
                                        new CompStmt(allocStmt8, printStmt7)))));

        PrgState prg9 = new PrgState(new MyStack<IStmt>(), new MyDictionary<>(), new MyQueue<>(), ex9, new MyDictionary<>(), new MyHeap());
        IRepo repo9 = new Repo("log9.txt");
        repo9.addPrgState(prg9);
        Ctrl ctrl9 = new Ctrl(repo9);


        // Ref int v;
        // new(v, 20);
        // Ref Ref int a;
        // new(a, v);
        // new(v, 30);
        // print(rH(rH(a)));
        VarDeclStmt varDeclStmt11 = new VarDeclStmt("v", new RefType(new IntType())); // Ref int v;
        HeapAllocationStmt allocStmt11 = new HeapAllocationStmt("v", new ValueExp(new IntValue(20))); // new(v, 20);
        VarDeclStmt varDeclStmt21 = new VarDeclStmt("a", new RefType(new RefType(new IntType()))); // Ref Ref int a;
        HeapAllocationStmt allocStmt21 = new HeapAllocationStmt("a", new VarExp("v")); // new(a, v);
        HeapAllocationStmt allocStmt31 = new HeapAllocationStmt("v", new ValueExp(new IntValue(30))); // new(v, 30);
        PrintStmt printStmt11 = new PrintStmt(new HeapReadingExp(new HeapReadingExp(new VarExp("a")))); // print(rH(rH(a)));

        IStmt exGarbageCollector = new CompStmt(varDeclStmt11,
                new CompStmt(allocStmt11,
                        new CompStmt(varDeclStmt21,
                                new CompStmt(allocStmt31,
                                        new CompStmt(allocStmt21, printStmt11)))));
        PrgState prgGarbageCollector = new PrgState(new MyStack<IStmt>(), new MyDictionary<>(), new MyQueue<>(), exGarbageCollector, new MyDictionary<>(), new MyHeap());
        IRepo repoGarbageCollector = new Repo("logGarbageCollector.txt");
        repoGarbageCollector.addPrgState(prgGarbageCollector);
        Ctrl ctrlGarbageCollector = new Ctrl(repoGarbageCollector);








        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCmd("0", "exit"));
        menu.addCommand(new RunExample("1", ex6.toString(), ctrl1));
        menu.addCommand(new RunExample("2", ex7.toString(), ctrl2));
        menu.addCommand(new RunExample("3", ex8.toString(), ctrl3));
        menu.addCommand(new RunExample("4", ex9.toString(), ctrl4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctrl5));
        menu.addCommand(new RunExample("6", ex6.toString(), ctrl6));
        menu.addCommand(new RunExample("7", ex7.toString(), ctrl7));
        menu.addCommand(new RunExample("8", ex8.toString(), ctrl8));
        menu.addCommand(new RunExample("9", ex9.toString(), ctrl9));
        menu.addCommand(new RunExample("10", exGarbageCollector.toString(), ctrlGarbageCollector));


        menu.show();



        }
    }