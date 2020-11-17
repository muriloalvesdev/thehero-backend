package br.com.thehero.exception;

public class IncidentNotFoundException extends RuntimeException {

  public IncidentNotFoundException(String message) {
    super(message);
  }
}
