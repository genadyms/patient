package com.gmail.genadyms.server.datamodel;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Patient extends AbstractModel {
	private static final long serialVersionUID = 1L;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String homeAddress;
	@Column
	private String diagnosis;
	@Column
	private Date issueDate;
	@Column
	private Date diseaseDate;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getDiseaseDate() {
		return diseaseDate;
	}

	public void setDiseaseDate(Date diseaseDate) {
		this.diseaseDate = diseaseDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	 @ManyToOne(targetEntity = BedPlace.class, fetch = FetchType.LAZY)
	 private BedPlace bedPlace;

	public BedPlace getBedPlace() {
		return bedPlace;
	}

	public void setBedPlace(BedPlace bedPlace) {
		this.bedPlace = bedPlace;
	}

}
