package Model.Statements;

import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.PrgState;
import Model.Types.Interfaces.IType;
import Model.Values.Interfaces.IValue;

public class VarDeclStmt implements IStmt{

    String name;
    IType type;

    public VarDeclStmt(String name, IType type){
        this.name = name;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, IValue> sym = state.getSymTable();
        if(sym.exist(name))
            throw MyException.exist("Variable " +name+" is already defined in this scope");
        sym.add(name, type.getDefaultValue());
        return state;
    }

    @Override
    public String toString() {
        return type.toString()+" "+ name;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(new String(name), type);
    }
}
