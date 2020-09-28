package br.com.thehero.service.incidents.impl;

import br.com.thehero.domain.model.Incidents;
import br.com.thehero.domain.model.Incidents.Status;
import br.com.thehero.domain.model.Organization;
import br.com.thehero.domain.repository.FilesRepository;
import br.com.thehero.domain.repository.IncidentsRepository;
import br.com.thehero.domain.repository.OrganizationRepository;
import br.com.thehero.dto.IncidentsDTO;
import br.com.thehero.service.convert.IncidentsConvert;
import br.com.thehero.service.incidents.IncidentsService;
import java.util.Optional;
import java.util.UUID;
import javassist.NotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
public class IncidentsServiceImpl implements IncidentsService {

  IncidentsRepository incidentsRepository;
  OrganizationRepository organizationRepository;
  FilesRepository filesRepository;

  public Incidents create(IncidentsDTO dto, String cnpjOrganization) {
    Optional<Organization> optionalOrganization =
        organizationRepository.findByCnpj(cnpjOrganization);
    if (optionalOrganization.isPresent()) {
      Organization organization = optionalOrganization.get();
      Incidents incidents = IncidentsConvert.convertDataTransferObjetToEntity(dto, organization);

      return incidentsRepository.saveAndFlush(incidents);

    } else {
      throw new RuntimeException("Não existe uma organização com o CNPJ informado.");
    }
  }

  public Page<IncidentsDTO> findAll(Pageable pageable) {
    return incidentsRepository
        .findByStatus(Status.AVAILABLE, pageable)
        .map(IncidentsConvert::convertEntityToDataTransferObject);
  }

  public void delete(String incidentId, String cnpjOrganization) {
    incidentsRepository
        .findById(UUID.fromString(incidentId))
        .ifPresent(
            incident -> {
              if (incident.getOrganization().getCnpj().equals(cnpjOrganization)) {
                incident.setStatus(Status.NOT_AVAILABLE);
                incidentsRepository.save(incident);
              } else {
                throw new IllegalAccessError("Não autorizado!");
              }
            });
  }

  public IncidentsDTO findById(String incidentId) throws NotFoundException {
    Optional<Incidents> incidentOptional =
        incidentsRepository.findById(UUID.fromString(incidentId));

    if (incidentOptional.isPresent() && incidentOptional.get().isAvailable()) {
      Incidents incidents = incidentOptional.get();
      return IncidentsConvert.convertEntityToDataTransferObject(incidents);
    } else {
      throw new NotFoundException("Não existe um incidente com o ID informado");
    }
  }
}
