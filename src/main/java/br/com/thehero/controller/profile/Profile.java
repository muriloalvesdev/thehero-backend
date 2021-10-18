package br.com.thehero.controller.profile;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.thehero.dto.IncidentsDTOList;
import br.com.thehero.service.profile.ProfileService;
import javassist.NotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Profile {

  private final ProfileService service;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("profile/{cnpj}")
  public ResponseEntity<IncidentsDTOList> findIncidentsByOrganization(
      @PathVariable(name = "cnpj") String cnpj) throws NotFoundException {
    return ResponseEntity.ok(this.service.findIncidentsByOrganization(cnpj));
  }
}
