package br.com.thehero.service.file.impl;

import java.io.IOException;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.thehero.domain.model.Files;
import br.com.thehero.domain.model.Files.FilesBuilder;
import br.com.thehero.domain.model.Incidents;
import br.com.thehero.domain.repository.FilesRepository;
import br.com.thehero.domain.repository.IncidentsRepository;
import br.com.thehero.service.file.FilesService;

@Service
public class FilesServiceImpl implements FilesService {

  static final Logger LOG = Logger.getLogger(FilesServiceImpl.class);
  FilesRepository filesRepository;
  IncidentsRepository incidentsRepository;

  public FilesServiceImpl(FilesRepository filesRepository,
      IncidentsRepository incidentsRepository) {
    this.filesRepository = filesRepository;
    this.incidentsRepository = incidentsRepository;
  }

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

    Incidents incidents = incidentsRepository.findById(UUID.fromString(uuidIncidents))
        .orElseThrow(() -> new RuntimeException(
            "Incident not found with UUID informed [" + uuidIncidents + "]"));

    Files files = filesRepository.saveAndFlush(FilesBuilder.newBuilder(data).withFilename(filename)
        .withType(type).withIncidents(incidents).build());
    incidents.setFiles(files);
    incidentsRepository.saveAndFlush(incidents);
    return files;
  }

  private void validateFilename(String filename) {
    if (filename.contains("..")) {
      throw new RuntimeException("Sorry! Filename contains invalid path sequence " + filename);
    }
  }
}
