package br.com.thehero.exception;

public class EmailNotFoundException extends RuntimeException {

  public EmailNotFoundException(String msg) {
    super(msg);
  }
}
