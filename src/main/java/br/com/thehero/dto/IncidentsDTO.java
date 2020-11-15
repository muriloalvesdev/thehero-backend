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

  private String id;

  @NotNull
  private String title;

  @NotNull
  private String description;

  @NotNull
  private String value;

  @JsonProperty("name")
  private String nameOrganization;

  private String city;

  private String uf;

  private String whatsapp;

  private String email;

  @JsonProperty("file_data")
  private byte[] fileData;

  @JsonProperty("mime_type")
  private String mimeType;
}
