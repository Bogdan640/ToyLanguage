package Model.Values.Classes;

import Model.Types.Interfaces.IType;
import Model.Types.Classes.IntType;
import Model.Values.Interfaces.IValue;


public class IntValue implements IValue {

    private int val;

    public IntValue(int val){
        this.val=val;
    }
    public IntValue(){
        this.val=0;
    }

    @Override
    public IType getType(){
        return new IntType();
    }

    public int getValue(){
        return this.val;
    }

    @Override
    public String toString(){
        return Integer.toString(val);
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof IntValue){
            IntValue anotherValue = (IntValue) another;
            return anotherValue.val == this.val;
        }
        return false;
    }

    @Override
    public IValue deepcopy() {
        return new IntValue(this.val);
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(this.val);
    }
}
