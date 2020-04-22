package br.com.thehero.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import br.com.thehero.dto.IncidentsDTOList;
import br.com.thehero.service.profile.ProfileService;

@CrossOrigin(origins = "*")
@RestController
public class Profile {

  @Autowired
  private ProfileService service;

  @GetMapping("/profile")
  public ResponseEntity<IncidentsDTOList> findIncidentsByOrganization(
      @RequestHeader(value = "authorization", required = true) String cnpj) {
    return ResponseEntity.ok(service.findIncidentsByOrganization(cnpj));
  }
}
