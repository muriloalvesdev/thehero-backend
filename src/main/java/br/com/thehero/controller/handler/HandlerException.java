package br.com.thehero.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.com.thehero.domain.model.ApiException;
import br.com.thehero.login.exception.ArgumentInvalidException;
import br.com.thehero.login.exception.EmailNotFoundException;
import br.com.thehero.login.exception.ExistingEmailException;
import br.com.thehero.login.exception.IllegalRoleException;
import br.com.thehero.login.exception.Sha512Exception;
import javassist.NotFoundException;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public @ResponseBody ResponseEntity<ApiException> handleNotFoundException(NotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(createResponseException(ex, HttpStatus.NOT_FOUND));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public @ResponseBody ResponseEntity<ApiException> handleInternalServerError(
      IllegalArgumentException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(createResponseException(ex, HttpStatus.BAD_REQUEST));
  }

  @ExceptionHandler(ArgumentInvalidException.class)
  public @ResponseBody ResponseEntity<ApiException> handleArgumentInvalidException(
      ArgumentInvalidException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(createResponseException(ex, HttpStatus.BAD_REQUEST));
  }

  @ExceptionHandler(EmailNotFoundException.class)
  public @ResponseBody ResponseEntity<ApiException> handleEmailNotFoundException(
      EmailNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(createResponseException(ex, HttpStatus.NOT_FOUND));
  }

  @ExceptionHandler(ExistingEmailException.class)
  public @ResponseBody ResponseEntity<ApiException> handleExistingEmailException(
      ExistingEmailException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(createResponseException(ex, HttpStatus.CONFLICT));
  }

  @ExceptionHandler(IllegalRoleException.class)
  public @ResponseBody ResponseEntity<ApiException> handleIllegalRoleException(
      IllegalRoleException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(createResponseException(ex, HttpStatus.BAD_REQUEST));
  }

  @ExceptionHandler(Sha512Exception.class)
  public @ResponseBody ResponseEntity<ApiException> handleSha512Exception(Sha512Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(createResponseException(ex, HttpStatus.INTERNAL_SERVER_ERROR));
  }

  @ExceptionHandler(br.com.thehero.login.exception.IllegalArgumentException.class)
  public @ResponseBody ResponseEntity<ApiException> handleIllegalArgumentException(
      br.com.thehero.login.exception.IllegalArgumentException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(createResponseException(ex, HttpStatus.BAD_REQUEST));
  }

  private ApiException createResponseException(Exception ex, HttpStatus httpStatus) {
    return new ApiException(ex.getMessage(), httpStatus.value());
  }
}
