package br.com.thehero.providers;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import br.com.thehero.IncidentsConstantsTest;
import br.com.thehero.OrganizationConstantsTest;
import br.com.thehero.dto.IncidentsDTO;

public class IncidentsDTOProviderTest
    implements ArgumentsProvider, OrganizationConstantsTest, IncidentsConstantsTest {
  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
    return Stream.of(
            IncidentsDTO.newBuilder()
                .city(OrganizationConstantsTest.CITY)
                .description(IncidentsConstantsTest.DESCRIPTION)
                .email(OrganizationConstantsTest.EMAIL_ONG)
                .mimeType(IncidentsConstantsTest.MIME_TYPE)
                .nameOrganization(OrganizationConstantsTest.NAME)
                .title(IncidentsConstantsTest.TITLE)
                .value(IncidentsConstantsTest.VALUE)
                .uf(OrganizationConstantsTest.UF)
                .whatsapp(OrganizationConstantsTest.WHATSAPP)
                .build())
        .map(Arguments::of);
  }
}
