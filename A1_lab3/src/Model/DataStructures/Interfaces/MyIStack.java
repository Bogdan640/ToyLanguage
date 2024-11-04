package Model.DataStructures.Interfaces;
import Exceptions.MyException;

public interface MyIStack<T> {
    void push(T item);
    T pop() throws MyException;
    T top();
    boolean isEmpty();
    String toString();
}
