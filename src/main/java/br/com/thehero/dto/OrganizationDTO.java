package br.com.thehero.dto;

import javax.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CNPJ;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrganizationDTO {

  @JsonProperty("name")
  private String name;

  @Email(message = "email is not valid!")
  @JsonProperty("email")
  private String email;

  @JsonProperty("whatsapp")
  private String whatsapp;

  @JsonProperty("city")
  private String city;

  @JsonProperty("uf")
  private String uf;

  @JsonProperty("cnpj")
  @CNPJ(message = "cnpj is not valid!")
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
