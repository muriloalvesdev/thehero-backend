package br.com.thehero.service.organization.impl;

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
import br.com.thehero.domain.model.Organization;
import br.com.thehero.domain.repository.OrganizationRepository;
import br.com.thehero.dto.OrganizationDTO;
import br.com.thehero.providers.OrganizationDTOProviderTest;
import br.com.thehero.service.convert.OrganizationConvert;
import br.com.thehero.service.organization.OrganizationService;
import javassist.NotFoundException;

class TestOrganizationServiceImpl {

  private OrganizationService service;
  private OrganizationRepository repository;

  @BeforeEach
  void setUp() {
    this.repository = mock(OrganizationRepository.class);
    this.service = new OrganizationServiceImpl(this.repository);
  }

  @ParameterizedTest
  @ArgumentsSource(OrganizationDTOProviderTest.class)
  void shouldCreateWithSuccess(OrganizationDTO organizationDataTransferObject) {
    // when
    BDDMockito.when(this.repository.save(Mockito.any(Organization.class)))
        .thenReturn(Mockito.any(Organization.class));

    // then
    this.service.create(organizationDataTransferObject);

    verify(this.repository, times(1)).save(Mockito.any());
  }

  @ParameterizedTest
  @ArgumentsSource(OrganizationDTOProviderTest.class)
  void shouldUpdateWithSuccess(OrganizationDTO organizationDataTransferObject) {
    // given
    Organization organization =
        OrganizationConvert.convertDataTransferObjectToEntity(organizationDataTransferObject);

    BDDMockito.when(this.repository.findByCnpj(organizationDataTransferObject.getCnpj()))
        .thenReturn(Optional.of(organization));
    BDDMockito.when(this.repository.saveAndFlush(organization)).thenReturn(organization);

    // then
    this.service.update(organizationDataTransferObject);

    verify(this.repository, times(1)).findByCnpj(Mockito.anyString());
    verify(this.repository, times(1)).saveAndFlush(Mockito.any());
  }

  @ParameterizedTest
  @ArgumentsSource(OrganizationDTOProviderTest.class)
  void shouldFindAllWithSuccessAndReturnOneElementInList(
      OrganizationDTO organizationDataTransferObject) {
    // given
    Organization organization =
        OrganizationConvert.convertDataTransferObjectToEntity(organizationDataTransferObject);
    List<Organization> organizations = new ArrayList<>();
    organizations.add(organization);

    // when
    BDDMockito.when(this.repository.findAll()).thenReturn(organizations);

    // then
    List<OrganizationDTO> result = this.service.findAll();

    assertEquals(1, result.size());

    verify(this.repository, times(1)).findAll();
  }

  @ParameterizedTest
  @ArgumentsSource(OrganizationDTOProviderTest.class)
  void shouldFindByCnpjWithSuccess(OrganizationDTO organizationDataTransferObject)
      throws Exception {
    // given
    Organization organization =
        OrganizationConvert.convertDataTransferObjectToEntity(organizationDataTransferObject);

    // when
    BDDMockito.when(this.repository.findByCnpj(organizationDataTransferObject.getCnpj()))
        .thenReturn(Optional.of(organization));

    // then
    OrganizationDTO dtoActual = this.service.findByCnpj(organizationDataTransferObject.getCnpj());

    assertEquals(organizationDataTransferObject.getCity(), dtoActual.getCity());
    assertEquals(organizationDataTransferObject.getCnpj(), dtoActual.getCnpj());
    assertEquals(organizationDataTransferObject.getEmail(), dtoActual.getEmail());
    assertEquals(organizationDataTransferObject.getName(), dtoActual.getName());
    assertEquals(organizationDataTransferObject.getUf(), dtoActual.getUf());
    assertEquals(organizationDataTransferObject.getWhatsapp(), dtoActual.getWhatsapp());

    verify(this.repository, times(1)).findByCnpj(Mockito.anyString());
  }

  @Test
  void shouldFindByCnpjWithError() {
    // when
    BDDMockito.when(this.repository.findByCnpj(Mockito.anyString())).thenReturn(Optional.empty());

    // then
    Exception exception = assertThrows(Exception.class, () -> {
      this.service.findByCnpj("anything");
    });

    assertTrue(exception instanceof NotFoundException);
    assertEquals(String.format(OrganizationServiceImpl.MESSAGE_NOT_FOUND, "CNPJ", "anything"),
        exception.getMessage());
  }

  @ParameterizedTest
  @ArgumentsSource(OrganizationDTOProviderTest.class)
  void shouldDeleteWithSuccess(OrganizationDTO organizationDataTransferObject) throws Exception {
    // given
    Organization organization =
        OrganizationConvert.convertDataTransferObjectToEntity(organizationDataTransferObject);

    // when
    BDDMockito.when(this.repository.findByCnpj(organizationDataTransferObject.getCnpj()))
        .thenReturn(Optional.of(organization));

    BDDMockito.doNothing().when(this.repository).delete(organization);

    // then
    this.service.delete(organizationDataTransferObject.getCnpj());

    verify(this.repository, times(1)).findByCnpj(Mockito.anyString());
    verify(this.repository, times(1)).delete(Mockito.any());
  }

  @Test
  void shouldDeleteWithError() {
    // when
    BDDMockito.when(this.repository.findByCnpj(Mockito.anyString())).thenReturn(Optional.empty());

    // then
    Exception exception = assertThrows(Exception.class, () -> {
      this.service.delete("anything");
    });

    assertTrue(exception instanceof NotFoundException);
    assertEquals(String.format(OrganizationServiceImpl.MESSAGE_NOT_FOUND, "CNPJ", "anything"),
        exception.getMessage());
  }

  @ParameterizedTest
  @ArgumentsSource(OrganizationDTOProviderTest.class)
  void shouldFindByEmailWithSuccess(OrganizationDTO organizationDataTransferObject)
      throws Exception {
    // given
    Organization organizationExpected =
        OrganizationConvert.convertDataTransferObjectToEntity(organizationDataTransferObject);

    // when
    BDDMockito.when(this.repository.findByEmail(organizationDataTransferObject.getEmail()))
        .thenReturn(Optional.of(organizationExpected));

    // then
    Organization organizationActual =
        this.service.findByEmail(organizationDataTransferObject.getEmail());

    assertEquals(organizationExpected.getCity(), organizationActual.getCity());
    assertEquals(organizationExpected.getCnpj(), organizationActual.getCnpj());
    assertEquals(organizationExpected.getEmail(), organizationActual.getEmail());
    assertEquals(organizationExpected.getName(), organizationActual.getName());
    assertEquals(organizationExpected.getUf(), organizationActual.getUf());
    assertEquals(organizationExpected.getWhatsapp(), organizationActual.getWhatsapp());

    verify(this.repository, times(1)).findByEmail(Mockito.anyString());
  }

  @Test
  void shouldFindByEmailWithError() {
    // when
    BDDMockito.when(this.repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

    // then
    Exception exception = assertThrows(Exception.class, () -> {
      this.service.findByEmail("anything");
    });

    assertTrue(exception instanceof NotFoundException);
    assertEquals(String.format(OrganizationServiceImpl.MESSAGE_NOT_FOUND, "EMAIL", "anything"),
        exception.getMessage());
  }
}
