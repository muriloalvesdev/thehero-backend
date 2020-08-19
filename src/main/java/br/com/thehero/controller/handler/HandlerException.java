package br.com.thehero.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.com.thehero.domain.model.ApiException;
import br.com.thehero.login.exception.EmailNotFoundException;
import br.com.thehero.login.exception.ExistingEmailException;
import br.com.thehero.login.exception.IllegalRoleException;
import br.com.thehero.login.exception.TokenNotFoundException;
import javassist.NotFoundException;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ApiException> handleNotFoundException(NotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(createResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
  }


  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiException> handleInternalServerError(IllegalArgumentException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(createResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
  }

  @ExceptionHandler(TokenNotFoundException.class)
  public ResponseEntity<ApiException> handlerTokenNotFoundException(TokenNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(createResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.value()));
  }

  @ExceptionHandler(ExistingEmailException.class)
  public ResponseEntity<ApiException> handlerExistingEmailException(ExistingEmailException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(createResponse(ex.getMessage(), HttpStatus.CONFLICT.value()));
  }

  @ExceptionHandler(IllegalRoleException.class)
  public ResponseEntity<ApiException> handlerIllegalRoleException(IllegalRoleException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(createResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
  }

  @ExceptionHandler(EmailNotFoundException.class)
  public ResponseEntity<ApiException> handlerEmailNotFoundException(EmailNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(createResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
  }

  private ApiException createResponse(String message, int httpValue) {
    return new ApiException(message, httpValue);
  }

}
