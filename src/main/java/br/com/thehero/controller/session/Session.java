package br.com.thehero.controller.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.thehero.dto.OrganizationDTO;
import br.com.thehero.service.organization.OrganizationService;
import javassist.NotFoundException;

@CrossOrigin(origins = "*")
@RestController
public class Session {

  @Autowired
  private OrganizationService service;

  @PostMapping("/session")
  public ResponseEntity<OrganizationDTO> create(@RequestBody(required = true) String cnpj)
      throws NotFoundException {
    return ResponseEntity.ok(service.findByCnpj(cnpj));
  }

}
