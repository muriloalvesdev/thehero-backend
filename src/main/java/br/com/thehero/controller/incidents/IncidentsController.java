package br.com.thehero.controller.incidents;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.thehero.domain.model.Incidents;
import br.com.thehero.dto.IncidentsDTO;
import br.com.thehero.service.incidents.IncidentsService;
import javassist.NotFoundException;

@CrossOrigin(origins = "*")
@RestController
public class IncidentsController {

  @Autowired
  private IncidentsService service;

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("incidents/{id}/{cnpj}")
  public ResponseEntity<Object> delete(
      @PathVariable(name = "id", required = true) String incidentsId,
      @PathVariable(value = "cnpj", required = true) String cnpjOrganization) {
    service.delete(incidentsId, cnpjOrganization);
    return ResponseEntity.noContent().build();
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("incidents/{id}")
  public ResponseEntity<IncidentsDTO> findById(
      @PathVariable(name = "id", required = true) String incidentsId) throws NotFoundException {
    return ResponseEntity.ok(service.findById(incidentsId));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("incidents/{cnpj}")
  public ResponseEntity<Object> create(@Validated @RequestBody IncidentsDTO dto,
      @PathVariable(name = "cnpj", required = true) String cnpjOrganization)
      throws NotFoundException {
    Incidents incidents = service.create(dto, cnpjOrganization);
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/incidents/{id}").buildAndExpand(incidents.getUuid()).toUri()).build();
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("incidents")
  public ResponseEntity<Page<IncidentsDTO>> findAll(Pageable pageable) {
    return ResponseEntity.ok(service.findAll(pageable));
  }

}
