package Model.DataStructures.Interfaces;

import Exceptions.MyException;

public interface MyIQueue<T> {
    void add(T value);
    T remove() throws MyException;
    T top() throws MyException;
    boolean isEmpty();
    String toString();

}
