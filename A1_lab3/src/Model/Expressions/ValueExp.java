package Model.Expressions;

import Exceptions.MyException;
import Model.DataStructures.Classes.MyDictionary;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.Values.Interfaces.IValue;


public class ValueExp implements Exp{

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
    public Exp deepcopy() {
        return new ValueExp(value.deepcopy());
    }
}
