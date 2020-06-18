package br.com.thehero.service.incidents.impl;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.thehero.domain.model.Incidents;
import br.com.thehero.domain.model.Organization;
import br.com.thehero.domain.repository.FilesRepository;
import br.com.thehero.domain.repository.IncidentsRepository;
import br.com.thehero.domain.repository.OrganizationRepository;
import br.com.thehero.dto.IncidentsDTO;
import br.com.thehero.service.convert.IncidentsConvert;
import br.com.thehero.service.incidents.IncidentsService;
import javassist.NotFoundException;

@Service
public class IncidentsServiceImpl implements IncidentsService {

	IncidentsRepository incidentsRepository;
	OrganizationRepository organizationRepository;
	FilesRepository filesRepository;

	public IncidentsServiceImpl(IncidentsRepository incidentsRepository, OrganizationRepository organizationRepository,
			FilesRepository filesRepository) {
		this.incidentsRepository = incidentsRepository;
		this.organizationRepository = organizationRepository;
		this.filesRepository = filesRepository;
	}

	public Incidents create(IncidentsDTO dto, String cnpjOrganization) {
		Optional<Organization> optionaOrganization = organizationRepository.findByCnpj(cnpjOrganization);
		if (optionaOrganization.isPresent()) {
			Organization organization = optionaOrganization.get();
			Incidents incidents = IncidentsConvert.convertDataTransferObjetToEntity(dto, organization);

			return incidentsRepository.saveAndFlush(incidents);

		} else {
			throw new RuntimeException("Não existe uma organização com o CNPJ informado.");
		}
	}

	public Page<IncidentsDTO> findAll(Pageable pageable) {
		return incidentsRepository.findAll(pageable)
				.map(incident -> IncidentsConvert.convertEntityToDataTransferObject(incident));

	}

	public IncidentsDTO findById(String incidentsId) throws NotFoundException {
		Optional<Incidents> incidentOptional = incidentsRepository.findById(UUID.fromString(incidentsId));

		if (incidentOptional.isPresent()) {
			Incidents incidents = incidentOptional.get();
			return IncidentsConvert.convertEntityToDataTransferObject(incidents);
		} else {
			throw new NotFoundException("Não existe um incidente com o ID informado");
		}
	}

}
