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
import javax.validation.constraints.Email;

import com.sun.istack.NotNull;

@Entity
@Table(name = "organization", uniqueConstraints = { @UniqueConstraint(columnNames = { "email", "cnpj" }) })
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

	@SuppressWarnings("unused")
	private Organization() {
	}

	private Organization(String name, String email, String whatsapp, String city, String uf, String cnpj) {
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

	public static class OrganizationBuilder {
		private String name;
		private String email;
		private String whatsapp;
		private String city;
		private String uf;
		private String cnpj;

		public static OrganizationBuilder newBuilder(@NotNull String name) {
			return new OrganizationBuilder(name);
		}

		private OrganizationBuilder(@NotNull String name) {
			this.name = name;
		}

		public OrganizationBuilder withEmail(@Email String email) {
			this.email = email;
			return this;
		}

		public OrganizationBuilder withWhatsapp(@NotNull String whatsapp) {
			this.whatsapp = whatsapp;
			return this;
		}

		public OrganizationBuilder withCity(@NotNull String city) {
			this.city = city;
			return this;
		}

		public OrganizationBuilder withUf(@NotNull String uf) {
			this.uf = uf;
			return this;
		}

		public OrganizationBuilder withCnpj(@NotNull String cnpj) {
			this.cnpj = cnpj;
			return this;
		}

		public Organization build() {
			return new Organization(name, email, whatsapp, city, uf, cnpj);
		}
	}

}
