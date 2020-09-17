package br.com.thehero.service.convert;

import br.com.thehero.domain.model.Organization;
import br.com.thehero.dto.OrganizationDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrganizationConvert {

  public static final Organization convertDataTransferObjectToEntity(OrganizationDTO dto) {
    return Organization.newBuilder().name(dto.getName()).city(dto.getCity()).cnpj(dto.getCnpj())
        .email(dto.getEmail()).uf(dto.getUf()).whatsapp(dto.getWhatsapp()).build();
  }

  public static final OrganizationDTO convertEntityToDataTransferObject(Organization org) {
    return new OrganizationDTO(org.getName(), org.getEmail(), org.getWhatsapp(), org.getCity(),
        org.getUf(), org.getCnpj());
  }
}
