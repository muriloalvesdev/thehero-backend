package br.com.thehero.exception;

public class TokenInvalidException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public TokenInvalidException(String msg) {
    super(msg);
  }
}
