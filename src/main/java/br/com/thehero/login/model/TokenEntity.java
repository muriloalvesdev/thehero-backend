package br.com.thehero.login.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "token")
public class TokenEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String token;
  private UUID idUser;
  private LocalDateTime expiracaoToken;

  public TokenEntity(String token, UUID idUser, LocalDateTime expiracaoToken) {
    this.token = token;
    this.idUser = idUser;
    this.expiracaoToken = expiracaoToken;
  }

  @SuppressWarnings("unused")
  private TokenEntity() {}

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public UUID getIdUser() {
    return idUser;
  }

  public void setIdUser(UUID idUser) {
    this.idUser = idUser;
  }

  public LocalDateTime getExpiracaoToken() {
    return expiracaoToken;
  }

  public void setExpiracaoToken(LocalDateTime expiracaoToken) {
    this.expiracaoToken = expiracaoToken;
  }

  public Long getId() {
    return id;
  }

  @Override
  public String toString() {
    return "TokenEntity [id=" + id + ", token=" + token + ", expiracaoToken=" + expiracaoToken
        + "]";
  }

}
