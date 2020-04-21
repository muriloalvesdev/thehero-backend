package br.com.thehero.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IncidentsDTO {

  @JsonProperty("title")
  private String title;

  @JsonProperty("description")
  private String description;

  @JsonProperty("value")
  private String value;

  @JsonProperty("cnpj_organization")
  private String cnpjOrgnaization;

}
