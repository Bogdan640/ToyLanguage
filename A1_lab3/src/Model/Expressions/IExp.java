package Model.Expressions;

import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.DataStructures.Interfaces.MyIHeap;
import Model.Values.Interfaces.IValue;

public interface IExp {
    IValue eval(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws MyException;
    String toString();
    IExp deepcopy();
}
