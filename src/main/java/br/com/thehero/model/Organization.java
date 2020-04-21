package br.com.thehero.model;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "organization", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@Getter
@Setter
@AllArgsConstructor
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

  @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER)
  private List<Incidents> incidents;

}
