package Model.Expressions;

import Exceptions.ExpressionEvaluationException;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.DataStructures.Interfaces.MyIHeap;
import Model.Types.Classes.IntType;
import Model.Types.Interfaces.IType;
import Model.Values.Classes.BoolValue;
import Model.Values.Classes.IntValue;
import Model.Values.Interfaces.IValue;

public class RelationalExp implements IExp{

    private IExp e1, e2;
    private String op;

    public RelationalExp(IExp e1, String op, IExp e2){
        this.e1=e1;
        this.e2=e2;
        this.op=op;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws MyException {
        IValue v1 = e1.eval(symTable, heap);
        IValue v2 = e2.eval(symTable, heap);

        if(!v1.getType().equals(new IntType()))
            throw ExpressionEvaluationException.type_mismatch("The first operand should be an integer");
        if(!v2.getType().equals(new IntType()))
            throw ExpressionEvaluationException.type_mismatch("The second operand should be an integer");

        int n1 = ((IntValue)v1).getValue();
        int n2 = ((IntValue)v2).getValue();

        return switch (op) {
            case "<" -> new BoolValue(n1<n2);
            case "<=" -> new BoolValue(n1<=n2);
            case "==" -> new BoolValue(n1==n2);
            case "!=" -> new BoolValue(n1!=n2);
            case ">" -> new BoolValue(n1>n2);
            case ">=" -> new BoolValue(n1>=n2);
            default -> throw ExpressionEvaluationException.invalid_input("The operator is not valid");
        };
    }

    @Override
    public IExp deepcopy() {
        return new RelationalExp(e1.deepcopy(),op,e2.deepcopy());
    }

    @Override
    public IType typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType t1, t2;
        t1 = e1.typecheck(typeEnv);
        t2 = e2.typecheck(typeEnv);
        if (t1.equals(new IntType())) {
            if (t2.equals(new IntType())) {
                return new BoolValue(true).getType();
            } else {
                throw ExpressionEvaluationException.type_mismatch("The second operand should be an integer");
            }
        } else {
            throw ExpressionEvaluationException.type_mismatch("The first operand should be an integer");
        }
    }

    @Override
    public String toString(){
        return e1.toString() + op + e2.toString();
    }
}
