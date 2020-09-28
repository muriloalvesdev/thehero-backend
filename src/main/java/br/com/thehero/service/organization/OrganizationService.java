package br.com.thehero.service.organization;

import br.com.thehero.domain.model.Organization;
import br.com.thehero.dto.OrganizationDTO;
import java.util.List;
import javassist.NotFoundException;

public interface OrganizationService {
  Organization create(OrganizationDTO dto);

  void update(OrganizationDTO dto);

  List<OrganizationDTO> findAll();

  OrganizationDTO findByCnpj(String cnpj) throws NotFoundException;

  void delete(String cnpj) throws NotFoundException;
}
