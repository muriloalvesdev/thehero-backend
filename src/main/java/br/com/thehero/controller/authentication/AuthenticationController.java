package br.com.thehero.controller.authentication;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthenticationController {

  @Autowired private UserService userService;

  @PostMapping("/login")
  public ResponseEntity<AccessToken> authenticateUser(@Validated @RequestBody LoginDTO loginData) {
    return ResponseEntity.ok().body(userService.authenticateUser(loginData));
  }

  @PostMapping("/register")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<User> registerUser(@Valid @RequestBody RegisterDTO registerData) {

    User user = userService.registerUser(registerData);

    return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri())
        .build();
  }
}
