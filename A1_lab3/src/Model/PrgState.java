package Model;

import Exceptions.DataStructureException;
import Model.DataStructures.Classes.MyDictionary;
import Model.DataStructures.Classes.MyHeap;
import Model.DataStructures.Classes.MyQueue;
import Model.DataStructures.Classes.MyStack;
import Model.DataStructures.Interfaces.*;
import Model.Statements.IStmt;
import Model.Values.Classes.StringValue;
import Model.Values.Interfaces.IValue;

import java.io.BufferedReader;

public class PrgState {
//    private int count = 1;
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, IValue> symTable;
    private MyIQueue<IValue> out;
    private MyIList<IValue> output;
    private IStmt originalProgram;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    private MyIHeap heap;

    private int id;
    private static  int global_id = 1;

    public static synchronized int generate_id(){
        return global_id++;
    }

    public int get_id(){
        return this.id;
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, IValue> dic, MyIQueue<IValue> q, IStmt prg,
                    MyIDictionary<StringValue, BufferedReader> fileTable, MyIHeap heap){
        this.exeStack=stk;
        this.symTable=dic;
        this.out=q;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram= prg.deepCopy();
        this.exeStack.push(originalProgram);
        this.id = generate_id();

    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, IValue> dic, MyIQueue<IValue> q, IStmt prg, MyIHeap heap){
        this.exeStack=stk;
        this.symTable=dic;
        this.out=q;
        this.heap = heap;
        this.originalProgram= prg.deepCopy();
        this.exeStack.push(originalProgram);
        this.id = generate_id();
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, IValue> dic, MyIList<IValue> q, IStmt prg,
                    MyIDictionary<StringValue, BufferedReader> fileTable, MyIHeap heap){
        this.exeStack=stk;
        this.symTable=dic;
        this.output=q;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram= prg.deepCopy();
        this.exeStack.push(originalProgram);
        this.id = generate_id();

    }


    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, IValue> dic, MyIList<IValue> q, IStmt prg, MyIHeap heap){
        this.exeStack=stk;
        this.symTable=dic;
        this.output=q;
        this.heap = heap;
        this.originalProgram= prg.deepCopy();
        this.exeStack.push(originalProgram);
        this.id = generate_id();
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable(){
        return this.fileTable;
    }
    public int getId() {
        return this.id;
    }
    public void setFileTable(MyDictionary<StringValue, BufferedReader> newFileTable){
        this.fileTable = newFileTable;
    }
    public MyIStack<IStmt> getExeStack(){
        return this.exeStack;
    }
    public void setExeStack(MyStack<IStmt> newexe){
        this.exeStack=newexe;
    }
    public MyIDictionary<String, IValue> getSymTable(){
        return this.symTable;
    }
    public void setSymTable(MyDictionary<String, IValue> newsym){
        this.symTable=newsym;
    }
    public MyIQueue<IValue> getOut(){
        return this.out;
    }
    public MyIList<IValue> getOutput(){
        return this.output;
    }
    public void setOut(MyQueue<IValue> newout){
        this.out=newout;
    }
    public IStmt getOriginalProgram(){
        return this.originalProgram;
    }
    public void setOriginalProgram(IStmt newprg){
        this.originalProgram=newprg;
    }
    public MyIHeap getHeap(){
        return this.heap;
    }
    public void setHeap(MyHeap newHeap){
        this.heap = newHeap;
    }
    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }
    public PrgState oneStep() throws Exception{
        if(exeStack.isEmpty())
            throw DataStructureException.data_structure_empty("The execution stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

//    public PrgState deepcopy(){
//        return new PrgState(this.exeStack.deepcopy(), this.symTable.deepcopy(), this.out.deepcopy(), this.originalProgram.deepcopy());
//    }

    @Override
    public String toString(){
        return "Program ID: "+ this.get_id()+
                "ExeStack:\n" + exeStack.toString() +
                "\nSymTable:\n" + symTable.toString() +
                "\nOut:\n" + out.toString() +
                "\nFileTable:\n" + fileTable.toString() +
                "\nHeap:\n" + heap.toString() + "\n";
    }
}
