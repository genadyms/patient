package com.gmail.genadyms.shared.dto;

import java.io.Serializable;
import java.util.Date;

public class PatientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String diagnosis;
	private Date leavingDate;
	private Date comingDate;
	@Override
	public String toString() {
		return "PatientDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", diagnosis=" + diagnosis + ", leavingDate=" + leavingDate + ", comingDate=" + comingDate + ", ward="
				+ ward + "]";
	}

	private Long ward;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWard() {
		return ward;
	}

	public void setWard(Long ward) {
		this.ward = ward;
	}

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

	public void setAddress(String address) {
		this.address = address;
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

	public void setComingDate(Date comingDate) {
		this.comingDate = comingDate;
	}

}
