package br.com.thehero.service.file.impl;

import java.io.IOException;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import br.com.thehero.domain.model.Files;
import br.com.thehero.domain.model.Incidents;
import br.com.thehero.domain.repository.FilesRepository;
import br.com.thehero.domain.repository.IncidentsRepository;
import br.com.thehero.exception.IncidentNotFoundException;
import br.com.thehero.service.file.FilesService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
class FilesServiceImpl implements FilesService {

  private static final Logger LOG = Logger.getLogger(FilesServiceImpl.class);
  private FilesRepository filesRepository;
  private IncidentsRepository incidentsRepository;

  public Files save(MultipartFile file, String uuidIncidents) {
    try {
      LOG.info("[BEGIN] save() - with filename [" + file.getName() + "]");
      return uploadFile(file, uuidIncidents);
    } catch (IOException e) {
      LOG.error("Persist file error: " + e.getMessage(), e);
    } finally {
      LOG.info("[END] save()");
    }
    return null;
  }

  private Files uploadFile(MultipartFile file, String uuidIncidents) throws IOException {
    byte[] data = file.getBytes();
    String type = file.getContentType();
    String filename = file.getOriginalFilename();
    validateFilename(filename);

    Incidents incidents =
        this.incidentsRepository
            .findById(UUID.fromString(uuidIncidents))
            .orElseThrow(
                () ->
                    new IncidentNotFoundException(
                        "Incident not found with UUID informed [" + uuidIncidents + "]"));

    Files files =
        this.filesRepository.saveAndFlush(
            Files.newBuilder()
                .filename(filename)
                .type(type)
                .incidents(incidents)
                .data(data)
                .build());
    incidents.setFiles(files);
    this.incidentsRepository.saveAndFlush(incidents);
    return files;
  }

  private void validateFilename(String filename) {
    if (filename.contains("..")) {
      throw new RuntimeException("Sorry! Filename contains invalid path sequence " + filename);
    }
  }
}
