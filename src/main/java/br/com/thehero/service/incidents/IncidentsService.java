package br.com.thehero.service.incidents;

import java.util.List;
import org.springframework.data.domain.Pageable;
import br.com.thehero.domain.model.Incidents;
import br.com.thehero.dto.IncidentsDTO;
import javassist.NotFoundException;

public interface IncidentsService {
  Incidents create(IncidentsDTO dto, String cnpjOrganization) throws NotFoundException;

  List<IncidentsDTO> findAll(Pageable pageable);

  void delete(String incidentId, String organizationId);

  IncidentsDTO findById(String incidentsId) throws NotFoundException;
}
