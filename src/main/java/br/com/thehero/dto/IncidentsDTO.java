package br.com.thehero.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IncidentsDTO {

	private String id;

	@NotNull
	private String title;

	@NotNull
	private String description;

	@NotNull
	private String value;

	@JsonProperty("name")
	private String nameOrganization;

	private String city;

	private String uf;

	private String whatsapp;

	@Email(message = "email is not valid!")
	private String email;

	@JsonProperty("file_data")
	private byte[] fileData;

	@JsonProperty("mime_type")
	private String mimeType;

	public IncidentsDTO(@NotNull String title, @NotNull String description, @NotNull String value, String id,
			String nameOrganization, String city, String uf, String whatsapp, String email, byte[] fileData,
			String mimeType) {
		this.title = title;
		this.description = description;
		this.value = value;
		this.id = id;
		this.nameOrganization = nameOrganization;
		this.city = city;
		this.uf = uf;
		this.whatsapp = whatsapp;
		this.email = email;
		this.fileData = fileData;
		this.mimeType = mimeType;
	}

	public IncidentsDTO() {
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getNameOrganization() {
		return nameOrganization;
	}

	public void setNameOrganization(String nameOrganization) {
		this.nameOrganization = nameOrganization;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}
