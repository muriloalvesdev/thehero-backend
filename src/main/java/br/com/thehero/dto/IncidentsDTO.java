package br.com.thehero.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder")
public class IncidentsDTO {

  private String id;

  @NotNull private String title;

  @NotNull private String description;

  @NotNull private String value;

  private String nameOrganization;

  private String city;

  private String uf;

  private String whatsapp;

  private String email;

  private byte[] fileData;

  private String mimeType;
}
