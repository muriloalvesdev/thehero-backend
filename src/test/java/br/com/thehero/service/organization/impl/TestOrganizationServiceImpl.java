package br.com.thehero.service.organization.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
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
}
