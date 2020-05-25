package br.com.thehero.controller.incidents;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.thehero.domain.model.Incidents;
import br.com.thehero.dto.IncidentsDTO;
import br.com.thehero.service.incidents.IncidentsService;
import javassist.NotFoundException;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class IncidentsController {

	private IncidentsService service;

	public IncidentsController(IncidentsService service) {
		this.service = service;
	}

	@DeleteMapping("incidents/{id}/{cnpj}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> delete(@PathVariable(name = "id", required = true) String incidentsId,
			@PathVariable(value = "cnpj", required = true) String cnpjOrganization) {
		service.delete(incidentsId, cnpjOrganization);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("incidents/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<IncidentsDTO> findById(@PathVariable(name = "id", required = true) String incidentsId)
			throws NotFoundException {
		return ResponseEntity.ok(service.findById(incidentsId));
	}

	@PostMapping("incidents/{cnpj}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> create(@Validated @RequestBody IncidentsDTO dto,
			@PathVariable(name = "cnpj", required = true) String cnpjOrganization) {
		Incidents incidents = service.create(dto, cnpjOrganization);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath().path("/incidents/{id}")
				.buildAndExpand(incidents.getUuid()).toUri()).build();
	}

	@GetMapping("incidents")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Page<IncidentsDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok(service.findAll(pageable));
	}

}
