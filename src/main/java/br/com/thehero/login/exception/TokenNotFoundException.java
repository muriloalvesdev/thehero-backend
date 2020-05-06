package br.com.thehero.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TokenNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public TokenNotFoundException(String msg) {
    super(msg);
  }

}
