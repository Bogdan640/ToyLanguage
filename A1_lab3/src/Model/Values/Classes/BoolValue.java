package Model.Values.Classes;

import Model.Types.Classes.BoolType;
import Model.Types.Interfaces.IType;
import Model.Values.Interfaces.IValue;

public class BoolValue implements IValue {

    private boolean val;
    public BoolValue(boolean value){
        this.val=value;
    }

    public BoolValue(){
        this.val=false;
    }
    @Override
    public IType getType() {
        return new BoolType();
    }

    public boolean getValue(){
        return this.val;
    }

    @Override
    public String toString(){
        return Boolean.toString(val);
    }

    @Override
    public boolean equals(Object another){
        if(another instanceof BoolValue){
            BoolValue anothervalue = (BoolValue) another;
            return anothervalue.val == this.val;
        }
        return false;
    }

    @Override
    public IValue deepcopy() {
        return new BoolValue(this.val);
    }
}
