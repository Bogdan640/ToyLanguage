package Model.Types.Interfaces;

import Model.Values.Interfaces.IValue;

public interface IType {
    boolean equals(Object another);
    String toString();
    IValue getDefaultValue();
    IType deepCopy();

}
