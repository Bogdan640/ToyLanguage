package Model.Statements;

import Exceptions.ExpressionEvaluationException;
import Exceptions.MyException;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Types.Classes.BoolType;
import Model.Values.Classes.BoolValue;
import Model.Values.Interfaces.IValue;

public class WhileStmt implements IStmt{
    private IExp exp;
    private IStmt stmt;

    public WhileStmt(IExp exp, IStmt stmt){
        this.exp = exp;
        this.stmt = stmt;
    }



    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue exp_value = exp.eval(state.getSymTable(), state.getHeap());
        if(!(exp_value.getType().equals(new BoolType())))
            throw ExpressionEvaluationException.type_mismatch("The expression " + exp.toString() + " is not a boolean");

        if(((BoolValue)exp_value).getValue()){
            state.getExeStack().push(this);
            state.getExeStack().push(stmt);
        }
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(exp.deepcopy(), stmt.deepCopy());
    }

    @Override
    public String toString() {
        return "while(" + exp.toString() + "){" + stmt.toString() + "}";
    }
}
