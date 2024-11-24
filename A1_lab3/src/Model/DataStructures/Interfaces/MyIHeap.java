package Model.DataStructures.Interfaces;

import Exceptions.MyException;
import Model.Values.Interfaces.IValue;

import java.util.Map;

public interface MyIHeap {

    int allocate(IValue value);
    boolean contains(Integer key);
    IValue getValue(Integer key) throws MyException;
    void insert(Integer key, IValue value);
    void setContent(Map<Integer, IValue> newContent);
    Map<Integer, IValue> getContent();
}
