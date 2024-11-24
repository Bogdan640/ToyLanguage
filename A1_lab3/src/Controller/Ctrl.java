package Controller;

import Exceptions.ControllerException;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIStack;
import Model.PrgState;
import Model.Statements.IStmt;
import Repository.IRepo;

import java.io.IOException;

public class Ctrl implements ICtrl{

    private IRepo repo;
    private boolean displayFlag;

    public Ctrl(IRepo repo, boolean displayFlag){
        this.repo=repo;
        this.displayFlag=displayFlag;
    }

    public Ctrl (IRepo repo){
        this.repo=repo;
        this.displayFlag=false;
    }

    public void set_displayFlag(boolean flag){
        this.displayFlag=flag;
    }

    @Override
    public PrgState executeOneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        if (stk.isEmpty())
            throw ControllerException.is_empty("CTRL ERROR: Execution stack is empty");
        IStmt crtStmt = stk.pop();
        crtStmt.execute(state);
//        if(displayFlag)
//            System.out.println(repo.getCrtPrg());
        return state;
    }

    @Override
    public void executeAll() throws MyException, IOException {
        PrgState prg =  repo.getCrtPrg();
        repo.logPrgStateExec();
        while(!prg.getExeStack().isEmpty()){
            executeOneStep(prg);
            repo.logPrgStateExec();
            if(displayFlag)
                System.out.println(prg);

        }

    }

    public void change_current_state(int index){
        repo.change_current_state(index);
    }





}
