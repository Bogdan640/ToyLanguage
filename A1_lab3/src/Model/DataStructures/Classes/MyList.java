package Model.DataStructures.Classes;

import Model.DataStructures.Interfaces.MyIList;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    private List<T> list;

    public MyList() {
        this.list = new ArrayList<>();
    }

    public void add(T e) {
        this.list.add(e);
    }

    public List<T> getAll() {
        return this.list;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T elem : this.list) {
            s.append(elem).append(" ");
        }
        return s.toString();
    }
}