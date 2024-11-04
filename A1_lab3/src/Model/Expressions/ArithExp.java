package Model.Expressions;

import Exceptions.MyException;
import Model.DataStructures.Classes.MyDictionary;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.Types.Classes.BoolType;
import Model.Types.Classes.IntType;
import Model.Values.Classes.BoolValue;
import Model.Values.Classes.IntValue;
import Model.Values.Interfaces.IValue;
import com.sun.jdi.Value;

public class ArithExp implements Exp{

    private Exp e1, e2;
    private String op;

    public ArithExp(Exp e1, String op, Exp e2){
        this.e1=e1;
        this.e2=e2;
        this.op=op;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable) throws MyException {
        IValue v1 = e1.eval(symTable);
        IValue v2 = e2.eval(symTable);

        if(v1.getType().equals(new IntType())){
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1 = i1.getValue();
                int n2 = i2.getValue();

                return switch (op) {
                    case "+" -> new IntValue(n1 + n2);
                    case "-" -> new IntValue((n1 - n2));
                    case "*" -> new IntValue(n1 * n2);
                    case "/" -> new IntValue(n1 / n2);
                    case "==" -> new BoolValue(n1==n2);
                    default -> throw MyException.invalid_input("Invalid operator type");
                };
            }
            else
                throw MyException.invalid_input("The second operand doesn't match the type of the first");
        }
        else
            throw MyException.invalid_input("You tried to use an invalid data type");
    }

    @Override
    public String toString(){
        return e1.toString() + op + e2.toString();
    }

    @Override
    public Exp deepcopy() {
        return new ArithExp(e1.deepcopy(),op, e2.deepcopy());
    }


}
