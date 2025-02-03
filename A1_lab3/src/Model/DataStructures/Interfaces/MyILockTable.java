package Model.DataStructures.Interfaces;

import Exceptions.DataStructureException;

import java.util.HashMap;
import java.util.Set;

public interface MyILockTable {

    int getFreeValue();
    void add(int key, int value) throws DataStructureException;
    HashMap<Integer, Integer> getContent();
    boolean exist(int key);
    int find(int key) throws DataStructureException;
    void update(int key, int value) throws DataStructureException;
    void setContent(HashMap<Integer, Integer> newContent);
    Set<Integer> keySet();
}
