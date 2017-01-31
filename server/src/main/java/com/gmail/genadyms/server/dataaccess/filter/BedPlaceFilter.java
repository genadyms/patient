package com.gmail.genadyms.server.dataaccess.filter;

import java.util.List;

public class BedPlaceFilter extends AbstractFilter {

	private int numberOfChamber;
	private int numberOfBed;

	public int getNumberOfChamber() {
		return numberOfChamber;
	}

	public void setNumberOfChamber(int numberOfChamber) {
		this.numberOfChamber = numberOfChamber;
	}

	public int getNumberOfBed() {
		return numberOfBed;
	}

	public void setNumberOfBed(int numberOfBed) {
		this.numberOfBed = numberOfBed;
	}

	List<Long> listActivePatientCards;

	public List<Long> getListActivePatientCards() {
		return listActivePatientCards;
	}

	public void setListActivePatientCards(List<Long> listActivePatientCards) {
		this.listActivePatientCards = listActivePatientCards;
	}

}