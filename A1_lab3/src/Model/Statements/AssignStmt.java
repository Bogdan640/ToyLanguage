package Model.Statements;

import Exceptions.DataStructureException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.DataStructures.Interfaces.MyIStack;
import Model.Expressions.IExp;
import Model.PrgState;
import Model.Types.Interfaces.IType;
import Model.Values.Interfaces.IValue;

public class AssignStmt implements IStmt{

    String key;
    IExp exp;

    public AssignStmt(String key, IExp exp){
        this.exp=exp;
        this.key=key;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, IValue> sym = state.getSymTable();
        MyIStack<IStmt> stk = state.getExeStack();
        if (sym.exist(key)){
            IValue val = exp.eval(sym, state.getHeap());
            IType type = sym.find(key).getType();
            if(val.getType().equals(type)){
                sym.update(key,val);
            }
            else
            {
                throw ExpressionEvaluationException.type_mismatch("The type of variable "+key+" and " +
                        "type of the assigned expression"+val.toString()+"do not match");
            }
        }
        else
            throw DataStructureException.not_defined_key("Variable "+key+" is not initialized");
        return null;
    }

    @Override
    public String toString() {
        return key+" = "+exp.toString()+";";
    }

    @Override
    public IStmt deepCopy() {
        return new AssignStmt(key, exp.deepcopy());
    }
}
