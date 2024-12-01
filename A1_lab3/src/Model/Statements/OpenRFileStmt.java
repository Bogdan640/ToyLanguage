package Model.Statements;

import Exceptions.DataStructureException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.MyException;
import Exceptions.StatementExecutionException;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Types.Classes.StringType;
import Model.Values.Classes.StringValue;
import Model.Values.Interfaces.IValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFileStmt implements IStmt{

    private IExp exp;

    public OpenRFileStmt(IExp exp){
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue val =exp.eval(state.getSymTable(), state.getHeap());
        if(!val.getType().equals(new StringType())){
            throw  ExpressionEvaluationException.type_mismatch("The expression must return a string");
        }
        StringValue filename = (StringValue) val;
        if(state.getFileTable().exist(filename))
            throw DataStructureException.defined_key("The file is already opened");
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename.getValue()));
            state.getFileTable().add(filename, reader);
        } catch (FileNotFoundException e) {
            throw StatementExecutionException.FileError("The file does not exist");
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new OpenRFileStmt(exp.deepcopy());
    }

    @Override
    public String toString() {
        return "openRFile("+exp.toString()+")";
    }
}
