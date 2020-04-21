package br.com.thehero.service.convert;

import br.com.thehero.dto.OrganizationDTO;
import br.com.thehero.model.Organization;

public final class OrganizationConvert {
  private OrganizationConvert() {}

  public static final Organization convertDataTransferObjectToEntity(OrganizationDTO dto) {
    return new Organization(dto.getName(), dto.getEmail(), dto.getWhatsapp(), dto.getCity(),
        dto.getUf(), dto.getCnpj());
  }

  public static final OrganizationDTO convertEntityToDataTransferObject(Organization org) {
    return new OrganizationDTO(org.getName(), org.getEmail(), org.getWhatsapp(), org.getCity(),
        org.getUf(), org.getCnpj());
  }
}
