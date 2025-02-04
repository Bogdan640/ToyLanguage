package Model.Statements;

import Exceptions.MyException;
import Exceptions.StatementExecutionException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.PrgState;
import Model.Types.Interfaces.IType;


    public class FuncReturnStmt implements IStmt {
        @Override
        public PrgState execute(PrgState state) throws MyException {
            try {
                state.getSymTableStack().pop();
            } catch (Exception e) {
                throw StatementExecutionException.FunctionError("Function return error");
            }

            return null;
        }

        @Override
        public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
            return typeEnv;
        }

        @Override
        public IStmt deepCopy() {
            return new FuncReturnStmt();
        }

        @Override
        public String toString() {
            return "return";
        }
    }

