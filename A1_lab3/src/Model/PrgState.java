package Model;

import Model.DataStructures.Classes.MyDictionary;
import Model.DataStructures.Classes.MyQueue;
import Model.DataStructures.Classes.MyStack;
import Model.Statements.IStmt;
import Model.Values.Interfaces.IValue;

public class PrgState {
    private MyStack<IStmt> exeStack;
    private MyDictionary<String, IValue> symTable;
    private MyQueue<IValue> out;
    private IStmt originalProgram;

    public PrgState(MyStack<IStmt> stk, MyDictionary<String, IValue> dic, MyQueue<IValue> q, IStmt prg){
        this.exeStack=stk;
        this.symTable=dic;
        this.out=q;
        this.originalProgram=deepcopy(prg);
        this.exeStack.push(prg);
    }

    private IStmt deepcopy(IStmt program){
        return program;
        //FIXME;
    }

    public MyStack<IStmt> getExeStack(){
        return this.exeStack;
    }
    public void setExeStack(MyStack<IStmt> newexe){
        this.exeStack=newexe;
    }
    public MyDictionary<String, IValue> getSymTable(){
        return this.symTable;
    }
    public void setSymTable(MyDictionary<String, IValue> newsym){
        this.symTable=newsym;
    }
    public MyQueue<IValue> getOut(){
        return this.out;
    }
    public void setOut(MyQueue<IValue> newout){
        this.out=newout;
    }
    @Override
    public String toString(){
        return "ExeStack: "+this.exeStack.toString()+"\nSymTable: "+this.symTable.toString()+"\nOut: "+this.out.toString();
    }
}
