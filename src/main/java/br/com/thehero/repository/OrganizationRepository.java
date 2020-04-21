package br.com.thehero.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.thehero.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

  Optional<Organization> findByCnpj(String cnpj);

}
