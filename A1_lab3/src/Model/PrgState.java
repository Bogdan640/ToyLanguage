package Model;

import Exceptions.DataStructureException;
import Exceptions.MyException;
import Model.DataStructures.Classes.MyDictionary;
import Model.DataStructures.Classes.MyHeap;
import Model.DataStructures.Classes.MyQueue;
import Model.DataStructures.Classes.MyStack;
import Model.DataStructures.Interfaces.*;
import Model.Statements.IStmt;
import Model.Values.Classes.StringValue;
import Model.Values.Interfaces.IValue;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.util.List;

public class PrgState {
    //    private int count = 1;
    private MyIStack<IStmt> exeStack;
    //private MyIDictionary<String, IValue> symTable;
    private MyIStack<MyIDictionary<String, IValue>> symTableStack;
    private MyIQueue<IValue> out;
    private MyIList<IValue> output;
    private IStmt originalProgram;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    private MyIHeap heap;
    private MyILockTable lockTable;
    private MyIProc<String, Pair<List<String>, IStmt>> procTable;

    private int id;
    private static  int global_id = 1;



    public static synchronized int generate_id(){
        return global_id++;
    }

    public int get_id(){
        return this.id;
    }

        public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, IValue> dic, MyIList<IValue> q, IStmt prg,
                    MyIDictionary<StringValue, BufferedReader> fileTable, MyIHeap heap, MyILockTable lockTable, MyIProc<String, Pair<List<String>, IStmt>> procTable){
        this.exeStack=stk;
        this.symTableStack = new MyStack<MyIDictionary<String, IValue>>();
        this.symTableStack.push(dic);
        this.output=q;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram= prg.deepCopy();
        this.exeStack.push(originalProgram);
        this.id = generate_id();
        this.lockTable = lockTable;
        this.procTable = procTable;

    }

    public PrgState(MyIStack<IStmt> stk, MyIStack<MyIDictionary<String, IValue>> dicStack, MyIList<IValue> q, IStmt prg,
                    MyIDictionary<StringValue, BufferedReader> fileTable, MyIHeap heap, MyILockTable lockTable, MyIProc<String, Pair<List<String>, IStmt>> procTable){
        this.exeStack=stk;
        this.symTableStack = dicStack;
        this.output=q;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram= prg.deepCopy();
        this.exeStack.push(originalProgram);
        this.id = generate_id();
        this.lockTable = lockTable;
        this.procTable = procTable;

    }





//    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, IValue> dic, MyIQueue<IValue> q, IStmt prg,
//                    MyIDictionary<StringValue, BufferedReader> fileTable, MyIHeap heap, MyILockTable lockTable){
//        this.exeStack=stk;
//        this.symTable=dic;
//        this.out=q;
//        this.fileTable = fileTable;
//        this.heap = heap;
//        this.originalProgram= prg.deepCopy();
//        this.exeStack.push(originalProgram);
//        this.id = generate_id();
//        this.lockTable = lockTable;
//
//    }
//
//    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, IValue> dic, MyIQueue<IValue> q, IStmt prg, MyIHeap heap, MyILockTable lockTable){
//        this.exeStack=stk;
//        this.symTable=dic;
//        this.out=q;
//        this.heap = heap;
//        this.originalProgram= prg.deepCopy();
//        this.exeStack.push(originalProgram);
//        this.id = generate_id();
//        this.lockTable = lockTable;
//    }
//
//    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, IValue> dic, MyIList<IValue> q, IStmt prg,
//                    MyIDictionary<StringValue, BufferedReader> fileTable, MyIHeap heap, MyILockTable lockTable){
//        this.exeStack=stk;
//        this.symTable=dic;
//        this.output=q;
//        this.fileTable = fileTable;
//        this.heap = heap;
//        this.originalProgram= prg.deepCopy();
//        this.exeStack.push(originalProgram);
//        this.id = generate_id();
//        this.lockTable = lockTable;
//
//    }
//
//
//    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, IValue> dic, MyIList<IValue> q, IStmt prg, MyIHeap heap, MyILockTable lockTable){
//        this.exeStack=stk;
//        this.symTable=dic;
//        this.output=q;
//        this.heap = heap;
//        this.originalProgram= prg.deepCopy();
//        this.exeStack.push(originalProgram);
//        this.id = generate_id();
//        this.lockTable = lockTable;
//    }



    public MyIProc<String, Pair<List<String>, IStmt>> getProcTable(){
        return this.procTable;
    }
    public MyILockTable getLockTable(){
        return this.lockTable;
    }
    public void setLockTable(MyILockTable newLockTable){
        this.lockTable = newLockTable;
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
    public MyIDictionary<String, IValue> getSymTable() throws MyException {
        return this.symTableStack.top();
    }

    public MyIStack<MyIDictionary<String, IValue>> getSymTableStack(){
        return this.symTableStack;
    }
    public void setSymTableStack(MyStack<MyIDictionary<String, IValue>> newSymTableStack){
        this.symTableStack = newSymTableStack;
    }


//    public void setSymTable(MyDictionary<String, IValue> newsym){
//        this.symTable=newsym;
//    }
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

    String lockTableToString() throws DataStructureException {
        StringBuilder result = new StringBuilder();
        for(int key: lockTable.keySet()){
            result.append(String.format("%d -> %d\n", key, lockTable.find(key)));;
        }
        return result.toString();
    }

    public String procedureTableToString() throws MyException {
        StringBuilder procedureTableStringBuilder = new StringBuilder();
        for (String key: procTable.getAddresses()) {
            procedureTableStringBuilder.append(String.format("%s - %s: %s\n", key, procTable.getContent(key).getKey(), procTable.getContent(key).getValue()));
        }
        procedureTableStringBuilder.append("\n");
        return procedureTableStringBuilder.toString();
    }

    @Override
    public String toString(){
        if(getOut() == null)
            return "Program ID: "+ this.get_id()+
                    "ExeStack:\n" + exeStack.toString() +
                    "\nSymTable:\n" + symTableStack.toString() +
                    "\nOut:\n" + output.toString() +
                    "\nFileTable:\n" + fileTable.toString() +
                    "\nHeap:\n" + heap.toString() +
                    "Procedure Table:\n" + procTable.toString() + "\n";

        return "Program ID: "+ this.get_id()+
                "ExeStack:\n" + exeStack.toString() +
                "\nSymTable:\n" + symTableStack.toString() +
                "\nOut:\n" + out.toString() +
                "\nFileTable:\n" + fileTable.toString() +
                "\nHeap:\n" + heap.toString() +
                "Procedure Table:\n" + procTable.toString() + "\n";
    }
}