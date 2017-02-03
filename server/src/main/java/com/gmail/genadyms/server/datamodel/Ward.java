package com.gmail.genadyms.server.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Ward extends AbstractModel {

	private static final long serialVersionUID = 1L;

	@Column
	private Integer number;

	@Column
	private Integer countBeds;

	@OneToMany(mappedBy = "ward", fetch = FetchType.LAZY)
	private List<Patient> patients;

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer numberOfChamber) {
		this.number = numberOfChamber;
	}

	public Integer getCountBeds() {
		return countBeds;
	}

	public void setCountBeds(Integer numberOfBed) {
		this.countBeds = numberOfBed;
	}

	@Override
	public String toString() {
		return "Ward [number=" + number + ", countBeds=" + countBeds + "]";
	}

}