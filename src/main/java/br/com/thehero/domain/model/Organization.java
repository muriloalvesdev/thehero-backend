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
@Table(name = "organization",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "cnpj"})})
public class Organization extends BaseEntity {

  private static final long serialVersionUID = 6997726546986427669L;

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

  @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Incidents> incidents;

}
