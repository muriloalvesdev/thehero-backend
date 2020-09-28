package br.com.thehero.service.incidents;

import br.com.thehero.domain.model.Incidents;
import br.com.thehero.dto.IncidentsDTO;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IncidentsService {
  Incidents create(IncidentsDTO dto, String cnpjOrganization);

  Page<IncidentsDTO> findAll(Pageable pageable);

  void delete(String incidentId, String organizationId);

  IncidentsDTO findById(String incidentsId) throws NotFoundException;
}
