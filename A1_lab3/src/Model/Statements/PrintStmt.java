package Model.Statements;

import Exceptions.MyException;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Values.Interfaces.IValue;

public class PrintStmt implements IStmt{

    IExp exp;

    public PrintStmt(IExp exp){
        this.exp=exp;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue val = exp.eval(state.getSymTable(), state.getHeap());
        state.getOut().add(val);
        return null;
    }

    @Override
    public String toString() {
        return "print("+exp.toString()+");";
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepcopy());
    }
}
