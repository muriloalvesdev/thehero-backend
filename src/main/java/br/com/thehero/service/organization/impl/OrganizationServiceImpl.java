package br.com.thehero.service.organization.impl;

import br.com.thehero.domain.model.Organization;
import br.com.thehero.domain.repository.OrganizationRepository;
import br.com.thehero.dto.OrganizationDTO;
import br.com.thehero.service.convert.OrganizationConvert;
import br.com.thehero.service.organization.OrganizationService;
import javassist.NotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
public class OrganizationServiceImpl implements OrganizationService {

  private OrganizationRepository repository;

  @Override
  public Organization create(OrganizationDTO organizationDTO) {
    Organization organization =
        OrganizationConvert.convertDataTransferObjectToEntity(organizationDTO);
    return repository.save(organization);
  }

  @Override
  public void update(OrganizationDTO organizationDTO) {
    repository
        .findByCnpj(organizationDTO.getCnpj())
        .ifPresent(
            organization -> {
              organization.setCity(organizationDTO.getCity());
              organization.setEmail(organizationDTO.getEmail());
              organization.setName(organizationDTO.getName());
              organization.setUf(organizationDTO.getUf());
              organization.setWhatsapp(String.valueOf(organizationDTO.getWhatsapp()));

              repository.saveAndFlush(organization);
            });
  }

  @Override
  public List<OrganizationDTO> findAll() {
    return this.repository.findAll().stream()
        .map(OrganizationConvert::convertEntityToDataTransferObject)
        .collect(Collectors.toList());
  }

  @Override
  public OrganizationDTO findByCnpj(String cnpj) throws NotFoundException {
    return this.repository
        .findByCnpj(cnpj)
        .map(OrganizationConvert::convertEntityToDataTransferObject)
        .orElseThrow(
            () ->
                new NotFoundException(
                    "Não existe uma organização com o CNPJ [" + cnpj + "] informado."));
  }

  @Override
  public void delete(String cnpj) throws NotFoundException {
    Optional<Organization> organizationOptional = repository.findByCnpj(cnpj);

    Organization organization =
        organizationOptional.orElseThrow(
            () ->
                new NotFoundException(
                    "Não existe uma organização com o CNPJ [" + cnpj + "] informado."));
    repository.delete(organization);
  }

  @Override
  public Organization findByEmail(String email) throws NotFoundException {
    return this.repository
        .findByEmail(email)
        .orElseThrow(
            () ->
                new NotFoundException(
                    "Não existe uma organização com o EMAIL [" + email + "] informado."));
  }
}
