package br.com.thehero.service.organization.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import br.com.thehero.domain.model.Organization;
import br.com.thehero.domain.repository.OrganizationRepository;
import br.com.thehero.dto.OrganizationDTO;
import br.com.thehero.service.convert.OrganizationConvert;
import br.com.thehero.service.organization.OrganizationService;
import javassist.NotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
class OrganizationServiceImpl implements OrganizationService {
  static final String MESSAGE_NOT_FOUND = "Não existe uma organização com o %s [%s] informado.";
  
  private OrganizationRepository repository;

  @Override
  public Organization create(OrganizationDTO organizationDTO) {
    Organization organization =
        OrganizationConvert.convertDataTransferObjectToEntity(organizationDTO);
    return repository.save(organization);
  }

  @Override
  public void update(OrganizationDTO organizationDTO) {
    this.repository
        .findByCnpj(organizationDTO.getCnpj())
        .ifPresent(
            organization -> {
              organization.setCity(organizationDTO.getCity());
              organization.setEmail(organizationDTO.getEmail());
              organization.setName(organizationDTO.getName());
              organization.setUf(organizationDTO.getUf());
              organization.setWhatsapp(String.valueOf(organizationDTO.getWhatsapp()));

              this.repository.saveAndFlush(organization);
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
                    String.format(MESSAGE_NOT_FOUND, "CNPJ", cnpj)));
  }

  @Override
  public void delete(String cnpj) throws NotFoundException {
    Organization organization = this.repository.findByCnpj(cnpj).orElseThrow(
            () ->
                new NotFoundException(
                    String.format(MESSAGE_NOT_FOUND, "CNPJ", cnpj)));
    this.repository.delete(organization);
  }

  @Override
  public Organization findByEmail(String email) throws NotFoundException {
    return this.repository
        .findByEmail(email)
        .orElseThrow(
            () ->
                new NotFoundException(
                    String.format(MESSAGE_NOT_FOUND, "EMAIL", email)));
  }
}
