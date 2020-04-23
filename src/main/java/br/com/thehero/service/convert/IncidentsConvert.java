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
    return new IncidentsDTO(incidents.getTitle(), incidents.getDescription(), incidents.getValue(),
        incidents.getUuid().toString(), organization.getName(), organization.getCity(),
        organization.getUf());
  }
}
