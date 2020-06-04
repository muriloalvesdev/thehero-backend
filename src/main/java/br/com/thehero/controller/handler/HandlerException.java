package br.com.thehero.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.com.thehero.domain.model.ApiException;
import javassist.NotFoundException;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public @ResponseBody ResponseEntity<ApiException> handleNotFoundException(NotFoundException ex) {
    ApiException exceptionError = new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionError);
  }


  @ExceptionHandler(IllegalArgumentException.class)
  public @ResponseBody ResponseEntity<ApiException> handleInternalServerError(
      IllegalArgumentException ex) {
    ApiException exceptionError = new ApiException(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionError);
  }
}
