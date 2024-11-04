package Model.DataStructures.Classes;

import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIQueue;

import java.util.LinkedList;
import java.util.Queue;

public class MyQueue<T> implements MyIQueue<T> {
    private Queue<T> queue;
    public MyQueue(){
        this.queue=new LinkedList<>(){
        };
    }


    @Override
    public void add(T value) {
        queue.add(value);
    }

    @Override
    public T remove() throws MyException {
        if(isEmpty()){
            throw MyException.is_empty("The queue is empty.");
        }
        return queue.remove();
    }

    @Override
    public T top() throws MyException {
        return queue.peek();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (T item : queue) {
            result.append(item).append(" ");
        }
        return result.toString();

    }
}
