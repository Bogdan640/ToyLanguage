package Model.Expressions;

import Exceptions.DataStructureException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIDictionary;
import Model.DataStructures.Interfaces.MyIHeap;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Types.Classes.RefType;
import Model.Values.Classes.RefValue;
import Model.Values.Interfaces.IValue;

public class HeapReadingExp implements IExp {
    private IExp exp;

    public HeapReadingExp(IExp exp) {
        this.exp = exp;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws MyException {
        IValue value = exp.eval(symTable, heap);
        if(!(value.getType() instanceof RefType))
            throw ExpressionEvaluationException.type_mismatch("The expression " + exp.toString() + " is not a reference");

        RefValue refValue = (RefValue) value;
        if(!(heap.contains(refValue.getAddress())))
            throw DataStructureException.not_defined_key("The address " + refValue.getAddress() + " is not allocated in the heap");

        IValue heapValue =heap.getValue(refValue.getAddress());
        return heapValue;

    }

    @Override
    public IExp deepcopy() {
        return new HeapReadingExp(exp.deepcopy());
    }

    @Override
    public String toString() {
        return "rH(" + exp.toString() + ")";
    }
}
