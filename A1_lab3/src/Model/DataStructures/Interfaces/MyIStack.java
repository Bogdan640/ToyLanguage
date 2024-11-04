package Model.DataStructures.Interfaces;
import Exceptions.MyException;

public interface MyIStack<T> {
    void push(T item);
    T pop() throws MyException;
    T top()  throws MyException;
    boolean isEmpty();
    String toString();
}
