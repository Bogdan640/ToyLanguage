package Model.Types.Classes;

import Model.Types.Interfaces.IType;
import Model.Values.Classes.BoolValue;
import Model.Values.Interfaces.IValue;

public class BoolType implements IType {

    @Override
    public boolean equals(Object another){
        return another instanceof BoolType;
    }
    @Override
    public String toString(){
        return "bool";
    }

    @Override
    public IValue getDefaultValue() {
        return new BoolValue(false);
    }

    @Override
    public IType deepCopy() {
        return new BoolType();
    }


}
