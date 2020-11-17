package br.com.thehero.login.service;

import javax.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import br.com.thehero.dto.UserDTO;
import br.com.thehero.exception.EmailNotFoundException;
import br.com.thehero.login.model.User;
import br.com.thehero.login.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) {

    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(
                () -> new EmailNotFoundException("User Not Found with -> email : " + email));

    return UserDTO.build(user);
  }
}
