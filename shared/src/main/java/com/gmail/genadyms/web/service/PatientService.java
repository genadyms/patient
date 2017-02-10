package com.gmail.genadyms.web.service;

import com.gmail.genadyms.shared.dto.PatientDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("patient")
public interface PatientService extends RemoteService {

	PatientDTO addPatient(PatientDTO patient);

	List<PatientDTO> getPatients();

	Long getCountPatients();

	List<PatientDTO> getPatients(Integer start, Integer length);

	PatientDTO getPatient(Long id);

	PatientDTO updatePatient(PatientDTO patient);
}
