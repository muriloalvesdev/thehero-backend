package br.com.thehero.login.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessToken {

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("ongId")
  private String id;

  @JsonProperty("ongName")
  private String name;

  public AccessToken(String accessToken, String id, String name) {
    this.accessToken = accessToken;
    this.id = id;
    this.name = name;
  }

  @SuppressWarnings("unused")
  private AccessToken() {}

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "AccessToken [accessToken=" + accessToken + ", id=" + id + ", name=" + name + "]";
  }
}
