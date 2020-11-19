package br.com.thehero.service.file.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.BDDMockito;
import org.springframework.web.multipart.MultipartFile;
import br.com.thehero.domain.model.Files;
import br.com.thehero.domain.model.Incidents;
import br.com.thehero.domain.repository.FilesRepository;
import br.com.thehero.domain.repository.IncidentsRepository;
import br.com.thehero.exception.IncidentNotFoundException;
import br.com.thehero.providers.IncidentsEntityProviderTest;

class FilesServiceImplTest {

  private static final String FILENAME = "cachorrinha perola";
  private static final String TYPE_FILE = ".png";

  private FilesRepository filesRepository;
  private IncidentsRepository incidentsRepository;
  private MultipartFile multipartFile;
  private Files file;
  private FilesServiceImpl service;

  @BeforeEach
  void setUp() {
    this.filesRepository = spy(FilesRepository.class);
    this.incidentsRepository = spy(IncidentsRepository.class);
    this.multipartFile = spy(MultipartFile.class);

    this.service = new FilesServiceImpl(this.filesRepository, this.incidentsRepository);
    this.file =
        Files.newBuilder().filename(FILENAME).type(TYPE_FILE).uuid(UUID.randomUUID()).build();
  }

  @ParameterizedTest
  @ArgumentsSource(IncidentsEntityProviderTest.class)
  void shouldSaveFile(Incidents incident) throws IOException {
    BDDMockito.given(this.incidentsRepository.findById(incident.getUuid()))
        .willReturn(Optional.of(incident));

    BDDMockito.given(this.filesRepository.saveAndFlush(this.file)).willReturn(this.file);

    BDDMockito.given(this.incidentsRepository.save(incident)).willReturn(incident);

    BDDMockito.given(this.multipartFile.getOriginalFilename()).willReturn(FILENAME);

    this.service.save(this.multipartFile, incident.getUuid().toString());

    verify(this.incidentsRepository, times(1)).findById(any());
    verify(this.incidentsRepository, times(1)).save(any(Incidents.class));
    verify(this.filesRepository, times(1)).saveAndFlush(any(Files.class));
    verify(this.multipartFile, times(2)).getOriginalFilename();
  }

  @Test
  void shouldExpectedException() {
    UUID uuid = UUID.randomUUID();
    BDDMockito.given(this.incidentsRepository.findById(uuid)).willReturn(Optional.empty());
    BDDMockito.given(this.multipartFile.getOriginalFilename()).willReturn(FILENAME);

    Exception exception =
        assertThrows(
            IncidentNotFoundException.class,
            () -> {
              this.service.save(this.multipartFile, uuid.toString());
            });

    assertEquals("Incident not found with UUID informed [" + uuid + "]", exception.getMessage());
    assertTrue(exception instanceof IncidentNotFoundException);

    verify(this.multipartFile, times(1)).getOriginalFilename();
    verify(this.incidentsRepository, times(1)).findById(any());
  }
}
