package ua.edu.ukma.termpapers.exception;

public class HBasePersistenceException extends RuntimeException {

  public HBasePersistenceException(String message) {
    super(message);
  }

  public HBasePersistenceException(String message, Throwable cause) {
    super(message, cause);
  }
}
