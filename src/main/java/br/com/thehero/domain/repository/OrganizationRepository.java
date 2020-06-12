package br.com.thehero.domain.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.thehero.domain.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

  Optional<Organization> findByCnpj(String cnpj);

  Optional<Organization> findByEmail(String email);

}
