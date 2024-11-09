package Model.Statements;

import Exceptions.MyException;
import Model.PrgState;

public class CompStmt implements IStmt{

    IStmt first;
    IStmt second;

    public CompStmt(IStmt first, IStmt second){
//        if (first == null || second == null) {
//            if (first == null)
//                System.out.println(second.toString());
//            else
//                System.out.println(first.toString());
//            if (first == null && second == null)
//                System.out.println("\nBoth are null");
//            throw new IllegalArgumentException("Arguments to CompStmt cannot be null");
//        }
        this.first=first;
        this.second=second;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        state.getExeStack().push(second);
        state.getExeStack().push(first);
        return state;
    }

    @Override
    public String toString() {
        return first.toString()+"\n"+second.toString();
    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt(first.deepCopy(), second.deepCopy());
    }
}
