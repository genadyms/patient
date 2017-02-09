package com.gmail.genadyms.web.service;

import com.gmail.genadyms.shared.dto.PatientDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface PatientServiceAsync {

    void addPatient(PatientDTO patient, AsyncCallback<PatientDTO> callback);

    void getPatients(AsyncCallback<List<PatientDTO>> callback);

    void getPatient(Long id, AsyncCallback<PatientDTO> callback);

    void updatePatient(PatientDTO patient, AsyncCallback<PatientDTO> callback);

    void getPatients(Integer start, Integer length, AsyncCallback<List<PatientDTO>> callback);
}