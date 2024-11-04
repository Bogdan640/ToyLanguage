package Exceptions;

public class StatementExecutionException extends MyException {
    public StatementExecutionException(String baseMessage, String additionalMessage) {
        super(baseMessage, additionalMessage);
    }

    public static StatementExecutionException executionStackEmpty() {
        return new StatementExecutionException("Attempting to execute with an empty execution stack", null);
    }
}
