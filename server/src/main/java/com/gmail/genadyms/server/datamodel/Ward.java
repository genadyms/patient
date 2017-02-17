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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((countBeds == null) ? 0 : countBeds.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ward other = (Ward) obj;
		if (countBeds == null) {
			if (other.countBeds != null)
				return false;
		} else if (!countBeds.equals(other.countBeds))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

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