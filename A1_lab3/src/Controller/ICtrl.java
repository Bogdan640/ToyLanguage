package Controller;

import Exceptions.MyException;

import java.io.IOException;

public interface ICtrl {
    //  PrgState executeOneStep(PrgState state) throws MyException;
    void allStep() throws MyException, IOException, InterruptedException;
    void set_displayFlag(boolean flag);
    void change_current_state(int index);
}
