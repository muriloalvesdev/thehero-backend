package br.com.thehero.service.incidents;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.thehero.domain.model.Incidents;
import br.com.thehero.dto.IncidentsDTO;
import javassist.NotFoundException;

public interface IncidentsService {
  Incidents create(IncidentsDTO dto) throws NotFoundException;

  Page<IncidentsDTO> findAll(Pageable pageable);

  void delete(String incidentId, String organizationId);

  IncidentsDTO findById(String incidentsId) throws NotFoundException;
}
