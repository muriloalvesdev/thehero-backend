package br.com.thehero.controller.file;

import br.com.thehero.domain.model.Files;
import br.com.thehero.service.file.FilesService;
import lombok.AccessLevel;
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

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class FileController {

  private FilesService service;

  @PostMapping("/uploadFile/{incidentId}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> uploadFile(
      @RequestParam("file") MultipartFile file,
      @PathVariable(name = "incidentId") String incidentId){

    Files files = this.service.save(file, incidentId);
    return ResponseEntity.ok(
        ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/downloadFile/")
            .path(files.getUuid().toString())
            .toUriString());
  }
}
