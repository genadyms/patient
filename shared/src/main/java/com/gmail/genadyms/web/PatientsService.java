package com.gmail.genadyms.web;

import com.gmail.genadyms.shared.PatientDTO;
import com.gmail.genadyms.shared.PatientDetails;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;

@RemoteServiceRelativePath("patient")
public interface PatientsService extends RemoteService {

    PatientDTO addPatient(PatientDTO contact);

    Boolean deletePatient(String id);

    ArrayList<PatientDetails> deletePatients(ArrayList<String> ids);

    ArrayList<PatientDetails> getPatientDetails();

    PatientDTO getPatient(String id);

    PatientDTO updatePatient(PatientDTO contact);
}
