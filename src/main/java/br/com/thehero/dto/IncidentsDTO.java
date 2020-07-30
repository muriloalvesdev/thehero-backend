package br.com.thehero.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IncidentsDTO {

  @JsonProperty("id")
  private String id;

  @JsonProperty("title")
  @NotNull
  private String title;

  @JsonProperty("description")
  @NotNull
  private String description;

  @JsonProperty("value")
  @NotNull
  private String value;

  @JsonProperty("name")
  private String nameOrganization;

  @JsonProperty("city")
  private String city;

  @JsonProperty("uf")
  private String uf;

  @JsonProperty("whatsapp")
  private String whatsapp;

  @JsonProperty("email")
  private String email;

  @JsonProperty("file_data")
  private byte[] fileData;

  @JsonProperty("mime_type")
  private String mimeType;

  public IncidentsDTO(@NotNull String title, @NotNull String description, @NotNull String value,
      String id, String nameOrganization, String city, String uf, String whatsapp, String email,
      byte[] fileData, String mimeType) {
    this.title = title;
    this.description = description;
    this.value = value;
    this.id = id;
    this.nameOrganization = nameOrganization;
    this.city = city;
    this.uf = uf;
    this.whatsapp = whatsapp;
    this.email = email;
    this.fileData = fileData;
    this.mimeType = mimeType;
  }

  public IncidentsDTO() {}

  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public byte[] getFileData() {
    return fileData;
  }

  public void setFileData(byte[] fileData) {
    this.fileData = fileData;
  }

  public String getWhatsapp() {
    return whatsapp;
  }

  public void setWhatsapp(String whatsapp) {
    this.whatsapp = whatsapp;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getUf() {
    return uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }

  public String getNameOrganization() {
    return nameOrganization;
  }

  public void setNameOrganization(String nameOrganization) {
    this.nameOrganization = nameOrganization;
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

}
