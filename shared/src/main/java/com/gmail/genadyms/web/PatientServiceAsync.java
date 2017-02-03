package com.gmail.genadyms.web;

import com.gmail.genadyms.shared.PatientDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface PatientServiceAsync {

	public void addPatient(PatientDTO patient, AsyncCallback<PatientDTO> callback);

	public void getPatients(AsyncCallback<List<PatientDTO>> callback);

	public void getPatient(Long id, AsyncCallback<PatientDTO> callback);

	public void updatePatient(PatientDTO patient, AsyncCallback<PatientDTO> callback);
}