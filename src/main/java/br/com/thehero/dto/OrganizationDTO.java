package br.com.thehero.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationDTO {

  @JsonProperty("name")
  private String name;

  @JsonProperty("email")
  private String email;

  @JsonProperty("whatsapp")
  private String whatsapp;

  @JsonProperty("city")
  private String city;

  @JsonProperty("uf")
  private String uf;

  @JsonProperty("cnpj")
  private String cnpj;

}
