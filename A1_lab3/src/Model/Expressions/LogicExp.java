package Model.Expressions;

import Exceptions.ExpressionEvaluationException;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.DataStructures.Interfaces.MyIHeap;
import Model.Types.Classes.BoolType;
import Model.Types.Interfaces.IType;
import Model.Values.Classes.BoolValue;
import Model.Values.Interfaces.IValue;

public class LogicExp implements IExp{

    private IExp e1;
    private IExp e2;
    private int op;

    public LogicExp(IExp e1, IExp e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws MyException {
        IValue v1,v2;
        v1 = e1.eval(symTable, heap);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(symTable, heap);
            if (v2.getType().equals(new BoolType())) {
                BoolValue i1 = (BoolValue)v1;
                BoolValue i2 = (BoolValue)v2;
                boolean n1,n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (op==1) return new BoolValue(n1&&n2);
                if (op==2) return new BoolValue(n1||n2);
            } else throw ExpressionEvaluationException.invalid_input("Second operand is not a boolean");
        } else throw ExpressionEvaluationException.invalid_input("First operand is not a boolean");
        return null;
    }

    @Override
    public IExp deepcopy() {
        return new LogicExp(e1.deepcopy(), e2.deepcopy(), op);
    }

    @Override
    public IType typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType t1, t2;
        t1 = e1.typecheck(typeEnv);
        t2 = e2.typecheck(typeEnv);
        if (t1.equals(new BoolType())) {
            if (t2.equals(new BoolType())) {
                return new BoolType();
            } else throw ExpressionEvaluationException.type_mismatch("Second operand is not a boolean");
        } else throw ExpressionEvaluationException.type_mismatch("First operand is not a boolean");
    }

    @Override
    public String toString() {
        String opStr = "";
        if (op == 1) {
            opStr = "&&";
        } else if (op == 2) {
            opStr = "||";
        }
        return e1.toString() + " " + opStr + " " + e2.toString();
    }

}
