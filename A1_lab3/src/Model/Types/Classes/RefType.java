package Model.Types.Classes;

import Model.Types.Interfaces.IType;
import Model.Values.Classes.RefValue;
import Model.Values.Interfaces.IValue;

public class RefType implements IType {

    private IType inner;

    public RefType(IType inner){
        this.inner=inner;
    }

    public IType getInner(){
        return inner;
    }

    public boolean equals(Object another){
        if(another instanceof RefType)
            return inner.equals(((RefType) another).getInner());
        return false;
    }


    public String toString(){
        return "Ref(" + inner.toString() + ")";
    }

    @Override
    public IValue getDefaultValue() {
        return new RefValue(0, inner);
    }

    @Override
    public IType deepCopy() {
        return new RefType(inner.deepCopy());
    }
}
