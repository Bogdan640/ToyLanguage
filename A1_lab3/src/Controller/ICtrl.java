package Controller;

import Exceptions.MyException;
import Model.PrgState;

public interface ICtrl {
    PrgState executeOneStep(PrgState state) throws MyException;
    void executeAll() throws MyException;
    void set_displayFlag(boolean flag);
    void change_current_state(int index);
}
