package br.com.thehero.login.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.thehero.login.model.TokenEntity;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

  Optional<TokenEntity> findByToken(String token);

  Optional<TokenEntity> findByIdUser(UUID userId);
}
