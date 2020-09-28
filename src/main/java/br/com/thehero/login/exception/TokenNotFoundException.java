package br.com.thehero.login.exception;

public class TokenNotFoundException extends RuntimeException {

  public TokenNotFoundException(String msg) {
    super(msg);
  }

}
