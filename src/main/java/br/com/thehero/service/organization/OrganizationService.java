package br.com.thehero.service.organization;

import java.util.List;

import br.com.thehero.domain.model.Organization;
import javassist.NotFoundException;

public interface OrganizationService<D, S, T> {
  D create(D dto);

  void update(D dto);

  List<D> findAll();

  D findByCnpj(S cnpj) throws NotFoundException;

  void delete(S cnpj) throws NotFoundException;

  T findByEmail(S email) throws NotFoundException;
}
