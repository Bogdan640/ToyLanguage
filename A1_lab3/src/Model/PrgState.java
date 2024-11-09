package Model;

import Model.DataStructures.Classes.MyDictionary;
import Model.DataStructures.Classes.MyQueue;
import Model.DataStructures.Classes.MyStack;
import Model.DataStructures.Interfaces.MyIDictionary;
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

    public PrgState(MyStack<IStmt> stk, MyDictionary<String, IValue> dic, MyQueue<IValue> q, IStmt prg,
                    MyIDictionary<StringValue, BufferedReader> fileTable){
        this.exeStack=stk;
        this.symTable=dic;
        this.out=q;
        this.originalProgram= prg.deepCopy();
        this.exeStack.push(originalProgram);
        this.fileTable = fileTable;
    }

    public PrgState(MyStack<IStmt> stk, MyDictionary<String, IValue> dic, MyQueue<IValue> q, IStmt prg){
        this.exeStack=stk;
        this.symTable=dic;
        this.out=q;
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
//    public PrgState deepcopy(){
//        return new PrgState(this.exeStack.deepcopy(), this.symTable.deepcopy(), this.out.deepcopy(), this.originalProgram.deepcopy());
//    }

    @Override
    public String toString(){
        return "ExeStack: \n"+this.exeStack.toString()+"\nSymTable: \n"+this.symTable.toString()+"\nOut: "+this.out.toString()+"\n\n"+
                "FileTable: \n"+this.fileTable.toString()+"\n";
    }
}
