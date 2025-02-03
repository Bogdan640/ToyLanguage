package Model.Expressions;

import Exceptions.DataStructureException;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.DataStructures.Interfaces.MyIHeap;
import Model.Types.Interfaces.IType;
import Model.Values.Interfaces.IValue;

public class VarExp implements IExp {

    private String key;
    public VarExp(String key){
        this.key=key;
    }
    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws MyException {
        if(symTable.exist(key)){
            return symTable.find(key);
        }
        throw DataStructureException.not_defined_key("The key: "+key.toString()+" does not exist in the dictionary");
    }

    @Override
    public String toString(){
        return key;
    }

    @Override
    public IExp deepcopy() {
        return new VarExp(key);
    }

    @Override
    public IType typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return typeEnv.find(key);
    }
}
