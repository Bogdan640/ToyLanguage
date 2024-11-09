package Model.Expressions;

import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.Values.Interfaces.IValue;


public class ValueExp implements IExp {

    private IValue value;

    public ValueExp(IValue v){
        this.value=v;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable) throws MyException {
        return this.value;
    }

    @Override
    public String toString(){
        return value.toString();
    }

    @Override
    public IExp deepcopy() {
        return new ValueExp(value.deepcopy());
    }
}
