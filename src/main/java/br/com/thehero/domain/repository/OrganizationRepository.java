package br.com.thehero.domain.repository;

import br.com.thehero.domain.model.Organization;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

  Optional<Organization> findByCnpj(String cnpj);

  Optional<Organization> findByEmail(String email);
}
