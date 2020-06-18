package br.com.thehero.controller.incidents;

import br.com.thehero.domain.model.Incidents;
import br.com.thehero.dto.IncidentsDTO;
import br.com.thehero.service.incidents.IncidentsService;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class IncidentsController {

  private final IncidentsService service;

  public IncidentsController(IncidentsService service) {
    this.service = service;
  }

  @GetMapping("incidents/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<IncidentsDTO> findById(@PathVariable(name = "id") String incidentsId)
      throws NotFoundException {
    return ResponseEntity.ok(service.findById(incidentsId));
  }

  @PostMapping("incidents/{cnpj}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<IncidentsDTO> create(
      @Validated @RequestBody IncidentsDTO dto,
      @PathVariable(name = "cnpj") String cnpjOrganization) {
    Incidents incidents = service.create(dto, cnpjOrganization);
    return new ResponseEntity(incidents.getUuid().toString(), HttpStatus.CREATED);
  }

  @GetMapping("incidents")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Page<IncidentsDTO>> findAll(Pageable pageable) {
    return ResponseEntity.ok(service.findAll(pageable));
  }
}
