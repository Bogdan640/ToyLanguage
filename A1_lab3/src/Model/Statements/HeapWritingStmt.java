package Model.Statements;

import Exceptions.DataStructureException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.DataStructures.Interfaces.MyIHeap;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Types.Classes.RefType;
import Model.Values.Classes.RefValue;
import Model.Values.Interfaces.IValue;

public class HeapWritingStmt implements IStmt{

    private String var_name;
    private IExp exp;

    public HeapWritingStmt(String var_name, IExp exp) {
        this.var_name = var_name;
        this.exp = exp;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, IValue> symtable = state.getSymTable();
        if(!(symtable.exist(var_name)))
            throw DataStructureException.not_defined_key("The variable " + var_name + " is not declared");

        IValue variable = symtable.find(var_name);
        if(!(variable.getType() instanceof RefType))
            throw ExpressionEvaluationException.type_mismatch("The variable " + var_name + " is not a reference");

        int address = ((RefValue) variable).getAddress();

        MyIHeap heap = state.getHeap();
        if(!(heap.contains(address)))
            throw DataStructureException.not_defined_key("The address " + address + " is not allocated in the heap");

        IValue exp_value = exp.eval(symtable, state.getHeap());
        if(!(exp_value.getType().equals(((RefValue) variable).getLocationType())))
            throw ExpressionEvaluationException.type_mismatch("The variable " + var_name + " is not of the same type as the expression");

        heap.insert(address, exp_value);
        return state;
    }

    @Override
    public String toString() {
        return "wH("+this.var_name + ", " + this.exp.toString() + ");";
    }

    @Override
    public IStmt deepCopy() {
        return new HeapWritingStmt(new String(var_name), exp.deepcopy());
    }
}
