package Exceptions;

public class RepoException extends MyException {
  public RepoException(String baseMessage, String additionalMessage) {
    super(baseMessage, additionalMessage);
  }

  public static RepoException is_empty(String additionalMessage) {
    return new RepoException("The repository is empty", additionalMessage);
  }
}
