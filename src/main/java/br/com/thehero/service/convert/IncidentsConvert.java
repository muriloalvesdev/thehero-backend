package br.com.thehero.service.convert;

import br.com.thehero.domain.model.Incidents;
import br.com.thehero.domain.model.Organization;
import br.com.thehero.dto.IncidentsDTO;

public final class IncidentsConvert {

  private IncidentsConvert() {}

  public static final Incidents convertDataTransferObjetToEntity(IncidentsDTO dto,
      Organization organization) {
    return new Incidents(dto.getTitle(), dto.getDescription(), dto.getValue(), organization);
  }

  public static final IncidentsDTO convertEntityToDataTransferObject(Incidents incidents) {
    Organization organization = incidents.getOrganization();
    return IncidentsDTO.newBuilder().title(incidents.getTitle())
        .description(incidents.getDescription()).value(incidents.getValue())
        .id(incidents.getUuid().toString()).nameOrganization(organization.getName())
        .city(organization.getCity()).uf(organization.getUf()).whatsapp(organization.getWhatsapp())
        .email(organization.getEmail()).fileData(incidents.getFiles().getData())
        .mimeType(incidents.getFiles().getType()).build();
  }
}
