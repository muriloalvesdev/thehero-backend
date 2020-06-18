package br.com.thehero.logout.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/user")
public class LogoutController {

  @Autowired
  private InvalidationTokenService service;

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(path = "/information/{token}")
  public ResponseEntity<Claims> getInformationUser(@RequestBody() Token token) {
    return ResponseEntity.ok(service.getInformations(token.getToken()));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(path = "/token-expiration/")
  public HttpStatus setExpirationToken(@RequestBody() Token token) {
    service.invalidate(token.getToken());
    return HttpStatus.NO_CONTENT;
  }
}
