package Model.Statements;

import Exceptions.ExpressionEvaluationException;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.Expressions.ArithExp;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Types.Interfaces.IType;

public class SwitchStmt implements IStmt{

    private IExp exp1;
    private IExp exp2;
    private IExp exp3;
    private IStmt stmt1;
    private IStmt stmt2;
    private IStmt stmt3;

    public SwitchStmt(IExp exp1, IExp exp2, IExp exp3, IStmt stmt1, IStmt stmt2, IStmt stmt3){
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStmt newStmt = new IfStmt(new ArithExp(exp1,"==", exp2), stmt1, new IfStmt(new ArithExp(exp1, "==", exp3), stmt2, stmt3));
        state.getExeStack().push(newStmt);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new SwitchStmt(exp1.deepcopy(), exp2.deepcopy(), exp3.deepcopy(), stmt1.deepCopy(), stmt2.deepCopy(), stmt3.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType typeexp1 = exp1.typecheck(typeEnv);
        IType typeexp2 = exp2.typecheck(typeEnv);
        IType typeexp3 = exp3.typecheck(typeEnv);
        if(typeexp1.equals(typeexp2) && typeexp1.equals(typeexp3))
        {
            stmt1.typecheck(typeEnv);
            stmt2.typecheck(typeEnv);
            stmt3.typecheck(typeEnv);
            return typeEnv;
        }
        else
            throw ExpressionEvaluationException.type_mismatch("The expressions must have the same type");

    }

    @Override
    public String toString() {
        return "switch(" + exp1.toString() + ") case " + exp2.toString() + ": " + stmt1.toString() + " case " + exp3.toString() + ": " + stmt2.toString() + " default: " + stmt3.toString();
    }
}
