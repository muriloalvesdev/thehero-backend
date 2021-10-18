package br.com.thehero.controller.incidents;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import br.com.thehero.domain.model.Incidents;
import br.com.thehero.dto.IncidentsDTO;
import br.com.thehero.service.incidents.IncidentsService;
import javassist.NotFoundException;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class IncidentsController {

  private final IncidentsService service;

  @DeleteMapping("incidents/{id}/{cnpj}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Object> delete(
      @PathVariable(name = "id") String incidentsId,
      @PathVariable(value = "cnpj") String cnpjOrganization) {
    this.service.delete(incidentsId, cnpjOrganization);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("incidents/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<IncidentsDTO> findById(@PathVariable(name = "id") String incidentsId)
      throws NotFoundException {
    return ResponseEntity.ok(service.findById(incidentsId));
  }

  @PostMapping("incidents/{cnpj}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Object> create(
      @Validated @RequestBody IncidentsDTO incidentsDTO,
      @PathVariable(name = "cnpj") String cnpjOrganization) {
    Incidents incidents = this.service.create(incidentsDTO, cnpjOrganization);
    return new ResponseEntity<>(incidents.getUuid().toString(), HttpStatus.CREATED);
  }

  @GetMapping("incidents")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Page<IncidentsDTO>> findAll(Pageable pageable) {
    return ResponseEntity.ok(this.service.findAll(pageable));
  }
}
