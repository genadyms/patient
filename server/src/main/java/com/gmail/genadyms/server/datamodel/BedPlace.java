package com.gmail.genadyms.server.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class BedPlace extends AbstractModel {
	private static final long serialVersionUID = 1L;

	@Column
	private Integer numberOfChamber;

	@Column
	private Integer numberOfBed;

	@OneToMany(mappedBy = "bedPlace", fetch = FetchType.LAZY)
	private List<PatientCard> patientCard;

	public Integer getNumberOfChamber() {
		return numberOfChamber;
	}

	public void setNumberOfChamber(Integer numberOfChamber) {
		this.numberOfChamber = numberOfChamber;
	}

	public Integer getNumberOfBed() {
		return numberOfBed;
	}

	public void setNumberOfBed(Integer numberOfBed) {
		this.numberOfBed = numberOfBed;
	}

	public List<PatientCard> getPatientCard() {
		return patientCard;
	}

	public void setPatientCard(List<PatientCard> patientCard) {
		this.patientCard = patientCard;
	}

}
