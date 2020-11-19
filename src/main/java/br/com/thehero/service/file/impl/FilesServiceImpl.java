package br.com.thehero.service.file.impl;

import br.com.thehero.domain.model.Files;
import br.com.thehero.domain.model.Incidents;
import br.com.thehero.domain.repository.FilesRepository;
import br.com.thehero.domain.repository.IncidentsRepository;
import br.com.thehero.service.IncidentNotFoundException;
import br.com.thehero.service.file.FilesService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
@Slf4j
class FilesServiceImpl implements FilesService {

  private FilesRepository filesRepository;
  private IncidentsRepository incidentsRepository;

  public Files save(MultipartFile file, String uuidIncidents) {
    log.info("[BEGIN] save() - with filename [" + file.getOriginalFilename() + "]");
    return uploadFile(file, uuidIncidents);
  }

  private Files uploadFile(MultipartFile file, String uuidIncidents) {
    Incidents incidents =
        this.incidentsRepository
            .findById(UUID.fromString(uuidIncidents))
            .orElseThrow(
                () ->
                    new IncidentNotFoundException(
                        "Incident not found with UUID informed [" + uuidIncidents + "]"));
    try {
      String type = file.getContentType();
      String filename = file.getOriginalFilename();
      validateFilename(filename);

      incidents.setFiles(
          this.filesRepository.saveAndFlush(
              Files.newBuilder()
                  .filename(filename)
                  .type(type)
                  .incidents(incidents)
                  .data(file.getBytes())
                  .build()));

      this.incidentsRepository.save(incidents);
    } catch (IOException e) {
      log.error(
          "Fail when trying to upload the file [{}], error: {}", file.getName(), e.getMessage(), e);
    }
    return incidents.getFiles();
  }

  private void validateFilename(String filename) {
    if (filename.contains("..")) {
      throw new RuntimeException("Sorry! Filename contains invalid path sequence " + filename);
    }
  }
}
