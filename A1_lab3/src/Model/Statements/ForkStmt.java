package Model.Statements;

import Exceptions.MyException;
import Model.DataStructures.Classes.MyDictionary;
import Model.DataStructures.Classes.MyStack;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.DataStructures.Interfaces.MyIStack;
import Model.PrgState;
import Model.Types.Interfaces.IType;
import Model.Values.Interfaces.IValue;

public class ForkStmt implements IStmt{
    IStmt stmt;

    public ForkStmt(IStmt stmt){
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> newStack = new MyStack<IStmt>();
        MyIDictionary<String, IValue> newSymTable = state.getSymTable().clone();
        if(state.getOut() != null )
            return new PrgState(newStack, newSymTable, state.getOut(), stmt, state.getFileTable() ,state.getHeap());
        return new PrgState(newStack, newSymTable, state.getOutput(), stmt, state.getFileTable() ,state.getHeap());
    }

    @Override
    public IStmt deepCopy() {
        return new ForkStmt(stmt.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return stmt.typecheck(typeEnv);
    }

    @Override
    public String toString() {
        return "fork(" + stmt.toString() + ")";
    }
}


