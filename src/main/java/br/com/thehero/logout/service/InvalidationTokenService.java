package br.com.thehero.logout.service;

import org.springframework.stereotype.Service;
import br.com.thehero.login.config.jwt.JwtBlacklist;
import br.com.thehero.login.config.jwt.JwtProvider;
import io.jsonwebtoken.Claims;

@Service
public class InvalidationTokenService {

  private JwtBlacklist blacklist;
  private JwtProvider jwtProvider;

  public InvalidationTokenService(JwtBlacklist blacklist, JwtProvider jwtProvider) {
    this.blacklist = blacklist;
    this.jwtProvider = jwtProvider;
  }

  public Claims getInformations(String token) {
    return jwtProvider.getUserInformation(token);
  }

  public void invalidate(String token) {
    blacklist.add(token);
  }
}
