package br.com.thehero.login.repository;

import br.com.thehero.login.model.Role;
import br.com.thehero.login.model.Role.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByName(RoleName roleName);
}
