package br.com.thehero.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IncidentsDTO {

  @JsonProperty("id")
  private String id;

  @JsonProperty("title")
  private String title;

  @JsonProperty("description")
  private String description;

  @JsonProperty("value")
  private String value;

  @JsonProperty("cnpj_organization")
  private String cnpjOrgnaization;

  public IncidentsDTO(String title, String description, String value, String cnpjOrgnaization) {
    this.title = title;
    this.description = description;
    this.value = value;
    this.cnpjOrgnaization = cnpjOrgnaization;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getCnpjOrgnaization() {
    return cnpjOrgnaization;
  }

  public void setCnpjOrgnaization(String cnpjOrgnaization) {
    this.cnpjOrgnaization = cnpjOrgnaization;
  }

}
