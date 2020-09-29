package br.com.thehero.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ArgumentInvalidException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ArgumentInvalidException(String message) {
    super(message);
  }

}
