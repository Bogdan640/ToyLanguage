package Model.Statements;

import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.DataStructures.Interfaces.MyIStack;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.Classes.BoolType;
import Model.Values.Classes.BoolValue;
import Model.Values.Interfaces.IValue;

public class IfStmt implements IStmt{

    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;

    public  IfStmt(Exp e, IStmt t, IStmt el){
        this.exp=e;
        this.thenS=t;
        this.elseS=el;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, IValue> sym;
        MyIStack<IStmt> stk = state.getExeStack();
        sym= state.getSymTable();
        IValue val=exp.eval(sym);
        if(!val.getType().equals(new BoolType()))
            throw MyException.type_mismatch("The expression must return a boolean");
        BoolValue v = (BoolValue) val;
        if(v.getValue())
            stk.push(thenS);
        else
            stk.push(elseS);
        return state;
    }

    @Override
    public String toString() {
        return "if("+exp.toString()+"){ "+thenS.toString()+"}else{ "+elseS.toString()+"}";
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(exp.deepcopy(), thenS.deepCopy(), elseS.deepCopy());
    }
}
