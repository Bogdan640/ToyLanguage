package Model.Expressions;

import Exceptions.MyException;
import Model.DataStructures.Classes.MyDictionary;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.Values.Interfaces.IValue;
import com.sun.jdi.Value;

public interface Exp {
    IValue eval(MyIDictionary<String, IValue> symTable) throws MyException;
    String toString();
    Exp deepcopy();
}
