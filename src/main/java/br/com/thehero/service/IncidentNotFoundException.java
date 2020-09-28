package br.com.thehero.service;

public class IncidentNotFoundException extends RuntimeException {

  public IncidentNotFoundException(String message) {
    super(message);
  }
}
