package Model.Statements;

import Exceptions.ExpressionEvaluationException;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Types.Interfaces.IType;
import Model.Values.Interfaces.IValue;

public class CondAssignedStmt implements IStmt {

    private String var;
    private IExp exp1;
    private IExp exp2;
    private IExp exp3;

    public CondAssignedStmt(String var, IExp exp1, IExp exp2, IExp exp3) {
        this.var = var;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStmt newStmt = new IfStmt(exp1, new AssignStmt(var, exp2), new AssignStmt(var, exp3));
        state.getExeStack().push(newStmt);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CondAssignedStmt(new String(var), exp1.deepcopy(), exp2.deepcopy(), exp3.deepcopy());
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType typevar = typeEnv.find(var);
        IType typeexp1 = exp1.typecheck(typeEnv);
        IType typeexp2 = exp2.typecheck(typeEnv);
        IType typeexp3 = exp3.typecheck(typeEnv);
        if(typevar.equals(typeexp2) && typevar.equals(typeexp3))
            return typeEnv;
        else
            throw ExpressionEvaluationException.type_mismatch("The variable " + var + " and the expressions must have the same type");
    }

    @Override
    public String toString() {
        return var + " = (" + exp1.toString() + ") ? " + exp2.toString() + " : " + exp3.toString();
    }
}


