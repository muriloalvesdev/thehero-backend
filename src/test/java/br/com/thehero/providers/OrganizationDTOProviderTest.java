package br.com.thehero.providers;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import br.com.thehero.OrganizationConstantsTest;
import br.com.thehero.domain.model.Organization;
import br.com.thehero.dto.OrganizationDTO;
import br.com.thehero.service.convert.OrganizationConvert;

public class OrganizationDTOProviderTest implements ArgumentsProvider, OrganizationConstantsTest {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
    OrganizationDTO dto =
        OrganizationConvert.convertEntityToDataTransferObject(Organization.newBuilder().name(NAME)
            .city(CITY).cnpj(CNPJ).email(EMAIL_ONG).uf(UF).whatsapp(WHATSAPP).build());

    return Stream.of(dto).map(Arguments::of);
  }

}
