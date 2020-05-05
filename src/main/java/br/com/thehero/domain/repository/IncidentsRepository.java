package br.com.thehero.domain.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.thehero.domain.model.Incidents;
import br.com.thehero.domain.model.Organization;

public interface IncidentsRepository extends JpaRepository<Incidents, UUID> {

  List<Incidents> findByOrganization(Organization organization);

}
