package Model;

import Model.DataStructures.Classes.MyDictionary;
import Model.DataStructures.Classes.MyHeap;
import Model.DataStructures.Classes.MyQueue;
import Model.DataStructures.Classes.MyStack;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.DataStructures.Interfaces.MyIHeap;
import Model.DataStructures.Interfaces.MyIQueue;
import Model.DataStructures.Interfaces.MyIStack;
import Model.Statements.IStmt;
import Model.Values.Classes.StringValue;
import Model.Values.Interfaces.IValue;

import java.io.BufferedReader;

public class PrgState {
//    private int count = 1;
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, IValue> symTable;
    private MyIQueue<IValue> out;
    private IStmt originalProgram;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    private MyIHeap heap;

    public PrgState(MyStack<IStmt> stk, MyDictionary<String, IValue> dic, MyQueue<IValue> q, IStmt prg,
                    MyIDictionary<StringValue, BufferedReader> fileTable, MyHeap heap){
        this.exeStack=stk;
        this.symTable=dic;
        this.out=q;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram= prg.deepCopy();
        this.exeStack.push(originalProgram);

    }

    public PrgState(MyStack<IStmt> stk, MyDictionary<String, IValue> dic, MyQueue<IValue> q, IStmt prg, MyHeap heap){
        this.exeStack=stk;
        this.symTable=dic;
        this.out=q;
        this.heap = heap;
        this.originalProgram= prg.deepCopy();
        this.exeStack.push(originalProgram);

    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable(){
        return this.fileTable;
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

//    public PrgState deepcopy(){
//        return new PrgState(this.exeStack.deepcopy(), this.symTable.deepcopy(), this.out.deepcopy(), this.originalProgram.deepcopy());
//    }

    @Override
    public String toString(){
        return "ExeStack:\n" + exeStack.toString() +
                "\nSymTable:\n" + symTable.toString() +
                "\nOut:\n" + out.toString() +
                "\nFileTable:\n" + fileTable.toString() +
                "\nHeap:\n" + heap.toString() + "\n";
    }
}
