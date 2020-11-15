package br.com.thehero.service.incidents;

import javassist.NotFoundException;

public interface IncidentsService<I, D, S, P, G> {
  I create(D incidentsDTO, S cnpjOrganization);

  P findAll(G pageable);

  void delete(S incidentId, S organizationId);

  D findById(S incidentsId) throws NotFoundException;
}
