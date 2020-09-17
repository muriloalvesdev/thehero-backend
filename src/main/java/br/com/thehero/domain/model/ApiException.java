package br.com.thehero.domain.model;

import lombok.Data;

@Data
public class ApiException {
  private String message;
  private int status;

}
