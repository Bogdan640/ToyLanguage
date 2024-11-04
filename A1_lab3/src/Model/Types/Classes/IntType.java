package Model.Types.Classes;

import Model.Types.Interfaces.IType;
import Model.Values.Classes.IntValue;
import Model.Values.Interfaces.IValue;

public class IntType implements IType {
    private int default_value;


    @Override
    public boolean equals(Object another){
        return another instanceof IntType;
    }

    @Override
    public String toString(){
        return "int";
    }

    @Override
    public IValue getDefaultValue() {
        return new IntValue(0);
    }

}

