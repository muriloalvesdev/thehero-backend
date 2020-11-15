package br.com.thehero.service.profile;

public interface ProfileService<I, S> {

    I findIncidentsByOrganization(S cnpj);
}
