package Model.DataStructures.Classes;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIStack;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {

    private Stack<T> stack;
    public MyStack(){
        this.stack=new Stack<>();
    }

    @Override
    public void push(T item) {
        stack.push(item);
    }

    @Override
    public T pop() throws MyException {
        if(stack.isEmpty()){
            throw MyException.is_empty("Stack is empty.");
        }
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public T top() {
        return stack.peek();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = stack.size() - 1; i >= 0; i--) {
            str.append(stack.get(i));
            str.append("\n");
        }
        return str.toString();
    }
}

