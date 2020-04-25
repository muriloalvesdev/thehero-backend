package br.com.thehero.controller.organization;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

  @Autowired
  private OrganizationService service;

  @GetMapping("/ongs")
  public ResponseEntity<List<OrganizationDTO>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @PostMapping("/ongs")
  public ResponseEntity<OrganizationDTO> create(
      @Validated @RequestBody OrganizationDTO organizationDTO) {
    Organization organization = service.create(organizationDTO);

    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/ongs/{cnpj}").buildAndExpand(organization.getCnpj()).toUri()).build();
  }

  @GetMapping("/ongs/{cnpj}")
  public ResponseEntity<OrganizationDTO> findByCnpj(
      @PathVariable(required = true, name = "cnpj") String cnpj) throws NotFoundException {
    return ResponseEntity.ok(service.findByCnpj(cnpj));
  }

  @PutMapping("/ongs")
  public ResponseEntity<Object> update(@Validated @RequestBody OrganizationDTO organizationDTO) {
    service.update(organizationDTO);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/ongs/{cnpj}")
  public ResponseEntity<Void> delete(@PathVariable(name = "cnpj") String cnpj)
      throws NotFoundException {
    service.delete(cnpj);
    return ResponseEntity.noContent().build();
  }
}
