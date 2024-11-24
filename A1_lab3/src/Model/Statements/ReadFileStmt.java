package Model.Statements;

import Exceptions.DataStructureException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.MyException;
import Exceptions.StatementExecutionException;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Types.Classes.IntType;
import Model.Types.Classes.StringType;
import Model.Values.Classes.IntValue;
import Model.Values.Classes.StringValue;
import Model.Values.Interfaces.IValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt{

    private IExp exp;
    private String var_name;

    public ReadFileStmt(IExp exp, String var_name){
        this.exp = exp;
        this.var_name = var_name;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(!state.getSymTable().exist(var_name))
            throw DataStructureException.not_defined_key("The variable is not defined");
        if(!state.getSymTable().find(var_name).getType().equals(new IntType()))
            throw ExpressionEvaluationException.type_mismatch("The variable is not an integer");

        IValue val = exp.eval(state.getSymTable());

        if (!val.getType().equals(new StringType()))
            throw ExpressionEvaluationException.type_mismatch("The expression must return a string");
        if(!state.getFileTable().exist((StringValue) val))
            throw DataStructureException.not_defined_key("The file is not opened");

        BufferedReader reader = state.getFileTable().find((StringValue) val);

        try{
            String line =reader.readLine();
            if (line.isEmpty()){
                state.getSymTable().update(var_name, new IntValue(0));
            }
            else{
                state.getSymTable().update(var_name, new IntValue(Integer.parseInt(line)));
            }
        } catch (IOException e) {
            throw StatementExecutionException.FileError("The file cannot be read");
        }
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new ReadFileStmt(exp.deepcopy(), var_name);
    }

    @Override
    public String toString() {
        return "readFile("+exp.toString()+", "+var_name+")";
    }
}
