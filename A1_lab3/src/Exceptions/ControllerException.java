package Exceptions;

public class ControllerException extends MyException {
  public ControllerException(String baseMessage, String additionalMessage) {
    super(baseMessage, additionalMessage);
  }

  public static RepoException is_empty(String additionalMessage) {
    return new RepoException("The repository is empty", additionalMessage);
  }
}
