package br.com.thehero.domain.model;

import java.time.LocalDateTime;
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "files")
public class Files extends BaseEntity {

  private static final long serialVersionUID = -2488282648212980416L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid;

  @Column(name = "created_at")
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
  protected LocalDateTime createdAt;

  @Column(name = "updated_at")
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
  protected LocalDateTime updatedAt;

  private String filename;

  private byte[] data;

  private String type;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "organization_uuid", referencedColumnName = "uuid",
      foreignKey = @ForeignKey(name = "uuid"))
  private Organization organization;

  @SuppressWarnings("unused")
  private Files() {}

  public Files(String filename, byte[] data, String type, Organization organization) {
    this.filename = filename;
    this.data = data;
    this.type = type;
    this.organization = organization;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return this.updatedAt == null ? getCreatedAt() : this.updatedAt;
  }


  public static class FilesBuilder {
    private String filename;
    private byte[] data;
    private String type;
    private Organization organization;

    private FilesBuilder(byte[] data) {
      this.data = data;
    }

    public static FilesBuilder newBuilder(byte[] data) {
      return new FilesBuilder(data);
    }

    public FilesBuilder withFilename(String filename) {
      this.filename = filename;
      return this;
    }

    public FilesBuilder withType(String type) {
      this.type = type;
      return this;
    }

    public FilesBuilder withOrganization(Organization organization) {
      this.organization = organization;
      return this;
    }

    public Files build() {
      return new Files(filename, data, type, organization);
    }
  }
}
