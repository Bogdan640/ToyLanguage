package Model.Values.Interfaces;

import Model.Types.Interfaces.IType;

public interface IValue {
    IType getType();
    String toString();
    boolean equals(Object another);
    IValue deepcopy();
}
