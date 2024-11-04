package Exceptions;

public class ExpressionEvaluationException extends MyException {
    public ExpressionEvaluationException(String baseMessage, String additionalMessage) {
        super(baseMessage, additionalMessage);
    }

    public static ExpressionEvaluationException divisionByZero(String additionalMessage) {
        return new ExpressionEvaluationException("Division by zero is not allowed", additionalMessage);
    }

    public static ExpressionEvaluationException variableNotDefined(String additionalMessage) {
        return new ExpressionEvaluationException("The variable is not defined in the symbol table", additionalMessage);
    }

    public static ExpressionEvaluationException invalid_input(String additionalMessage) {
        return new ExpressionEvaluationException("The input is not valid", additionalMessage);
    }

    public static ExpressionEvaluationException type_mismatch(String additionalMessage) {
        return new ExpressionEvaluationException("Type missmatch: ", additionalMessage);
    }
}

