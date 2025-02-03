package Model.Statements;

import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.PrgState;
import Model.Types.Interfaces.IType;

public class SleepStmt implements IStmt{
    private int number;

    public SleepStmt(int number){
        this.number = number;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(number > 0){
            state.getExeStack().push(new SleepStmt(number - 1));
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new SleepStmt(number);
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return typeEnv;
    }
    @Override
    public String toString() {
        return "Sleep(" + number + ")";
    }
}
