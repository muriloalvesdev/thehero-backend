package br.com.thehero.service.organization.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
public class OrganizationServiceImpl implements OrganizationService {

  private OrganizationRepository repository;

  public Organization create(OrganizationDTO organizationDTO) {
    Organization organization = OrganizationConvert.convertDataTransferObjectToEntity(organizationDTO);
    return repository.saveAndFlush(organization);
  }

  public void update(OrganizationDTO organizationDTO) {
    repository.findByCnpj(organizationDTO.getCnpj()).ifPresent(organization -> {
      organization.setCity(organizationDTO.getCity());
      organization.setEmail(organizationDTO.getEmail());
      organization.setName(organizationDTO.getName());
      organization.setUf(organizationDTO.getUf());
      organization.setWhatsapp(String.valueOf(organizationDTO.getWhatsapp()));

      repository.saveAndFlush(organization);
    });
  }

  public List<OrganizationDTO> findAll() {
    List<OrganizationDTO> organizationDTOs = new ArrayList<>();

    repository.findAll().stream().forEach(organization -> {
      OrganizationDTO organizationDTO = OrganizationConvert.convertEntityToDataTransferObject(organization);
      organizationDTOs.add(organizationDTO);
    });
    return organizationDTOs;
  }

  public void delete(String cnpj) throws NotFoundException {
    Optional<Organization> organizationOptional = repository.findByCnpj(cnpj);
    if (organizationOptional.isPresent()) {
      repository.delete(organizationOptional.get());
    } else {
      throw new NotFoundException(
          "Não existe uma organização com o CNPJ [" + cnpj + "] informado.");
    }
  }

  public OrganizationDTO findByCnpj(String cnpj) throws NotFoundException {
    Optional<Organization> organizationOptional =
        repository.findAll().stream().filter(organization -> organization.getCnpj().equals(cnpj)).findFirst();
    if (organizationOptional.isPresent()) {
      OrganizationDTO organizationDTO =
          OrganizationConvert.convertEntityToDataTransferObject(organizationOptional.get());
      return organizationDTO;
    } else {
      throw new NotFoundException(
          "Não existe uma organização com o CNPJ [" + cnpj + "] informado.");
    }
  }

}
