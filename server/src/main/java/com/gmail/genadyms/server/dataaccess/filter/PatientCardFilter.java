package com.gmail.genadyms.server.dataaccess.filter;

import com.gmail.genadyms.server.datamodel.BedPlace;
import com.gmail.genadyms.server.datamodel.Patient;

public class PatientCardFilter extends AbstractFilter {

    private boolean isFetchPatients;
    private boolean isFetchBedPlaces;
    private boolean isPatientCardActive;
    private Patient patient;
    private BedPlace bedPlace;

    public boolean isFetchPatients() {
        return isFetchPatients;
    }

    public void setFetchPatients(boolean fetchPatients) {
        isFetchPatients = fetchPatients;
    }

    public boolean isFetchBedPlaces() {
        return isFetchBedPlaces;
    }

    public void setFetchBedPlaces(boolean fetchBedPlaces) {
        isFetchBedPlaces = fetchBedPlaces;
    }

    public boolean isPatientCardActive() {
        return isPatientCardActive;
    }

    public void setPatientCardActive(boolean patientCardActive) {
        isPatientCardActive = patientCardActive;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public BedPlace getBedPlace() {
        return bedPlace;
    }

    public void setBedPlace(BedPlace bedPlace) {
        this.bedPlace = bedPlace;
    }
}