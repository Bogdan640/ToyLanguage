package Model.Expressions;

import Exceptions.ExpressionEvaluationException;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.Types.Classes.IntType;
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
    public IValue eval(MyIDictionary<String, IValue> symTable) throws MyException {
        IValue v1 = e1.eval(symTable);
        IValue v2 = e2.eval(symTable);

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
    public String toString(){
        return e1.toString() + op + e2.toString();
    }
}
