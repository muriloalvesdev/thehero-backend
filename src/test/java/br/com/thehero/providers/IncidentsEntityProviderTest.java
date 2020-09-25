package br.com.thehero.providers;

import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import br.com.thehero.IncidentsConstantsTest;
import br.com.thehero.OrganizationConstantsTest;
import br.com.thehero.domain.model.Incidents;
import br.com.thehero.domain.model.Incidents.Status;
import br.com.thehero.domain.model.Organization;

public class IncidentsEntityProviderTest
    implements ArgumentsProvider, OrganizationConstantsTest, IncidentsConstantsTest {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
    return Stream
        .of(Incidents.newBuilder().description(DESCRIPTION)
            .organization(Organization.newBuilder().uuid(UUID.randomUUID()).name(NAME).city(CITY)
                .cnpj(CNPJ).email(EMAIL_ONG).uf(UF).whatsapp(WHATSAPP).build())
            .status(Status.AVAILABLE).title(TITLE).uuid(UUID.randomUUID()).value("150.00").build())
        .map(Arguments::of);
  }

}
