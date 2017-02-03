package com.gmail.genadyms.web;

import com.gmail.genadyms.shared.PatientDTO;
import com.gmail.genadyms.shared.PatientDetails;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("patient")
public interface PatientService extends RemoteService {

	PatientDTO addPatient(PatientDTO patient);

	List<PatientDTO> getPatients();

	PatientDTO getPatient(Long id);

	PatientDTO updatePatient(PatientDTO patient);
}
