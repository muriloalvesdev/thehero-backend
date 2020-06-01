package br.com.thehero.login.service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.thehero.domain.model.Organization;
import br.com.thehero.domain.repository.OrganizationRepository;
import br.com.thehero.login.config.jwt.JwtProvider;
import br.com.thehero.login.exception.EmailNotFoundException;
import br.com.thehero.login.exception.ExistingEmailException;
import br.com.thehero.login.exception.IllegalRoleException;
import br.com.thehero.login.model.AccessToken;
import br.com.thehero.login.model.Role;
import br.com.thehero.login.model.Role.RoleName;
import br.com.thehero.login.model.User;
import br.com.thehero.login.repository.RoleRepository;
import br.com.thehero.login.repository.UserRepository;
import br.com.thehero.login.request.LoginDTO;
import br.com.thehero.login.request.RegisterDTO;

@Service
public class UserService {

  private UserRepository userRepository;
  private RoleRepository roleRepository;
  private PasswordEncoder encoder;
  private JwtProvider jwtProvider;
  private AuthenticationManager authenticationManager;
  private OrganizationRepository organizationRepository;

  public UserService(UserRepository userRepository, RoleRepository roleRepository,
      PasswordEncoder encoder, JwtProvider jwtProvider, AuthenticationManager authenticationManager,
      OrganizationRepository organizationRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.encoder = encoder;
    this.jwtProvider = jwtProvider;
    this.authenticationManager = authenticationManager;
    this.organizationRepository = organizationRepository;
  }

  public User registerUser(RegisterDTO registerData) {

    if (userRepository.existsByEmail(registerData.getEmail())) {
      throw new ExistingEmailException("Fail -> Email is already in use!");
    }

    User user = new User(UUID.randomUUID(), registerData.getName(), registerData.getLastName(),
        registerData.getEmail(), encodePassword(registerData.getPassword()));

    Set<String> strRoles = registerData.getRole();
    Set<Role> roles = new HashSet<>();

    strRoles.forEach(role -> {
      switch (role) {
        case "admin":
          Role admin = roleRepository.findByName(RoleName.ROLE_ADMIN)
              .orElseThrow(() -> new IllegalRoleException("Fail! -> Cause: Admin Role not find."));
          roles.add(admin);
          break;
        default:
          throw new IllegalRoleException("Fail! -> Cause: Role invalid.");
      }
    });

    user.setRoles(roles);
    return userRepository.saveAndFlush(user);

  }

  private String encodePassword(String password) {
    return encoder.encode(password);
  }

  public AccessToken authenticateUser(LoginDTO loginDto) {

    Authentication authentication =
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginDto.getEmail(), encodePassword(loginDto.getPassword())));

    SecurityContextHolder.getContext().setAuthentication(authentication);


    Organization organization = organizationRepository.findByEmail(loginDto.getEmail()).orElseThrow(
        () -> new EmailNotFoundException("Informed email does not exist in the database!"));


    return new AccessToken(jwtProvider.generateJwtToken(authentication), organization.getCnpj(),
        organization.getFullName());
  }

  public void resetPassword(LoginDTO loginData) {
    User user = userRepository.findByEmail(loginData.getEmail()).orElseThrow(
        () -> new EmailNotFoundException("Informed email does not exist in the database!"));
    user.setPassword(loginData.getPassword());
    userRepository.save(user);
  }
}
