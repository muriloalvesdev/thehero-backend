package br.com.thehero.login.repository;

import br.com.thehero.login.model.TokenEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

  Optional<TokenEntity> findByToken(String token);

  Optional<TokenEntity> findByIdUser(UUID userId);
}
