package Repository;

import Exceptions.MyException;
import Exceptions.RepoException;
import Model.PrgState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Repo implements IRepo{

    private List<PrgState> prgSTS;
    private int current_index;
    private String filename;

    public Repo(){
        this.prgSTS= new ArrayList<>();
        this.current_index=0;
    }

    public Repo(String filename) throws RepoException {
        this.prgSTS= new ArrayList<>();
        this.current_index=0;
        this.filename=filename;
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("");
            writer.close();
        } catch (IOException e) {
            throw RepoException.file_not_created("REPO ERROR: The file was not created");
        }
    }

    @Override
    public PrgState getCrtPrg() throws MyException {
        if (this.prgSTS.isEmpty()){
            throw RepoException.is_empty("REPO ERROR: There is no program state in the list");
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
    public void logPrgStateExec() throws MyException, IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
        logFile.println("ExeStack:");
        logFile.println(this.getCrtPrg().getExeStack().toString());
        logFile.print("\n\n");
        logFile.println("SymTable:");
        logFile.println(this.getCrtPrg().getSymTable().toString());
        logFile.print("\n\n");
        logFile.println("Out:");
        logFile.println(this.getCrtPrg().getOut().toString());
        logFile.close();
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
        //FIXME
        //TODO
    }

}
