package com.gmail.genadyms.web;

import com.gmail.genadyms.shared.PatientDTO;
import com.gmail.genadyms.shared.PatientDetails;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;

/**
 * Created by Геннадий on 03.02.2017.
 */
public interface PatientsServiceAsync {

    public void addPatient(PatientDTO contact, AsyncCallback<PatientDTO> callback);
    public void deletePatient(String id, AsyncCallback<Boolean> callback);
    public void deletePatients(ArrayList<String> ids, AsyncCallback<ArrayList<PatientDetails>> callback);
    public void getPatientDetails(AsyncCallback<ArrayList<PatientDetails>> callback);
    public void getPatient(String id, AsyncCallback<PatientDTO> callback);
    public void updatePatient(PatientDTO contact, AsyncCallback<PatientDTO> callback);
}