package br.com.thehero.controller.logout;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.thehero.logout.request.Token;
import br.com.thehero.logout.service.InvalidationTokenService;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class LogoutController {

  private InvalidationTokenService service;

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(path = "/information/{token}")
  public ResponseEntity<Claims> getInformationUser(@RequestBody Token token) {
    return ResponseEntity.ok(this.service.getInformations(token.getToken()));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(path = "/token-expiration/")
  public HttpStatus setExpirationToken(@RequestBody Token token) {
    this.service.invalidate(token.getToken());
    return HttpStatus.NO_CONTENT;
  }
}
