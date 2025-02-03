package Model.Statements;

import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.Expressions.IExp;
import Model.Expressions.NegExp;
import Model.PrgState;
import Model.Types.Interfaces.IType;

public class RepTillStmt implements IStmt{

    IExp exp;
    IStmt stmt;

    public RepTillStmt( IStmt stmt, IExp exp){
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStmt newStmt = new CompStmt(stmt, new WhileStmt(new NegExp(exp), stmt));
        state.getExeStack().push(newStmt);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new RepTillStmt(stmt.deepCopy(), exp.deepcopy());
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        stmt.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "repeat{" + stmt.toString() + "} until(" + exp.toString() + ")";
    }
}
