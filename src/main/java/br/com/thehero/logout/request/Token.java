package br.com.thehero.logout.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {

  @JsonProperty("token")
  private String token;

  public Token() {}

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
