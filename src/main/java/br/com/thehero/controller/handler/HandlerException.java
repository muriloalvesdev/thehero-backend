package br.com.thehero.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.com.thehero.domain.model.ApiException;
import br.com.thehero.exception.EmailNotFoundException;
import br.com.thehero.exception.ExistingEmailException;
import br.com.thehero.exception.IllegalRoleException;
import br.com.thehero.exception.IncidentNotFoundException;
import br.com.thehero.exception.TokenNotFoundException;
import javassist.NotFoundException;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ApiException> handleNotFoundException(NotFoundException notFoundException) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(createResponse(notFoundException.getMessage(), HttpStatus.NOT_FOUND.value()));
  }

  @ExceptionHandler(IncidentNotFoundException.class)
  public ResponseEntity<ApiException> handleIncidentNotFoundException(
      IncidentNotFoundException incidentNotFoundException) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(createResponse(incidentNotFoundException.getMessage(), HttpStatus.BAD_REQUEST.value()));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiException> handleInternalServerError(IllegalArgumentException illegalArgumentException) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(createResponse(illegalArgumentException.getMessage(), HttpStatus.BAD_REQUEST.value()));
  }

  @ExceptionHandler(TokenNotFoundException.class)
  public ResponseEntity<ApiException> handlerTokenNotFoundException(TokenNotFoundException tokenNotFoundException) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(createResponse(tokenNotFoundException.getMessage(), HttpStatus.UNAUTHORIZED.value()));
  }

  @ExceptionHandler(ExistingEmailException.class)
  public ResponseEntity<ApiException> handlerExistingEmailException(ExistingEmailException existingEmailException) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(createResponse(existingEmailException.getMessage(), HttpStatus.CONFLICT.value()));
  }

  @ExceptionHandler(IllegalRoleException.class)
  public ResponseEntity<ApiException> handlerIllegalRoleException(IllegalRoleException illegalRoleException) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(createResponse(illegalRoleException.getMessage(), HttpStatus.BAD_REQUEST.value()));
  }

  @ExceptionHandler(EmailNotFoundException.class)
  public ResponseEntity<ApiException> handlerEmailNotFoundException(EmailNotFoundException emailNotFoundException) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(createResponse(emailNotFoundException.getMessage(), HttpStatus.BAD_REQUEST.value()));
  }

  private ApiException createResponse(String message, int httpValue) {
    return new ApiException(message, httpValue);
  }
}
