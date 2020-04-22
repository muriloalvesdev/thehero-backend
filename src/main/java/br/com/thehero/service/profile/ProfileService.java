package br.com.thehero.service.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import br.com.thehero.domain.model.Organization;
import br.com.thehero.domain.repository.IncidentsRepository;
import br.com.thehero.domain.repository.OrganizationRepository;
import br.com.thehero.dto.IncidentsDTO;
import br.com.thehero.service.convert.IncidentsConvert;

@Service
public class ProfileService {

  private OrganizationRepository organizationRepository;
  private IncidentsRepository incidentsRepository;

  public ProfileService(OrganizationRepository organizationRepository,
      IncidentsRepository incidentsRepository) {
    this.organizationRepository = organizationRepository;
    this.incidentsRepository = incidentsRepository;
  }

  public List<IncidentsDTO> findIncidentsByOrganization(String organizationId) {
    Optional<Organization> organizationOptional =
        organizationRepository.findById(UUID.fromString(organizationId));
    List<IncidentsDTO> incidents = new ArrayList<>();

    if (organizationOptional.isPresent()) {
      Organization organization = organizationOptional.get();
      incidentsRepository.findByOrganization(organization).stream().forEach(incident -> {
        IncidentsDTO incidentsDTO = IncidentsConvert.convertEntityToDataTransferObject(incident);
        incidents.add(incidentsDTO);
      });
    }
    return incidents;
  }


}
