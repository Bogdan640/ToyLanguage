package Model.Values.Classes;

import Model.Types.Classes.StringType;
import Model.Types.Interfaces.IType;
import Model.Values.Interfaces.IValue;

public class StringValue implements IValue {

    private String value;

    public StringValue(String value){
        this.value = value;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public IValue deepcopy() {
        return new StringValue(this.value);
    }

    @Override
    public boolean equals(Object another){
        if(another instanceof StringValue){
            StringValue anotherValue = (StringValue) another;
            if( anotherValue.value.equals(this.value))
                return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return this.value;
    }

    public String getValue(){
        return this.value;
    }
}
