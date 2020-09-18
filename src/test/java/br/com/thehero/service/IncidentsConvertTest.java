package br.com.thehero.service;

import org.junit.Assert;
import org.junit.Test;
import br.com.thehero.domain.model.Incidents;
import br.com.thehero.domain.model.Organization;
import br.com.thehero.dto.IncidentsDTO;
import br.com.thehero.service.convert.IncidentsConvert;

public class IncidentsConvertTest {

  @Test
  public void convertDataTransferObjetToEntityTest() {
    Organization organization = Organization.newBuilder().name("Anjos de Patas LTDA")
        .city("Ribeirão Preto").cnpj("59533195000188").email("contato@anjosdepatas.com.br").uf("SP")
        .whatsapp("11999999999").build();
    final byte[] teste = new byte[0];
    IncidentsDTO incidentsDTO = new IncidentsDTO();
    incidentsDTO.setCity("cidade");
    incidentsDTO.setDescription("descricao");
    incidentsDTO.setEmail("teste@teste.com");
    incidentsDTO.setFileData(teste);
    incidentsDTO.setId("2");
    incidentsDTO.setMimeType(".png");
    incidentsDTO.setNameOrganization("nome organizacao");
    incidentsDTO.setTitle("titulo");
    incidentsDTO.setUf("SP");
    incidentsDTO.setWhatsapp("1111111111111");

    Incidents incidents =
        IncidentsConvert.convertDataTransferObjetToEntity(incidentsDTO, organization);

    Assert.assertEquals(incidents.getDescription(), incidentsDTO.getDescription());
    Assert.assertEquals(incidents.getTitle(), incidentsDTO.getTitle());
  }

}
