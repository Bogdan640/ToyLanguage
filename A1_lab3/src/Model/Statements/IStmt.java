package Model.Statements;

import Exceptions.MyException;
import Model.DataStructures.Classes.MyDictionary;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.PrgState;
import Model.Types.Interfaces.IType;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    String toString();
    IStmt deepCopy();
    MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException;
}
