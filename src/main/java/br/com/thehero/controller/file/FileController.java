package br.com.thehero.controller.file;

import java.io.IOException;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.thehero.domain.model.Files;
import br.com.thehero.service.file.FilesService;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class FileController {

  FilesService<Files, MultipartFile, String> service;

  @PostMapping("/uploadFile/{incidentId}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> uploadFile(
      @RequestParam("file") MultipartFile file,
      @PathVariable(name = "incidentId", required = true) String incidentId)
      throws IOException {

    Files files = service.save(file, incidentId);
    return ResponseEntity.ok(
        ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/downloadFile/")
            .path(files.getUuid().toString())
            .toUriString());
  }
}
