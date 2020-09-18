package br.com.thehero.dto;

import javax.validation.constraints.NotNull;
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

}
