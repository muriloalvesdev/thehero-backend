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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "incidents")
public class Incidents extends BaseEntity {

  private static final long serialVersionUID = -7504356832234307582L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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

  private Incidents(String title, String description, String value, Organization organization) {
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

  public static class IncidentsBuilder {
    private String title;
    private String description;
    private String value;
    private Organization organization;

    private IncidentsBuilder(Organization organization) {
      this.organization = organization;
    }

    public static IncidentsBuilder newBuilder(Organization organization) {
      return new IncidentsBuilder(organization);
    }

    public IncidentsBuilder withTitle(@NotNull String title) {
      this.title = title;
      return this;
    }

    public IncidentsBuilder withDescription(@NotNull String description) {
      this.description = description;
      return this;
    }

    public IncidentsBuilder withValue(@NotNull String value) {
      this.value = value;
      return this;
    }

    public Incidents build() {
      return new Incidents(title, description, value, organization);
    }
  }
}
