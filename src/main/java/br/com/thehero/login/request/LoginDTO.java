package br.com.thehero.login.request;

public class LoginDTO {

  private String email;
  private String password;

  public LoginDTO(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public LoginDTO() {}

  public String getEmail() {
    return email;
  }

  public void setUsername(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "LoginData [email=" + email + ", password=" + password + "]";
  }
}
