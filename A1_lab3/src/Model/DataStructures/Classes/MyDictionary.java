package Model.DataStructures.Classes;

import Exceptions.DataStructureException;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.Values.Interfaces.IValue;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {

    private HashMap<K,V> dict;
    public MyDictionary(){
        this.dict=new HashMap<>();
    }

    @Override
    public void add(K key, V value) throws MyException {
        if(exist(key)){
            throw DataStructureException.defined_key("Dictionary issue");
        }
        dict.put(key, value);
    }

    @Override
    public void remove(K key) throws MyException {
        if(!exist(key)){
            throw DataStructureException.not_defined_key("Dictionary issue, key:" + key.toString()+" not found");
        }
        dict.remove(key);
    }

    @Override
    public void update(K key, V NewValue) throws MyException {
        if(!exist(key)){
            throw DataStructureException.not_defined_key("Dictionary issue, key:" + key.toString()+" not found");
        }
        dict.put(key, NewValue);
    }

    @Override
    public boolean exist(K key) {
        return dict.containsKey(key);
    }

    @Override
    public V find(K key) throws MyException {
        if(!exist(key)){
            throw DataStructureException.not_defined_key("Dictionary issue, key:" + key.toString()+" not found");
        }
        return dict.get(key);
    }

    //get content
    public Map<K, V> getContent() {
        return this.dict;
    }

    @Override
    public MyIDictionary<K, V> clone() {
        MyIDictionary<K, V> clone = new MyDictionary<K, V>();
        for(K key: dict.keySet()){
            IValue value = (IValue) dict.get(key);
            try {
                clone.add(key, (V) value.deepcopy());
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        }
        return clone;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (K key : dict.keySet()) {
            result.append(key).append(" -> ").append(dict.get(key)).append("\n");
        }
        return result.toString();
    }

    public MyDictionary<K, V> deepCopy(){
        MyDictionary<K, V> copy = new MyDictionary<>();
        for(K key: dict.keySet()){
            copy.dict.put(key, dict.get(key));
        }
        return copy;
    }
}
