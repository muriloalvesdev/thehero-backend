package br.com.thehero.controller.organization;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.thehero.domain.model.Organization;
import br.com.thehero.dto.OrganizationDTO;
import br.com.thehero.service.organization.OrganizationService;
import javassist.NotFoundException;

@CrossOrigin(origins = "*")
@RestController
public class OrganizationController {

  private OrganizationService service;

  public OrganizationController(OrganizationService service) {
    this.service = service;
  }

  @GetMapping("/ongs")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<OrganizationDTO>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @PostMapping("/ongs")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<OrganizationDTO> create(
      @Validated @RequestBody OrganizationDTO organizationDTO) {
    Organization organization = service.create(organizationDTO);

    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/ongs/{cnpj}").buildAndExpand(organization.getCnpj()).toUri()).build();
  }

  @GetMapping("/ongs/{cnpj}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<OrganizationDTO> findByCnpj(
      @PathVariable(required = true, name = "cnpj") String cnpj) throws NotFoundException {
    return ResponseEntity.ok(service.findByCnpj(cnpj));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/ongs")
  public ResponseEntity<Object> update(@Validated @RequestBody OrganizationDTO organizationDTO) {
    service.update(organizationDTO);
    return ResponseEntity.ok().build();
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/ongs/{cnpj}")
  public ResponseEntity<Void> delete(@PathVariable(name = "cnpj") String cnpj)
      throws NotFoundException {
    service.delete(cnpj);
    return ResponseEntity.noContent().build();
  }
}
