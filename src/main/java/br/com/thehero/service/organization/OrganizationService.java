package br.com.thehero.service.organization;

import br.com.thehero.domain.model.Organization;
import br.com.thehero.dto.OrganizationDTO;
import javassist.NotFoundException;

import java.util.List;

public interface OrganizationService {

  Organization create(OrganizationDTO organizationDTO);

  void update(OrganizationDTO organizationDTO);

  List<OrganizationDTO> findAll();

  OrganizationDTO findByCnpj(String cnpj) throws NotFoundException;

  void delete(String cnpj) throws NotFoundException;

  Organization findByEmail(String email) throws NotFoundException;
}
