package br.com.thehero.logout.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Token {

  @JsonProperty("token")
  private String token;

}
