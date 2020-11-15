package br.com.thehero.login.model;

public class Password {

  private String password;

  public Password(String password) {
    this.password = password;
  }

  @SuppressWarnings("unused")
  private Password() {}

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "Password [password=" + password + "]";
  }
}
