package Repository;

import Exceptions.MyException;
import Model.PrgState;

import java.util.ArrayList;
import java.util.List;

public class Repo implements IRepo{

    private List<PrgState> prgSTS;
    private int current_index;

    public Repo(){
        this.prgSTS= new ArrayList<>();
        this.current_index=0;
    }

    @Override
    public PrgState getCrtPrg() throws MyException {
        if (this.prgSTS.isEmpty()){
            throw MyException.is_empty("REPO ERROR: There is no program state in the list");
        }
        return this.prgSTS.get(current_index);
    }

    @Override
    public void addPrgState(PrgState new_state) {
        this.prgSTS.add(new_state);
        this.current_index=prgSTS.size()-1;
    }

    @Override
    public void change_current_state(int index) {
        this.current_index=index;
    }

    @Override
    public List<PrgState> getRepo() {
        return this.prgSTS;
    }

    @Override
    public List<PrgState> getRepoCopy() {
        List<PrgState> copy = new ArrayList<>();
        for(PrgState prg : prgSTS){
            copy.add(prg);
        }
        return copy;
    }

}
