package Model.DataStructures.Interfaces;
import Exceptions.MyException;

import java.util.Stack;

public interface MyIStack<T> {
    void push(T item);
    T pop() throws MyException;
    T top()  throws MyException;
    boolean isEmpty();
    String toString();
    Stack<T> getStack();
}
