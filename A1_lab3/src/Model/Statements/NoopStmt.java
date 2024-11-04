package Model.Statements;

import Exceptions.MyException;
import Model.PrgState;

public class NoopStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return state;
    }

    @Override
    public String toString() {
        return "nop";
    }

    @Override
    public IStmt deepCopy() {
        return new NoopStmt();
    }
}
