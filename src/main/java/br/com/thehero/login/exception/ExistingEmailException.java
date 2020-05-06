package br.com.thehero.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ExistingEmailException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ExistingEmailException(String msg) {
    super(msg);
  }

}
