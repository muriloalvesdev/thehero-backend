package br.com.thehero.providers;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import br.com.thehero.OrganizationConstantsTest;
import br.com.thehero.domain.model.Organization;

public class OrganizationEntityProviderTest
    implements ArgumentsProvider, OrganizationConstantsTest {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
    return Stream.of(
            Organization.newBuilder()
                .name(NAME)
                .city(CITY)
                .cnpj(CNPJ)
                .email(EMAIL_ONG)
                .uf(UF)
                .whatsapp(WHATSAPP)
                .build())
        .map(Arguments::of);
  }
}
