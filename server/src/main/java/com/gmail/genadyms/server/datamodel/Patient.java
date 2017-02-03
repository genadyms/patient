package com.gmail.genadyms.server.datamodel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Patient extends AbstractModel {
	private static final long serialVersionUID = 1L;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String address;
	@Column
	private String diagnosis;
	@Column
	private Date leavingDate;
	@Column
	private Date comingDate;

	@ManyToOne(targetEntity = Ward.class, fetch = FetchType.LAZY)
	private Ward ward;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String homeAddress) {
		this.address = homeAddress;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Date getLeavingDate() {
		return leavingDate;
	}

	public void setLeavingDate(Date leavingDate) {
		this.leavingDate = leavingDate;
	}

	public Date getComingDate() {
		return comingDate;
	}

	public void setComingDate(Date diseaseDate) {
		this.comingDate = diseaseDate;
	}

	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward bedPlace) {
		this.ward = bedPlace;
	}

	@Override
	public String toString() {
		return "Patient [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", diagnosis="
				+ diagnosis + ", leavingDate=" + leavingDate + ", comingDate=" + comingDate + "]";
	}

}
