package Model.DataStructures.Interfaces;

import Exceptions.DataStructureException;

import java.util.Map;
import java.util.Set;

public interface MyIProc <A, C>{

    C getContent(A address) throws DataStructureException;
    Set<A> getAddresses();
    void insert(A address, C content);
    void update(A address, C content);
    void remove(A address) throws DataStructureException;
    boolean contains(A address);
    int getNextFreeAddr();
    void setContent(Map<A, C> newHeap);
    Map<A, C> getContent();

}
