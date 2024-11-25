package Model.DataStructures.Classes;

import Exceptions.DataStructureException;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIHeap;
import Model.Values.Interfaces.IValue;

import java.util.HashMap;
import java.util.Map;

public class MyHeap implements MyIHeap {
    private Map<Integer, IValue> heap;

    public MyHeap(){
        heap = new HashMap<>();
    }

    @Override
    public int allocate(IValue value) {
        int i=1;
        while(heap.containsKey(i))
            i++;
        heap.put(i, value);
        return i;
    }

    @Override
    public boolean contains(Integer key) {
        return heap.containsKey(key);
    }

    @Override
    public IValue getValue(Integer key) throws MyException {
        if(heap.containsKey(key))
            return heap.get(key);
        throw DataStructureException.not_defined_key("The key" + key.toString() + " is not defined in the heap");
    }

    @Override
    public void insert(Integer key, IValue value) {
        heap.put(key, value);
    }

    @Override
    public void setContent(Map<Integer, IValue> newContent) {
        this.heap =  newContent;
    }

    @Override
    public Map<Integer, IValue> getContent() {
        return this.heap;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(Integer key: heap.keySet()){
            result.append(key.toString()).append(" -> ").append(heap.get(key).toString()).append("\n");
        }
        return result.toString();
    }
}
