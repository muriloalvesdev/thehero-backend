package br.com.thehero.service.profile.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import br.com.thehero.OrganizationConstantsTest;
import br.com.thehero.domain.model.Incidents;
import br.com.thehero.domain.model.Organization;
import br.com.thehero.domain.repository.IncidentsRepository;
import br.com.thehero.domain.repository.OrganizationRepository;
import br.com.thehero.dto.IncidentsDTOList;
import br.com.thehero.providers.IncidentsEntityProviderTest;
import br.com.thehero.service.profile.ProfileService;
import javassist.NotFoundException;

class TestProfileServiceImpl implements OrganizationConstantsTest {

  private ProfileService service;
  private OrganizationRepository organizationRepository;
  private IncidentsRepository incidentsRepository;

  @BeforeEach
  void setUp() {
    this.organizationRepository = mock(OrganizationRepository.class);
    this.incidentsRepository = mock(IncidentsRepository.class);
    this.service = new ProfileServiceImpl(organizationRepository, incidentsRepository);
  }

  @ParameterizedTest
  @ArgumentsSource(IncidentsEntityProviderTest.class)
  void shouldFindIncidentsByOrganizationWithSuccess(Incidents incidents) throws NotFoundException {
    // given
    List<Incidents> incidentsList = new ArrayList<>();
    incidentsList.add(incidents);
    Organization organization = Organization.newBuilder().name(NAME).city(CITY).cnpj(CNPJ)
        .email(EMAIL_ONG).uf(UF).whatsapp(WHATSAPP).build();
    // when
    BDDMockito.when(this.organizationRepository.findByCnpj(organization.getCnpj()))
        .thenReturn(Optional.of(organization));
    BDDMockito.when(this.incidentsRepository.findByOrganization(organization))
        .thenReturn(incidentsList);

    // then
    IncidentsDTOList incidentsDTOList =
        this.service.findIncidentsByOrganization(organization.getCnpj());

    verify(this.organizationRepository, times(1)).findByCnpj(Mockito.anyString());
    verify(this.incidentsRepository, times(1)).findByOrganization(Mockito.any());

    assertEquals(1, incidentsDTOList.getIncidents().size());
  }

  @Test
  void shouldFindIncidentsByOrganizationWithError() {
    // when
    BDDMockito.when(this.organizationRepository.findByCnpj(CNPJ)).thenReturn(Optional.empty());

    Exception exception = assertThrows(Exception.class, () -> {
      this.service.findIncidentsByOrganization(CNPJ);
    });

    assertTrue(exception instanceof NotFoundException);
    assertEquals(String.format(ProfileServiceImpl.MESSAGE_ERROR, CNPJ), exception.getMessage());
  }

}
