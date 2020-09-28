package br.com.thehero.domain.model;

import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Entity
@Table(name = "incidents")
public class Incidents extends BaseEntity {

  private static final long serialVersionUID = -7504356832234307582L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid;

  @Column private String title;

  @Column private String description;

  @Column private String value;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(
      name = "organization_uuid",
      referencedColumnName = "uuid",
      foreignKey = @ForeignKey(name = "uuid"))
  private Organization organization;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  @JoinColumn(
      name = "files_uuid",
      referencedColumnName = "uuid",
      foreignKey = @ForeignKey(name = "uuid"))
  private Files files;

  @Enumerated(EnumType.STRING)
  private Status status;

  public boolean isAvailable() {
    return this.status.equals(Status.AVAILABLE);
  }

  public boolean isNotAvailable() {
    return this.status.equals(Status.NOT_AVAILABLE);
  }

  public enum Status {
    AVAILABLE,
    NOT_AVAILABLE;
  }
}
