package br.com.thehero.model;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "incidents")
@Getter
@Setter
@AllArgsConstructor
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


}
