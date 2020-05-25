package br.com.thehero.service.profile;

import static org.mockito.Matchers.anyByte;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import br.com.thehero.domain.model.Organization.OrganizationBuilder;
import br.com.thehero.domain.repository.IncidentsRepository;
import br.com.thehero.domain.repository.OrganizationRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceTest {

	private ProfileService service;

	@Mock
	private OrganizationRepository organizationRepository;

	@Mock
	private IncidentsRepository incidentsRepository;

	private RestTemplate template;
	
	@Before
	public void before() {
		service = new ProfileService(organizationRepository, incidentsRepository);
	}

	@Test
	public void findIncidentsByOrganization() {
		OrganizationBuilder.newBuilder("Anjos de Patas LTDA").withCity("Ribeirão Preto").withCnpj("59533195000188")
				.withEmail("contato@anjosdepatas.com.br").withUf("SP").withWhatsapp("11999999999").build();

		 
		
	}

}
