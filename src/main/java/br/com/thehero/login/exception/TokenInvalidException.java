package br.com.thehero.login.exception;

public class TokenInvalidException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public TokenInvalidException(String message) {
    super(message);
  }
}
