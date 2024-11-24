package Model.Types.Classes;

import Model.Types.Interfaces.IType;
import Model.Values.Classes.StringValue;
import Model.Values.Interfaces.IValue;

public class StringType implements IType {

    @Override
    public boolean equals(Object another){
        return another instanceof StringType;
    }

    @Override
    public String toString(){
        return "string";
    }

    @Override
    public IValue getDefaultValue() {
        return new StringValue("");
    }
}
