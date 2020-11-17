package br.com.thehero.service.file;

import br.com.thehero.domain.model.Files;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FilesService {
  Files save(MultipartFile files, String cnpjOrganization) throws IOException;
}
