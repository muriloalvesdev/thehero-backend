package br.com.thehero.controller.profile;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import br.com.thehero.dto.IncidentsDTO;
import br.com.thehero.service.profile.ProfileService;

@RestController
public class Profile {

  @Autowired
  private ProfileService service;
  
  @GetMapping("/profile")
  public ResponseEntity<List<IncidentsDTO>> findIncidentsByOrganization(
      @RequestHeader(value = "authorization", required = true) String organizationId) {
    return ResponseEntity.ok(service.findIncidentsByOrganization(organizationId));
  }
}
