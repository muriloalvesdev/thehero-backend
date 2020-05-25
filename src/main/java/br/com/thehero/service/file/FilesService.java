package br.com.thehero.service.file;

import org.springframework.web.multipart.MultipartFile;

public interface FilesService {
	void save(MultipartFile files, String cnpjOrganization);
}
