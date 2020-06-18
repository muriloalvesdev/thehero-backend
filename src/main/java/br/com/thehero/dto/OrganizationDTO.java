package br.com.thehero.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrganizationDTO {

  @NotNull(message = "name is not valid!")
  private String name;

  @Email(message = "email is not valid!")
  @NotNull(message = "email is not valid!")
  private String email;

  @NotNull(message = "whatsapp is not valid!")
  private String whatsapp;

  @NotNull(message = "city is not valid!")
  private String city;

  @NotNull(message = "uf is not valid!")
  private String uf;

  @CNPJ(message = "cnpj is not valid!")
  @NotNull(message = "cnpj is not valid!")
  private String cnpj;

  public OrganizationDTO(String name, @Email(message = "email is not valid!") String email,
      String whatsapp, String city, String uf, @CNPJ(message = "cnpj is not valid!") String cnpj) {
    this.name = name;
    this.email = email;
    this.whatsapp = whatsapp;
    this.city = city;
    this.uf = uf;
    this.cnpj = cnpj;
  }

  public OrganizationDTO() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getWhatsapp() {
    return whatsapp;
  }

  public void setWhatsapp(String whatsapp) {
    this.whatsapp = whatsapp;
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

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

}
