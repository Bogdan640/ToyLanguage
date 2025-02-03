package Model.DataStructures.Classes;

import Exceptions.DataStructureException;
import Model.DataStructures.Interfaces.MyILockTable;

import java.util.HashMap;
import java.util.Set;

public class MyLockTable implements MyILockTable {

    private HashMap<Integer, Integer> lockTable;
    private int freelocation = 0;

    public MyLockTable(){
        this.lockTable = new HashMap<>();
    }



    @Override
    public int getFreeValue() {
        synchronized (this){
            freelocation++;
            return freelocation;
        }
    }

    @Override
    public void add(int key, int value) throws DataStructureException {
        synchronized (this){
            if(!lockTable.containsKey(key))
                lockTable.put(key, value);
            else
                throw DataStructureException.defined_key("The key: "+key+" is already defined in the lock table");
        }

    }

    @Override
    public HashMap<Integer, Integer> getContent() {
        synchronized (this){
            return lockTable;
        }
    }

    @Override
    public boolean exist(int key) {
        synchronized (this){
            return lockTable.containsKey(key);
        }
    }

    @Override
    public int find(int key) throws DataStructureException {
        synchronized (this){
            if(!lockTable.containsKey(key))
                throw DataStructureException.not_defined_key("The key: "+key+" is not defined in the lock table");
            else
                return lockTable.get(key);
        }
    }

    @Override
    public void update(int key, int value) throws DataStructureException {
        synchronized (this){
            if(lockTable.containsKey(key))
                lockTable.replace(key, value);
            else
                throw DataStructureException.not_defined_key("The key: "+key+" is not defined in the lock table");
        }

    }

    @Override
    public void setContent(HashMap<Integer, Integer> newContent) {
        synchronized (this){
            lockTable = newContent;
        }
    }

    @Override
    public Set<Integer> keySet() {
        synchronized (this){
            return lockTable.keySet();
        }
    }

    @Override
    public String toString() {
        synchronized (this){
            return lockTable.toString();
        }
    }
}
