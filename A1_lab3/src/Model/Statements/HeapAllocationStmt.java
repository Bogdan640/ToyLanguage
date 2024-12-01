package Model.Statements;

import Exceptions.DataStructureException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Types.Classes.RefType;
import Model.Values.Classes.RefValue;
import Model.Values.Interfaces.IValue;

public class HeapAllocationStmt implements IStmt{

    private String var_name;
    private IExp exp;

    public HeapAllocationStmt(String var_name, IExp exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        if (!(symTable.exist(var_name)))
            throw DataStructureException.not_defined_key("The variable " + var_name + " is not declared");

        IValue variable = symTable.find(var_name);

        if (!(variable.getType() instanceof RefType))
            throw ExpressionEvaluationException.type_mismatch("The variable " + var_name + " is not a reference");

        RefValue refvalue = (RefValue) variable;
        IValue value = exp.eval(symTable, state.getHeap());

        if (!(refvalue.getLocationType().equals(value.getType())))
            throw ExpressionEvaluationException.type_mismatch("The variable " + var_name + " is not of the same type as the expression");

        int address = state.getHeap().allocate(value);
        symTable.update(var_name, new RefValue(address, value.getType()));

        return null;
    }



    @Override
    public IStmt deepCopy() {
        return new HeapAllocationStmt(new String(var_name), exp.deepcopy());
    }

    @Override
    public String toString() {
        return "new(" + var_name + ", " + exp.toString() + ")";
    }


}
