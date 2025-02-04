package Model.Statements;

import Exceptions.MyException;
import Model.DataStructures.Classes.MyDictionary;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Types.Interfaces.IType;
import Model.Values.Interfaces.IValue;

public class PrintStmt implements IStmt{

    IExp exp;

    public PrintStmt(IExp exp){
        this.exp=exp;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue val = exp.eval(state.getSymTable(), state.getHeap());
        //TODO check this 2 lines
        //state.getOut().add(val);
        state.getOutput().add(val);
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

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}