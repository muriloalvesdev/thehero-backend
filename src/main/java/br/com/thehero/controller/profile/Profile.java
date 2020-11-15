package br.com.thehero.controller.profile;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.thehero.dto.IncidentsDTOList;
import br.com.thehero.service.profile.impl.ProfileServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class Profile {

  private ProfileServiceImpl service;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("profile/{cnpj}")
  public ResponseEntity<IncidentsDTOList> findIncidentsByOrganization(
      @PathVariable(name = "cnpj", required = true) String cnpj) {
    return ResponseEntity.ok(service.findIncidentsByOrganization(cnpj));
  }
}
