package br.com.thehero.login.model;

import java.util.Date;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import br.com.thehero.login.util.Utils;

@Entity
@Table(
    name = "users",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {

  public static final String ERROR_INVALID_PASS = "Password can not be null or empty";
  public static final String ERROR_INVALID_NAME = "Name can not be null or empty";
  public static final String ERROR_INVALID_LAST_NAME = "Last name can not be null or empty";
  public static final String ERROR_INVALID_EMAIL = "Email can not be null or empty";
  public static final String ERROR_INVALID_ROLES = "Roles can not be null or empty";
  public static final String ERROR_INVALID_UUID = "UUID can not be null or empty";

  @Id private UUID id;

  @Column(name = "name")
  private String name;

  @Column(name = "last_name")
  private String lastName;

  @Email(message = "Please provide a valid e-mail")
  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles")
  private Set<Role> roles;

  @Column(name = "created_at")
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Column(name = "update_at")
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "token_password_id", unique = true)
  private TokenEntity token;

  public User(UUID id, String name, String lastName, String email, String password) {
    setId(id);
    setName(name);
    setLastName(lastName);
    setEmail(email);
    setPassword(password);
  }

  public User(
      UUID id, String name, String lastName, String email, String password, Set<Role> roles) {
    setId(id);
    setName(name);
    setLastName(lastName);
    setEmail(email);
    setPassword(password);
    setRoles(roles);
  }

  public User() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    Utils.argumentNotEmpty(name, ERROR_INVALID_NAME);
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    Utils.argumentNotEmpty(lastName, ERROR_INVALID_LAST_NAME);
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    Utils.argumentNotEmpty(email, ERROR_INVALID_EMAIL);
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    Utils.argumentNotEmpty(password, ERROR_INVALID_PASS);
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    Utils.argumentNotNull(roles, ERROR_INVALID_ROLES);
    this.roles = roles;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    Utils.argumentNotNull(id, ERROR_INVALID_UUID);
    this.id = id;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public TokenEntity getToken() {
    return token;
  }

  public void setToken(TokenEntity token) {
    this.token = token;
  }

  @Override
  public String toString() {
    return "User [id="
        + id
        + ", name="
        + name
        + ", lastName="
        + lastName
        + ", email="
        + email
        + ", password="
        + password
        + ", roles="
        + roles
        + ", createdAt="
        + createdAt
        + ", updatedAt="
        + updatedAt
        + ", token="
        + token
        + "]";
  }
}
