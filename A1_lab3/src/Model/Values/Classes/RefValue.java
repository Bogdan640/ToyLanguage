package Model.Values.Classes;

import Model.Types.Classes.RefType;
import Model.Types.Interfaces.IType;
import Model.Values.Interfaces.IValue;

public class RefValue implements IValue {

    int address;
    IType locationType;

    public RefValue(int address, IType locationType){
        this.address=address;
        this.locationType=locationType;
    }

    public int getAddress(){
        return address;
    }

    public IType getLocationType(){
        return locationType;
    }

    @Override
    public IType getType() {
        return new RefType(locationType);
    }

    @Override
    public IValue deepcopy() {
        return new RefValue(this. address, this.locationType.deepCopy());
    }

    @Override
    public String toString(){
        return "(" + Integer.toString(address) + ", " + locationType.toString() + ")";
    }

    @Override
    public boolean equals(Object another){
        if (another instanceof RefValue)
            return address == ((RefValue) another).getAddress() && locationType.equals(((RefValue) another).getLocationType());
        return false;
    }


}
