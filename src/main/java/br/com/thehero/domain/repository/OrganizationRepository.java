package br.com.thehero.domain.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.thehero.domain.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

  Optional<Organization> findByCnpj(String cnpj);

}
