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
  void shouldCreateWithSuccess(OrganizationDTO dto) {
    // when
    BDDMockito.when(this.repository.save(Mockito.any(Organization.class)))
        .thenReturn(Mockito.any(Organization.class));

    // then
    this.service.create(dto);

    verify(this.repository, times(1)).save(Mockito.any());
  }

  @ParameterizedTest
  @ArgumentsSource(OrganizationDTOProviderTest.class)
  void shouldUpdateWithSuccess(OrganizationDTO dto) {
    // given
    Organization organization = OrganizationConvert.convertDataTransferObjectToEntity(dto);

    BDDMockito.when(this.repository.findByCnpj(dto.getCnpj()))
        .thenReturn(Optional.of(organization));
    BDDMockito.when(this.repository.saveAndFlush(organization)).thenReturn(organization);

    // then
    this.service.update(dto);

    verify(this.repository, times(1)).findByCnpj(Mockito.anyString());
    verify(this.repository, times(1)).saveAndFlush(Mockito.any());
  }

  @ParameterizedTest
  @ArgumentsSource(OrganizationDTOProviderTest.class)
  void shouldFindAllWithSuccessAndReturnOneElementInList(OrganizationDTO dto) {
    // given
    Organization organization = OrganizationConvert.convertDataTransferObjectToEntity(dto);
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
  void shouldFindByCnpjWithSuccess(OrganizationDTO dto) throws Exception {
    // given
    Organization organization = OrganizationConvert.convertDataTransferObjectToEntity(dto);

    // when
    BDDMockito.when(this.repository.findByCnpj(dto.getCnpj()))
        .thenReturn(Optional.of(organization));

    // then
    OrganizationDTO dtoActual = this.service.findByCnpj(dto.getCnpj());

    assertEquals(dto.getCity(), dtoActual.getCity());
    assertEquals(dto.getCnpj(), dtoActual.getCnpj());
    assertEquals(dto.getEmail(), dtoActual.getEmail());
    assertEquals(dto.getName(), dtoActual.getName());
    assertEquals(dto.getUf(), dtoActual.getUf());
    assertEquals(dto.getWhatsapp(), dtoActual.getWhatsapp());

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
  void shouldDeleteWithSuccess(OrganizationDTO dto) throws Exception {
    // given
    Organization organization = OrganizationConvert.convertDataTransferObjectToEntity(dto);

    // when
    BDDMockito.when(this.repository.findByCnpj(dto.getCnpj()))
        .thenReturn(Optional.of(organization));

    BDDMockito.doNothing().when(this.repository).delete(organization);

    // then
    this.service.delete(dto.getCnpj());

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
  void shouldFindByEmailWithSuccess(OrganizationDTO dto) throws Exception {
    // given
    Organization organizationExpected = OrganizationConvert.convertDataTransferObjectToEntity(dto);

    // when
    BDDMockito.when(this.repository.findByEmail(dto.getEmail()))
        .thenReturn(Optional.of(organizationExpected));

    // then
    Organization organizationActual = this.service.findByEmail(dto.getEmail());

    assertEquals(organizationExpected.getCity(), organizationActual.getCity());
    assertEquals(organizationExpected.getCnpj(), organizationActual.getCnpj());
    assertEquals(organizationExpected.getEmail(), organizationActual.getEmail());
    assertEquals(organizationExpected.getName(), organizationActual.getName());
    assertEquals(organizationExpected.getUf(), organizationActual.getUf());
    assertEquals(organizationExpected.getWhatsapp(), organizationActual.getWhatsapp());

    verify(this.repository, times(1)).findByEmail(Mockito.anyString());
  }
}
