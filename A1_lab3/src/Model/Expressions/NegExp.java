package Model.Expressions;

import Exceptions.ExpressionEvaluationException;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.DataStructures.Interfaces.MyIHeap;
import Model.Types.Classes.BoolType;
import Model.Types.Interfaces.IType;
import Model.Values.Classes.BoolValue;
import Model.Values.Interfaces.IValue;

public class NegExp implements IExp{


    private IExp exp;

    public NegExp(IExp exp){
        this.exp = exp;
    }
    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws MyException {
        IValue v1;
        v1 = exp.eval(symTable, heap);
        if(v1.getType().equals(new BoolType())){
            BoolValue b1 = (BoolValue) v1;
            boolean b2 = b1.getValue();
            return new BoolValue(!b2);
        }
        else
            throw ExpressionEvaluationException.type_mismatch("The expression must return a boolean");
    }

    @Override
    public IExp deepcopy() {
        return new NegExp(exp.deepcopy());
    }

    @Override
    public IType typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType typeexp = exp.typecheck(typeEnv);
        if(typeexp.equals(new BoolType()))
            return new BoolType();
        else
            throw ExpressionEvaluationException.type_mismatch("The expression must return a boolean");
    }

    @Override
    public String toString(){
        return "!(" + exp.toString() + ")";
    }
}
