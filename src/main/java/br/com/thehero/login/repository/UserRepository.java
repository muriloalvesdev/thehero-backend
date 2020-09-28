package br.com.thehero.login.repository;

import br.com.thehero.login.model.TokenEntity;
import br.com.thehero.login.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByEmail(String email);

  Boolean existsByEmail(String email);

  Optional<User> findByToken(TokenEntity tokenEntity);
}
