package br.com.thehero.service.file;

import org.springframework.web.multipart.MultipartFile;

import br.com.thehero.domain.model.Files;

public interface FilesService<F, M, S> {
  F save(M files, S cnpjOrganization);
}
