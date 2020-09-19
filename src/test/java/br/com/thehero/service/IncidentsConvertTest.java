package br.com.thehero.service;

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
import br.com.thehero.service.convert.IncidentsConvert;

class IncidentsConvertTest {

  private IncidentsDTO dto;

  @BeforeEach
  void setUp() {
    this.dto = IncidentsDTO.newBuilder().city(OrganizationConstantsTest.CITY)
        .description(IncidentsConstantsTest.DESCRIPTION).email(OrganizationConstantsTest.EMAIL_ONG)
        .mimeType(IncidentsConstantsTest.MIME_TYPE).nameOrganization(OrganizationConstantsTest.NAME)
        .title(IncidentsConstantsTest.TITLE).value(IncidentsConstantsTest.VALUE)
        .uf(OrganizationConstantsTest.UF).whatsapp(OrganizationConstantsTest.WHATSAPP).build();
  }

  @ParameterizedTest
  @ArgumentsSource(OrganizationEntityProviderTest.class)
  void convertDataTransferObjetToEntityTest(Organization organization) {
    Incidents incidents = IncidentsConvert.convertDataTransferObjetToEntity(this.dto, organization);

    assertEquals(this.dto.getCity(), organization.getCity());
    assertEquals(this.dto.getDescription(), incidents.getDescription());
    assertEquals(this.dto.getEmail(), organization.getEmail());
    assertEquals(this.dto.getNameOrganization(), organization.getName());
    assertEquals(this.dto.getTitle(), incidents.getTitle());
    assertEquals(this.dto.getUf(), organization.getUf());
    assertEquals(this.dto.getValue(), incidents.getValue());
    assertEquals(this.dto.getWhatsapp(), organization.getWhatsapp());
  }

}
