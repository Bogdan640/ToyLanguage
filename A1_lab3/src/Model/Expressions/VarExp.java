package Model.Expressions;

import Exceptions.MyException;
import Model.DataStructures.Classes.MyDictionary;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.Values.Interfaces.IValue;
import com.sun.jdi.Value;

public class VarExp implements Exp{

    private String key;
    public VarExp(String key){
        this.key=key;
    }
    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable) throws MyException {
        if(symTable.exist(key)){
            return symTable.find(key);
        }
        throw MyException.not_defined("The variable "+ key+" is not instantiated.");
    }

    @Override
    public String toString(){
        return key;
    }

    @Override
    public Exp deepcopy() {
        return new VarExp(key);
    }
}
