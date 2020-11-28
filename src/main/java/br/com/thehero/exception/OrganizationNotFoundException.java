package br.com.thehero.exception;

public class OrganizationNotFoundException extends RuntimeException {
  public OrganizationNotFoundException(String message) {
    super(message);
  }
}
