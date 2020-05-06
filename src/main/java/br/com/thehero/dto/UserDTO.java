package br.com.thehero.dto;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.thehero.login.model.User;

public class UserDTO implements UserDetails {
  private static final long serialVersionUID = -5745731685321252631L;

  private UUID id;
  private String name;
  private String lastName;
  private String email;
  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDTO(UUID id, String name, String lastName, String email, String password,
      Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserDTO build(User user) {
    List<GrantedAuthority> authorities =
        user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
            .collect(Collectors.toList());

    return new UserDTO(UUID.randomUUID(), user.getName(), user.getLastName(), user.getEmail(),
        user.getPassword(), authorities);
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getUsername() {
    return lastName;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserDTO other = (UserDTO) obj;
    if (authorities == null) {
      if (other.authorities != null)
        return false;
    } else if (!authorities.equals(other.authorities))
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    return true;
  }

}
