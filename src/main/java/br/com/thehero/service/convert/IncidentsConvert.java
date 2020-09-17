package br.com.thehero.service.convert;

import br.com.thehero.domain.model.Incidents;
import br.com.thehero.domain.model.Organization;
import br.com.thehero.dto.IncidentsDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class IncidentsConvert {

  public static final Incidents convertDataTransferObjetToEntity(IncidentsDTO dto,
      Organization organization) {
    return Incidents.newBuilder().title(dto.getTitle()).description(dto.getDescription())
        .value(dto.getValue()).organization(organization).build();
  }

  public static final IncidentsDTO convertEntityToDataTransferObject(Incidents incidents) {
    Organization organization = incidents.getOrganization();
    return new IncidentsDTO(incidents.getTitle(), incidents.getDescription(), incidents.getValue(),
        incidents.getUuid().toString(), organization.getName(), organization.getCity(),
        organization.getUf(), organization.getWhatsapp(), organization.getEmail(),
        incidents.getFiles().getData(), incidents.getFiles().getType());
  }
}
