package Model.Statements;

import Exceptions.DataStructureException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.DataStructures.Interfaces.MyILockTable;
import Model.PrgState;
import Model.Types.Classes.IntType;
import Model.Types.Interfaces.IType;
import Model.Values.Classes.IntValue;
import Model.Values.Interfaces.IValue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnlockStmt implements IStmt{
    private String var;
    private static Lock lock = new ReentrantLock();

    public UnlockStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        MyIDictionary<String, IValue> symtable = state.getSymTable();
        MyILockTable locktable = state.getLockTable();
        if(symtable.exist(var)){
            if(symtable.find(var).getType().equals(new IntType())){
                IntValue fi = (IntValue) symtable.find(var);
                int foundIndex = fi.getValue();
                if(locktable.exist(foundIndex)){
                    if(locktable.find(foundIndex) == state.getId()){
                        locktable.update(foundIndex, -1);
                        state.setLockTable(locktable);
                    }
                    else{
                        throw DataStructureException.not_defined_key("The index " + foundIndex + " is not in the lock table");
                    }
                }
                else{
                    throw DataStructureException.not_defined_key("The index " + foundIndex + " is not in the lock table");
                }
            }
            else
                throw DataStructureException.type_mismatch("The variable " + var + " is not an integer");
        }
        else
            throw DataStructureException.not_defined_key("The variable " + var + " is not defined");

        lock.unlock();
        return null;

    }

    @Override
    public IStmt deepCopy() {
        return new UnlockStmt(new String(var));
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType typevar = typeEnv.find(var);
        if(typevar.equals(new IntType()))
            return typeEnv;
        else
            throw ExpressionEvaluationException.type_mismatch("The variable " + var + " is not an integer");
    }

    @Override
    public String toString() {
        return "unlock(" + var + ");";
    }
}