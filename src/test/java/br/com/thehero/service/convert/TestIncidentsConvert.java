package br.com.thehero.service.convert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import br.com.thehero.IncidentsConstantsTest;
import br.com.thehero.OrganizationConstantsTest;
import br.com.thehero.domain.model.Incidents;
import br.com.thehero.domain.model.Organization;
import br.com.thehero.dto.IncidentsDTO;
import br.com.thehero.providers.OrganizationEntityProviderTest;

class TestIncidentsConvert {

  private IncidentsDTO incidentsDTO;

  @BeforeEach
  void setUp() {
    this.incidentsDTO = IncidentsDTO.newBuilder().city(OrganizationConstantsTest.CITY)
        .description(IncidentsConstantsTest.DESCRIPTION).email(OrganizationConstantsTest.EMAIL_ONG)
        .mimeType(IncidentsConstantsTest.MIME_TYPE).nameOrganization(OrganizationConstantsTest.NAME)
        .title(IncidentsConstantsTest.TITLE).value(IncidentsConstantsTest.VALUE)
        .uf(OrganizationConstantsTest.UF).whatsapp(OrganizationConstantsTest.WHATSAPP).build();
  }

  @ParameterizedTest
  @ArgumentsSource(OrganizationEntityProviderTest.class)
  void convertDataTransferObjetToEntityTest(Organization organization) {
    Incidents incidents = IncidentsConvert.convertDataTransferObjetToEntity(this.incidentsDTO, organization);

    assertEquals(this.incidentsDTO.getCity(), organization.getCity());
    assertEquals(this.incidentsDTO.getDescription(), incidents.getDescription());
    assertEquals(this.incidentsDTO.getEmail(), organization.getEmail());
    assertEquals(this.incidentsDTO.getNameOrganization(), organization.getName());
    assertEquals(this.incidentsDTO.getTitle(), incidents.getTitle());
    assertEquals(this.incidentsDTO.getUf(), organization.getUf());
    assertEquals(this.incidentsDTO.getValue(), incidents.getValue());
    assertEquals(this.incidentsDTO.getWhatsapp(), organization.getWhatsapp());
  }
}
