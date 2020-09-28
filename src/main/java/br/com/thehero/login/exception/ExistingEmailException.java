package br.com.thehero.login.exception;

public class ExistingEmailException extends RuntimeException {

  public ExistingEmailException(String msg) {
    super(msg);
  }

}
