package br.com.thehero.login.config.jwt;

import br.com.thehero.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

  private static final Logger LOG = LoggerFactory.getLogger(JwtProvider.class);

  @Value("${security.app.jwtSecret}")
  private String jwtSecret;

  @Value("${security.app.jwtExpiration}")
  private int jwtExpiration;

  public String generateJwtToken(Authentication authentication) {

    UserDTO userPrincipal = (UserDTO) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject((userPrincipal.getEmail()))
        .setIssuedAt(new Date())
        .claim("username", userPrincipal.getName())
        .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public Claims getUserInformation(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      LOG.error("Invalid JWT signature -> Message: {} ", e);
    } catch (MalformedJwtException e) {
      LOG.error("Invalid JWT token -> Message: {}", e);
    } catch (ExpiredJwtException e) {
      LOG.error("Expired JWT token -> Message: {}", e);
    } catch (UnsupportedJwtException e) {
      LOG.error("Unsupported JWT token -> Message: {}", e);
    } catch (IllegalArgumentException e) {
      LOG.error("JWT claims string is empty -> Message: {}", e);
    }

    return false;
  }
}
