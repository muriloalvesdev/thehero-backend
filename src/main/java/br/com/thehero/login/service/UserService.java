package br.com.thehero.login.service;

import java.util.HashSet;
import java.util.Optional;
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

  private static final String EMAIL_IS_ALREADY = "Fail -> Email is already in use!";
  private static final String ROLE_NOT_FOUND = "Fail! -> Cause: %s Role not find.";
  private static final String ROLE_INVALID = "Fail! -> Cause: Role invalid.";
  private static final String EMAIL_NOT_FOUND = "Informed email does not exist in the database!";

  private UserRepository userRepository;
  private RoleRepository roleRepository;
  private PasswordEncoder encoder;
  private JwtProvider jwtProvider;
  private AuthenticationManager authenticationManager;
  private OrganizationRepository organizationRepository;

  public UserService(
      UserRepository userRepository,
      RoleRepository roleRepository,
      PasswordEncoder encoder,
      JwtProvider jwtProvider,
      AuthenticationManager authenticationManager,
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
      throw new ExistingEmailException(EMAIL_IS_ALREADY);
    }

    User user =
        new User(
            UUID.randomUUID(),
            registerData.getName(),
            registerData.getLastName(),
            registerData.getEmail(),
            encoder.encode(registerData.getPassword()));

    Set<String> strRoles = registerData.getRole();
    Set<Role> roles = new HashSet<>();

    strRoles.forEach(
        role -> {
          switch (role.toLowerCase()) {
            case "admin":
              Role admin =
                  roleRepository
                      .findByName(RoleName.ROLE_ADMIN)
                      .orElseThrow(
                          () -> new IllegalRoleException(String.format(ROLE_NOT_FOUND, "Admin")));
              roles.add(admin);
              break;
            default:
              throw new IllegalRoleException(ROLE_INVALID);
          }
        });

    user.setRoles(roles);
    return userRepository.saveAndFlush(user);
  }

  public AccessToken authenticateUser(LoginDTO loginDto) {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    Optional<Organization> optionalOrganization =
        organizationRepository.findByEmail(loginDto.getEmail());

    if (!optionalOrganization.isPresent()) {
      throw new EmailNotFoundException(EMAIL_NOT_FOUND);
    }
    Organization organization = optionalOrganization.get();
    return new AccessToken(
        jwtProvider.generateJwtToken(authentication),
        organization.getCnpj(),
        organization.getName());
  }
}
