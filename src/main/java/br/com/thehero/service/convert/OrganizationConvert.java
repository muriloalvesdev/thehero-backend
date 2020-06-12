package br.com.thehero.service.convert;

import br.com.thehero.domain.model.Organization;
import br.com.thehero.domain.model.Organization.OrganizationBuilder;
import br.com.thehero.dto.OrganizationDTO;

public final class OrganizationConvert {
  private OrganizationConvert() {}

  public static final Organization convertDataTransferObjectToEntity(OrganizationDTO dto) {
    return OrganizationBuilder.newBuilder(dto.getName()).withCity(dto.getCity())
        .withCnpj(dto.getCnpj()).withEmail(dto.getEmail()).withUf(dto.getUf())
        .withWhatsapp(dto.getWhatsapp()).build();
  }

  public static final OrganizationDTO convertEntityToDataTransferObject(Organization org) {
    return new OrganizationDTO(org.getName(), org.getEmail(), org.getWhatsapp(), org.getCity(),
        org.getUf(), org.getCnpj());
  }
}
