package br.com.thehero.domain.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "incidents")
public class Incidents {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private UUID uuid;

  @Column
  private String title;

  @Column
  private String description;

  @Column
  private String value;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "organization_uuid", referencedColumnName = "uuid",
      foreignKey = @ForeignKey(name = "uuid"))
  private Organization organization;

  @SuppressWarnings("unused")
  private Incidents() {}

  public Incidents(String title, String description, String value, Organization organization) {
    this.title = title;
    this.description = description;
    this.value = value;
    this.organization = organization;
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

  public Organization getOrganization() {
    return organization;
  }

  public void setOrganization(Organization organization) {
    this.organization = organization;
  }

  public UUID getUuid() {
    return uuid;
  }

}
