package Model.Statements;

import Exceptions.MyException;
import Exceptions.StatementExecutionException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.DataStructures.Interfaces.MyIStack;
import Model.Expressions.IExp;
import Model.Expressions.RelationalExp;
import Model.Expressions.ValueExp;
import Model.Expressions.VarExp;
import Model.PrgState;
import Model.Types.Classes.IntType;
import Model.Types.Interfaces.IType;

import java.beans.Expression;

public class ForStmt implements IStmt{
    private String var;
    private IExp exp1;
    private IExp exp2;
    private IExp exp3;
    private IStmt stmt;

    public ForStmt(String var, IExp exp1, IExp exp2, IExp exp3, IStmt stmt){
        this.var = var;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        IStmt newStmt = new CompStmt(new VarDeclStmt(var, new IntType()),
                        new CompStmt(new AssignStmt(var, exp1),
                        new WhileStmt(new RelationalExp(new VarExp(var), "<", exp2), new CompStmt(stmt, new AssignStmt(var, exp3)))));

        state.getExeStack().push(newStmt);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new ForStmt(var, exp1.deepcopy(), exp2.deepcopy(), exp3.deepcopy(), stmt.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {

        IType typeexp1 = exp1.typecheck(typeEnv);
        IType typeexp2 = exp2.typecheck(typeEnv);
        IType typeexp3 = exp3.typecheck(typeEnv);
        if(typeexp1.equals(new IntType()) && typeexp2.equals(new IntType()) && typeexp3.equals(new IntType()))
        {
            stmt.typecheck(typeEnv);
            return typeEnv;
        }
        else
            throw StatementExecutionException.TypeMissmatch("The condition of for is not a boolean");
    }

    @Override
    public String toString() {
        return "for(" + var + "=" + exp1.toString() + ";" + var + "<" + exp2.toString() + ";" + var + "=" + exp3.toString() + "){" + stmt.toString() + "}";
    }
}
