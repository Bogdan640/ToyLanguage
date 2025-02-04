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
        MyIStack<MyIDictionary<String, IValue>> newSymTableStack = state.getSymTableStack().deepcopy();
        return new PrgState(newStack, newSymTableStack, state.getOutput(),stmt, state.getFileTable(), state.getHeap(), state.getLockTable(), state.getProcTable());
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

