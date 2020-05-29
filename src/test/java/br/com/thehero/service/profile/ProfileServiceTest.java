package br.com.thehero.service.profile;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.thehero.domain.model.Incidents;
import br.com.thehero.domain.model.Incidents.IncidentsBuilder;
import br.com.thehero.domain.model.Organization;
import br.com.thehero.domain.model.Organization.OrganizationBuilder;
import br.com.thehero.domain.repository.IncidentsRepository;
import br.com.thehero.domain.repository.OrganizationRepository;
import br.com.thehero.dto.IncidentsDTO;
import br.com.thehero.dto.IncidentsDTOList;
import br.com.thehero.service.IncidentsConstans;
import br.com.thehero.service.OrganizationConstans;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles(profiles = {"test"})
@RunWith(SpringRunner.class)
public class ProfileServiceTest implements OrganizationConstans, IncidentsConstans {

  @Autowired
  private ProfileService service;

  @Autowired
  private OrganizationRepository organizationRepository;

  @Autowired
  private IncidentsRepository incidentsRepository;

  @Before
  public void setUp() {
    assertEquals(0, organizationRepository.findAll().size());

    Organization organization = OrganizationBuilder.newBuilder(NAME).withCity(CITY).withCnpj(CNPJ)
        .withEmail(EMAIL).withUf(UF).withWhatsapp(WHATSAPP).build();

    organizationRepository.save(organization);

    Incidents incidents = IncidentsBuilder.newBuilder(organization).withDescription(DESCRIPTION)
        .withTitle(TITLE).withValue(VALUE).build();

    incidentsRepository.save(incidents);
  }

  @After
  public void after() {
    organizationRepository.deleteAll();
  }

  @Test
  public void findIncidentsByOrganization() {

    IncidentsDTOList incidents = service.findIncidentsByOrganization(CNPJ);

    IncidentsDTO dto = incidents.getIncidents().get(0);
    assertEquals(NAME, dto.getNameOrganization());
    assertEquals(CITY, dto.getCity());
    assertEquals(UF, dto.getUf());
    assertEquals(EMAIL, dto.getEmail());
    assertEquals(WHATSAPP, dto.getWhatsapp());
    assertEquals(TITLE, dto.getTitle());
    assertEquals(DESCRIPTION, dto.getDescription());
    assertEquals(VALUE, dto.getValue());
  }

}
