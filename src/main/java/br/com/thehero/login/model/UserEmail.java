package br.com.thehero.login.model;

public class UserEmail {

  private String email;

  public UserEmail(String email) {
    this.email = email;
  }

  @SuppressWarnings("unused")
  private UserEmail() {}

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "UserEmail [email=" + email + "]";
  }
}
