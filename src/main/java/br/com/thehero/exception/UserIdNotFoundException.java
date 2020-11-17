package br.com.thehero.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserIdNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public UserIdNotFoundException(String msg) {
    super(msg);
  }
}
