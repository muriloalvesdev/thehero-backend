package br.com.thehero.service.profile;

import br.com.thehero.domain.model.Organization;
import br.com.thehero.domain.repository.IncidentsRepository;
import br.com.thehero.domain.repository.OrganizationRepository;
import br.com.thehero.dto.IncidentsDTO;
import br.com.thehero.dto.IncidentsDTOList;
import br.com.thehero.service.convert.IncidentsConvert;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
public class ProfileService {

  OrganizationRepository organizationRepository;
  IncidentsRepository incidentsRepository;

  public IncidentsDTOList findIncidentsByOrganization(String cnpj) {
    Optional<Organization> organizationOptional = organizationRepository.findByCnpj(cnpj);
    List<IncidentsDTO> incidents = new ArrayList<>();
    IncidentsDTOList incidentsDTOList = new IncidentsDTOList();

    if (organizationOptional.isPresent()) {
      Organization organization = organizationOptional.get();
      incidentsRepository.findByOrganization(organization).stream()
          .forEach(
              incident -> {
                IncidentsDTO incidentsDTO =
                    IncidentsConvert.convertEntityToDataTransferObject(incident);
                incidents.add(incidentsDTO);
              });
    }
    incidentsDTOList.setIncidents(incidents);
    return incidentsDTOList;
  }
}
