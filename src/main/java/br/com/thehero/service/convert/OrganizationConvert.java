package br.com.thehero.service.convert;

import br.com.thehero.domain.model.Organization;
import br.com.thehero.dto.OrganizationDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrganizationConvert {

  public static final Organization convertDataTransferObjectToEntity(
      OrganizationDTO organizationDTO) {
    return Organization.newBuilder()
        .name(organizationDTO.getName())
        .city(organizationDTO.getCity())
        .cnpj(organizationDTO.getCnpj())
        .email(organizationDTO.getEmail())
        .uf(organizationDTO.getUf())
        .whatsapp(organizationDTO.getWhatsapp())
        .build();
  }

  public static final OrganizationDTO convertEntityToDataTransferObject(Organization organization) {
    return OrganizationDTO.newBuilder()
        .name(organization.getName())
        .email(organization.getEmail())
        .whatsapp(organization.getWhatsapp())
        .city(organization.getCity())
        .uf(organization.getUf())
        .cnpj(organization.getCnpj())
        .build();
  }
}
