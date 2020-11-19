package br.com.thehero.service.profile;

import br.com.thehero.dto.IncidentsDTOList;
import javassist.NotFoundException;

public interface ProfileService {

  IncidentsDTOList findIncidentsByOrganization(String cnpj) throws NotFoundException;
}
