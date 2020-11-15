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
import javassist.NotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
public class IncidentsServiceImpl implements IncidentsService<Incidents, IncidentsDTO,String, Page, Pageable> {

  IncidentsRepository incidentsRepository;
  OrganizationRepository organizationRepository;
  FilesRepository filesRepository;

  public Incidents create(IncidentsDTO incidentsDTO, String cnpjOrganization) {
    Organization organization =
        this.organizationRepository
            .findByCnpj(cnpjOrganization)
            .orElseThrow(
                () -> new RuntimeException("Não existe uma organização com o CNPJ informado."));
    Incidents incidents =
        IncidentsConvert.convertDataTransferObjetToEntity(incidentsDTO, organization);
    return this.incidentsRepository.saveAndFlush(incidents);
  }

  public Page<IncidentsDTO> findAll(Pageable pageable) {
    return this.incidentsRepository
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
    return this.incidentsRepository
        .findById(UUID.fromString(incidentId))
        .filter(Incidents::isAvailable)
        .map(IncidentsConvert::convertEntityToDataTransferObject)
        .orElseThrow(() -> new NotFoundException("Não existe um incidente com o ID informado"));
  }
}
