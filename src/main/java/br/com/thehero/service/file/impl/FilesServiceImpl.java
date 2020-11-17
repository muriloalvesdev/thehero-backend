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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  public Files save(MultipartFile file, String uuidIncidents) throws IOException {
    return uploadFile(file, uuidIncidents);
  }

  private Files uploadFile(MultipartFile file, String uuidIncidents) throws IOException {
    log.info("[BEGIN] save() and uploadFile() - with filename [ {} ]", file.getName());
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
    log.info("[END] save() and uploadFile()");
    return files;
  }

  private void validateFilename(String filename) {
    if (filename.contains("..")) {
      throw new RuntimeException("Sorry! Filename contains invalid path sequence " + filename);
    }
  }
}
