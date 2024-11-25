package Model.DataStructures.Interfaces;

import Exceptions.MyException;

import java.util.Map;

public interface MyIDictionary<K, V> {
    void add(K key, V value) throws MyException;
    void remove(K key) throws MyException;
    void update(K key, V NewValue) throws MyException;
    boolean exist(K key);
    Map<K, V> getContent();

    /**
     *
     * @param key
     * @return the value associated with the key
     * @throws MyException if the key does not exist
     */
    V find(K key) throws  MyException;
    String toString();
}
