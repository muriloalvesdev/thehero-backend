package br.com.thehero.login.service;

import br.com.thehero.dto.UserDTO;
import br.com.thehero.login.exception.EmailNotFoundException;
import br.com.thehero.login.model.User;
import br.com.thehero.login.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) {

    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(
                () -> new EmailNotFoundException("User Not Found with -> email : " + email));

    return UserDTO.build(user);
  }
}
