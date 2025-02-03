package Exceptions;

public class StatementExecutionException extends MyException {
    public StatementExecutionException(String baseMessage, String additionalMessage) {
        super(baseMessage, additionalMessage);
    }

    public static StatementExecutionException executionStackEmpty() {
        return new StatementExecutionException("Attempting to execute with an empty execution stack", null);
    }

    public static StatementExecutionException FileError(String additionalMessage) {
        return new StatementExecutionException("File error", additionalMessage);
    }
    public static StatementExecutionException TypeMissmatch(String additionalMessage) {
        return new StatementExecutionException("Type missmatch", additionalMessage);
    }
}
