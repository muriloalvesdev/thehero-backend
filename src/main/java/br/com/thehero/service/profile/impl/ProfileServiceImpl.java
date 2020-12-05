package br.com.thehero.service.profile.impl;

import br.com.thehero.domain.model.Organization;
import br.com.thehero.domain.repository.IncidentsRepository;
import br.com.thehero.domain.repository.OrganizationRepository;
import br.com.thehero.dto.IncidentsDTOList;
import br.com.thehero.service.convert.IncidentsConvert;
import br.com.thehero.service.profile.ProfileService;
import javassist.NotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
public class ProfileServiceImpl implements ProfileService {

  OrganizationRepository organizationRepository;
  IncidentsRepository incidentsRepository;

  public IncidentsDTOList findIncidentsByOrganization(String cnpj) throws NotFoundException {
    Organization organization =
        this.organizationRepository
            .findByCnpj(cnpj)
            .orElseThrow(
                () ->
                    new NotFoundException(
                        "Não existe uma organização com o CNPJ [" + cnpj + "] informado."));
    return new IncidentsDTOList(
        this.incidentsRepository.findByOrganization(organization).stream()
            .map(IncidentsConvert::convertEntityToDataTransferObject)
            .collect(Collectors.toList()));
  }
}
