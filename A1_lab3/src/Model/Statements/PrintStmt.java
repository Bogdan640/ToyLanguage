package Model.Statements;

import Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Values.Interfaces.IValue;

public class PrintStmt implements IStmt{

    Exp exp;

    public PrintStmt(Exp exp){
        this.exp=exp;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue val = exp.eval(state.getSymTable());
        state.getOut().add(val);
        return state;
    }

    @Override
    public String toString() {
        return "print("+exp.toString()+")";
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepcopy());
    }
}
