package br.com.thehero.controller.file;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import br.com.thehero.service.file.FilesService;

@CrossOrigin(origins = "*")
@RestController
public class FileController {

  FilesService service;

  public FileController(FilesService service) {
    this.service = service;
  }

  @PostMapping("files/{cnpj}")
  public void save(@RequestBody MultipartFile file,
      @PathVariable(name = "cnpj") String cnpjOrganization) {
    service.save(file, cnpjOrganization);
  }
}
