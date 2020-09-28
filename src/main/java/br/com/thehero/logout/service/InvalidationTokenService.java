package br.com.thehero.logout.service;

import br.com.thehero.login.config.jwt.JwtBlacklist;
import br.com.thehero.login.config.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
public class InvalidationTokenService {

  private JwtBlacklist blacklist;
  private JwtProvider jwtProvider;

  public Claims getInformations(String token) {
    return jwtProvider.getUserInformation(token);
  }

  public void invalidate(String token) {
    blacklist.add(token);
  }
}
