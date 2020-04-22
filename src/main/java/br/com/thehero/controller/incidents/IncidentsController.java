package br.com.thehero.controller.incidents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

  @DeleteMapping("incidents/{id}")
  public ResponseEntity<Object> delete(
      @PathVariable(name = "id", required = true) String incidentsId,
      @RequestHeader(value = "authorization", required = true) String cnpjOrganization) {
    service.delete(incidentsId, cnpjOrganization);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("incidents/{id}")
  public ResponseEntity<Object> findById(
      @PathVariable(name = "id", required = true) String incidentsId) throws NotFoundException {
    service.findById(incidentsId);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("incidents")
  public ResponseEntity<Object> create(@Validated @RequestBody IncidentsDTO dto,
      @RequestHeader(value = "authorization", required = true) String cnpjOrganization)
      throws NotFoundException {
    Incidents incidents = service.create(dto, cnpjOrganization);
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/incidents/{id}").buildAndExpand(incidents.getUuid()).toUri()).build();
  }

  @GetMapping("incidents/")
  public ResponseEntity<Page<IncidentsDTO>> findAll(Pageable pageable) {
    return ResponseEntity.ok(service.findAll(pageable));
  }

}
