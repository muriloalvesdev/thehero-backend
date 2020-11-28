package br.com.thehero.controller.authentication;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.thehero.login.model.AccessToken;
import br.com.thehero.login.model.User;
import br.com.thehero.login.request.LoginDTO;
import br.com.thehero.login.request.RegisterDTO;
import br.com.thehero.login.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

  private UserService userService;

  @PostMapping("/login")
  public ResponseEntity<AccessToken> authenticateUser(@Validated @RequestBody LoginDTO loginData) {
    return ResponseEntity.ok().body(this.userService.authenticateUser(loginData));
  }

  @PostMapping("/register")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<User> registerUser(@Valid @RequestBody RegisterDTO registerData) {

    User user = this.userService.registerUser(registerData);

    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(user.getId()).toUri()).build();
  }
}
