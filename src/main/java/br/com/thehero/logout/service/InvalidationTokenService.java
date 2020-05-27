package br.com.thehero.logout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.thehero.login.config.jwt.JwtBlacklist;
import br.com.thehero.login.config.jwt.JwtProvider;
import io.jsonwebtoken.Claims;

@Service
public class InvalidationTokenService {

  @Autowired
  private JwtBlacklist blacklist;

  @Autowired
  private JwtProvider jwtProvider;

  public Claims getInformations(String token) {
    return jwtProvider.getUserInformation(token);
  }

  public void invalidate(String token) {
    blacklist.add(token);
  }
}
