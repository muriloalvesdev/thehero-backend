package br.com.thehero.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder")
public class OrganizationDTO {

  @JsonProperty("name")
  @NotNull(message = "name is not valid!")
  private String name;

  @Email(message = "email is not valid!")
  @NotNull(message = "email is not valid!")
  @JsonProperty("email")
  private String email;

  @JsonProperty("whatsapp")
  @NotNull(message = "whatsapp is not valid!")
  private String whatsapp;

  @JsonProperty("city")
  @NotNull(message = "city is not valid!")
  private String city;

  @JsonProperty("uf")
  @NotNull(message = "uf is not valid!")
  private String uf;

  @JsonProperty("cnpj")
  @CNPJ(message = "cnpj is not valid!")
  @NotNull(message = "cnpj is not valid!")
  private String cnpj;

}
