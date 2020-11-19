package br.com.thehero.exception;

public class ExistingEmailException extends RuntimeException {

  public ExistingEmailException(String message) {
    super(message);
  }
}
