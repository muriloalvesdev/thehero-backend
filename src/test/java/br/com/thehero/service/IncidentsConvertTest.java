package br.com.thehero.service;

import br.com.thehero.domain.model.Incidents;
import br.com.thehero.domain.model.Organization;
import br.com.thehero.dto.IncidentsDTO;
import br.com.thehero.service.convert.IncidentsConvert;
import org.junit.Assert;
import org.junit.Test;

public class IncidentsConvertTest {

    @Test public void convertDataTransferObjetToEntityTest() {
        Organization organization =
            Organization.OrganizationBuilder.newBuilder("Anjos de Patas LTDA")
                .withCity("Ribeirão Preto").withCnpj("59533195000188")
                .withEmail("contato@anjosdepatas.com.br").withUf("SP").withWhatsapp("11999999999")
                .build();
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
