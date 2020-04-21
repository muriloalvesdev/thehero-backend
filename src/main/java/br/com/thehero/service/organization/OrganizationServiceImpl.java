package br.com.thehero.service.organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import br.com.thehero.dto.OrganizationDTO;
import br.com.thehero.model.Organization;
import br.com.thehero.repository.OrganizationRepository;
import br.com.thehero.service.convert.OrganizationConvert;
import javassist.NotFoundException;

public class OrganizationServiceImpl implements OrganizationService {

  private OrganizationRepository repository;

  public OrganizationServiceImpl(OrganizationRepository repository) {
    this.repository = repository;
  }

  public Organization create(OrganizationDTO dto) {
    Organization organization = OrganizationConvert.convertDataTransferObjectToEntity(dto);
    return repository.saveAndFlush(organization);
  }

  public void update(OrganizationDTO dto) {
    repository.findByCnpj(dto.getCnpj()).ifPresent(organization -> {
      organization.setCity(dto.getCity());
      organization.setEmail(dto.getEmail());
      organization.setName(dto.getName());
      organization.setUf(dto.getUf());
      organization.setWhatsapp(String.valueOf(dto.getWhatsapp()));

      repository.saveAndFlush(organization);
    });
  }

  public List<OrganizationDTO> findAll() {
    List<OrganizationDTO> organizationDTOs = new ArrayList<>();

    repository.findAll().stream().forEach(org -> {
      OrganizationDTO organizationDTO = OrganizationConvert.convertEntityToDataTransferObject(org);
      organizationDTOs.add(organizationDTO);
    });
    return organizationDTOs;
  }

  public void delete(String cnpj) {
    Optional<Organization> organizationOptional = getOrganizationOptionalByCnpj(cnpj);
    if (organizationOptional.isPresent()) {
      repository.delete(organizationOptional.get());
    }
  }

  public OrganizationDTO findByCnpj(String cnpj) throws NotFoundException {
    Optional<Organization> organizationOptional = getOrganizationOptionalByCnpj(cnpj);
    if (organizationOptional.isPresent()) {
      OrganizationDTO organizationDTO =
          OrganizationConvert.convertEntityToDataTransferObject(organizationOptional.get());
      return organizationDTO;
    } else {
      throw new NotFoundException("Não existe uma organização com o CNPJ informado.");
    }
  }

  private Optional<Organization> getOrganizationOptionalByCnpj(String cnpj) {
    Optional<Organization> organizationOptional = repository.findByCnpj(cnpj);
    return organizationOptional;
  }
}