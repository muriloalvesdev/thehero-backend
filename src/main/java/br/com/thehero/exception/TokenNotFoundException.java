package br.com.thehero.exception;

public class TokenNotFoundException extends RuntimeException {

  public TokenNotFoundException(String msg) {
    super(msg);
  }
}
