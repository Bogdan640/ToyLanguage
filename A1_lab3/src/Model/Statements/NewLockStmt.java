package Model.Statements;


import Exceptions.MyException;
import Exceptions.StatementExecutionException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.DataStructures.Interfaces.MyILockTable;
import Model.PrgState;
import Model.Types.Classes.IntType;
import Model.Types.Interfaces.IType;
import Model.Values.Classes.IntValue;
import Model.Values.Interfaces.IValue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLockStmt implements IStmt{

    private String var;
    private static  Lock lock = new ReentrantLock();

    public NewLockStmt(String var){
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        MyILockTable lockTable = state.getLockTable();
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        int freeLocation = lockTable.getFreeValue();
        lockTable.add(freeLocation, -1);
        if(symTable.exist(var) && symTable.find(var).getType().equals(new IntType()))
            symTable.update(var, new IntValue(freeLocation));
        else
           throw StatementExecutionException.TypeMissmatch("The variable " + var + " is not an integer");
        lock.unlock();
        return null;

    }

    @Override
    public IStmt deepCopy() {
        return new NewLockStmt(new String(var));
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType typevar = typeEnv.find(var);
        if(typevar.equals(new IntType()))
            return typeEnv;
        else
            throw StatementExecutionException.TypeMissmatch("The variable " + var + " is not an integer");
    }

    @Override
    public String toString(){
        return "NewLock(" + var + ")";
    }
}
