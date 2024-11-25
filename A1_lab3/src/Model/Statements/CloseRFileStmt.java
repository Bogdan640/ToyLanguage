package Model.Statements;

import Exceptions.ExpressionEvaluationException;
import Exceptions.MyException;
import Exceptions.StatementExecutionException;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Types.Classes.StringType;
import Model.Values.Classes.StringValue;
import Model.Values.Interfaces.IValue;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStmt implements IStmt{

    private IExp exp;

    public CloseRFileStmt(IExp exp){
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue val = exp.eval(state.getSymTable(), state.getHeap());
        if (!val.getType().equals(new StringType()))
            throw ExpressionEvaluationException.type_mismatch("The expression must return a string value");
        StringValue filename = (StringValue) val;

        if(!state.getFileTable().exist(filename))
            throw StatementExecutionException.FileError("The file is not opened");
        try{
            BufferedReader br = state.getFileTable().find(filename);
            if (br == null)
                throw  StatementExecutionException.FileError("The file is not opened");
            br.close();
            state.getFileTable().remove(filename);
        } catch (IOException e) {
            throw StatementExecutionException.FileError("The file cannot be closed");
        }

        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new CloseRFileStmt(exp.deepcopy());
    }
    @Override
    public String toString(){
        return "Close file "+exp.toString();
    }
}
