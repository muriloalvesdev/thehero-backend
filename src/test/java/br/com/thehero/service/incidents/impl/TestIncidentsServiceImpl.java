package br.com.thehero.service.incidents.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import br.com.thehero.domain.model.Incidents;
import br.com.thehero.domain.repository.IncidentsRepository;
import br.com.thehero.domain.repository.OrganizationRepository;
import br.com.thehero.dto.IncidentsDTO;
import br.com.thehero.exception.OrganizationNotFoundException;
import br.com.thehero.providers.IncidentsDTOProviderTest;
import br.com.thehero.providers.IncidentsEntityProviderTest;
import br.com.thehero.service.incidents.IncidentsService;
import javassist.NotFoundException;

class TestIncidentsServiceImpl {

  private static final String CNPJ_FAKE = "00000000000000";
  private static final UUID UUID_FAKE = UUID.fromString("46bfef77-628c-49b5-a732-73f55f161fee");

  private IncidentsService service;
  private IncidentsRepository incidentsRepository;
  private OrganizationRepository organizationRepository;

  @BeforeEach
  void setUp() {
    this.incidentsRepository = mock(IncidentsRepository.class);
    this.organizationRepository = mock(OrganizationRepository.class);
    this.service = new IncidentsServiceImpl(incidentsRepository, organizationRepository);
  }

  @ParameterizedTest
  @ArgumentsSource(IncidentsDTOProviderTest.class)
  void testCreateWithError(IncidentsDTO incidentsDTO) {
    BDDMockito.given(this.organizationRepository.findByCnpj(CNPJ_FAKE))
        .willReturn(Optional.empty());

    Exception exception =
        assertThrows(
            Exception.class,
            () -> {
              this.service.create(incidentsDTO, CNPJ_FAKE);
            });

    assertTrue(exception instanceof OrganizationNotFoundException);
    assertEquals(IncidentsServiceImpl.ORGANIZATION_NOT_FOUND, exception.getMessage());
  }

  @ParameterizedTest
  @ArgumentsSource(IncidentsEntityProviderTest.class)
  void testDeleteWithError(Incidents incidents) {
    BDDMockito.given(this.incidentsRepository.findById(UUID_FAKE))
        .willReturn(Optional.of(incidents));

    BDDMockito.given(this.incidentsRepository.save(incidents)).willReturn(incidents);

    Throwable exception =
        assertThrows(
            Throwable.class,
            () -> {
              this.service.delete(String.valueOf(UUID_FAKE), CNPJ_FAKE);
            });

    assertTrue(exception instanceof Throwable);
    assertEquals(IncidentsServiceImpl.UNAUTHORIZED, exception.getMessage());

    BDDMockito.verify(this.incidentsRepository, times(1)).findById(UUID_FAKE);

    BDDMockito.verify(this.incidentsRepository, times(0)).save(Mockito.any());
  }

  @Test
  void testFindByIdWithError() {
    BDDMockito.given(this.incidentsRepository.findById(UUID_FAKE)).willReturn(Optional.empty());

    Exception exception =
        assertThrows(
            Exception.class,
            () -> {
              this.service.findById(String.valueOf(UUID_FAKE));
            });

    assertTrue(exception instanceof NotFoundException);
    assertEquals(IncidentsServiceImpl.INCIDENT_NOT_FOUND, exception.getMessage());

    BDDMockito.verify(this.incidentsRepository, times(1)).findById(UUID_FAKE);
  }
}
