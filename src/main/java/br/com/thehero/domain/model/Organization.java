package br.com.thehero.domain.model;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "organization",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "cnpj"})})
public class Organization {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private UUID uuid;

  @Column
  private String name;

  @Column
  private String email;

  @Column
  private String whatsapp;

  @Column
  private String city;

  @Column
  private String uf;

  @Column
  private String cnpj;

  @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER, orphanRemoval = true)
  private List<Incidents> incidents;

  @SuppressWarnings("unused")
  private Organization() {}

  public Organization(String name, String email, String whatsapp, String city, String uf,
      String cnpj) {
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

  public List<Incidents> getIncidents() {
    return incidents;
  }

  public void setIncidents(List<Incidents> incidents) {
    this.incidents = incidents;
  }

  public UUID getUuid() {
    return uuid;
  }

}
