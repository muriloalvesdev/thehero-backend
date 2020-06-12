package br.com.thehero.login.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.thehero.login.model.Role;
import br.com.thehero.login.model.Role.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByName(RoleName roleName);

}
