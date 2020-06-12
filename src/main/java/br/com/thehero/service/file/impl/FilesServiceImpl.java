package br.com.thehero.service.file.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.thehero.domain.model.Files;
import br.com.thehero.domain.model.Files.FilesBuilder;
import br.com.thehero.domain.model.Organization;
import br.com.thehero.domain.repository.FilesRepository;
import br.com.thehero.domain.repository.OrganizationRepository;
import br.com.thehero.service.file.FilesService;

@Service
public class FilesServiceImpl implements FilesService {

	static final Logger LOG = Logger.getLogger(FilesServiceImpl.class);
	FilesRepository filesRepository;
	OrganizationRepository organizationRepository;

	public FilesServiceImpl(FilesRepository filesRepository, OrganizationRepository organizationRepository) {
		this.filesRepository = filesRepository;
		this.organizationRepository = organizationRepository;
	}

	public void save(MultipartFile files, String cnpjOrganization) {
		Arrays.asList(files).stream().map(file -> {
			try {
				LOG.info("[BEGIN] save() - with filename [" + file.getName() + "]");
				return uploadFile(file, cnpjOrganization);
			} catch (IOException e) {
				LOG.error("Persist file error: " + e.getMessage(), e);
			} finally {
				LOG.info("[END] save()");
			}
			return null;
		}).collect(Collectors.toList());
	}

	private Files uploadFile(MultipartFile file, String cnpjOrganization) throws IOException {
		byte[] data = file.getBytes();
		String filename = file.getName();
		String type = file.getContentType();
		Organization organization = organizationRepository.findByCnpj(cnpjOrganization).orElseThrow(
				() -> new RuntimeException("Organization not found with CNPJ informed [" + cnpjOrganization + "]"));
		if (filename.contains("..")) {
			throw new RuntimeException("Sorry! Filename contains invalid path sequence " + filename);
		}

		return filesRepository.saveAndFlush(FilesBuilder.newBuilder(data).withFilename(filename).withType(type)
				.withOrganization(organization).build());
	}
}
