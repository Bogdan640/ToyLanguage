package Model.DataStructures.Classes;

import Exceptions.DataStructureException;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;

import java.util.HashMap;

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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (K key : dict.keySet()) {
            result.append(key).append(" -> ").append(dict.get(key)).append("\n");
        }
        return result.toString();
    }
}
