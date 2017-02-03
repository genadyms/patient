package com.gmail.genadyms.server.datamodel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class PatientCard extends AbstractModel {

    @ManyToOne(targetEntity = Patient.class, fetch = FetchType.LAZY)
    private Patient patient;
    @Column
    private String diagnosis;
    @Column
    private Date issueDate;
    @Column
    private Date diseaseDate;

    @ManyToOne(targetEntity = BedPlace.class, fetch = FetchType.LAZY)
    private BedPlace bedPlace;

    public Patient getPatient() {
        return patient;
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

    public BedPlace getBedPlace() {
        return bedPlace;
    }

    public void setBedPlace(BedPlace bedPlace) {
        this.bedPlace = bedPlace;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
