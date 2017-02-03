package com.gmail.genadyms.server.service;

import com.gmail.genadyms.server.dataaccess.PatientDao;
import com.gmail.genadyms.server.dataaccess.WardDao;
import com.gmail.genadyms.server.datamodel.Patient;
import com.gmail.genadyms.shared.PatientDTO;
import com.gmail.genadyms.web.PatientService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class PatientServiceImpl extends RemoteServiceServlet implements PatientService {
	private final PatientDao patientDao;
	private final WardDao wardDao;

	public PatientServiceImpl() {
		this.patientDao = new PatientDao();
		this.wardDao = new WardDao();
	}

	@Override
	public PatientDTO addPatient(PatientDTO patientDTO) {
		Patient patient = toDAO(patientDTO);
		patientDao.insert(patient);
		return toDTO(patient);
	}

	@Override
	public List<PatientDTO> getPatients() {
		List<Patient> patienstDao = patientDao.getAll();
		return toDTO(patienstDao);
	}

	@Override
	public PatientDTO getPatient(Long id) {
		return toDTO(patientDao.get(id));
	}

	@Override
	public PatientDTO updatePatient(PatientDTO patientDTO) {
		patientDao.update(toDAO(patientDTO));
		return null;
	}

	private List<PatientDTO> toDTO(List<Patient> patientsDao) {
		List<PatientDTO> resultDto = new ArrayList();
		for (Patient patient : patientsDao) {
			resultDto.add(toDTO(patient));
		}
		return resultDto;
	}

	private Patient toDAO(PatientDTO patientDTO) {
		Patient patient = new Patient();
		patient.setId(patientDTO.getId());
		patient.setFirstName(patientDTO.getFirstName());
		patient.setLastName(patientDTO.getLastName());
		patient.setAddress(patientDTO.getAddress());
		patient.setDiagnosis(patientDTO.getDiagnosis());
		patient.setComingDate(patientDTO.getComingDate());
		patient.setLeavingDate(patientDTO.getLeavingDate());
		patient.setWard(wardDao.get(patientDTO.getWard()));
		return null;
	}

	private PatientDTO toDTO(Patient patient) {
		PatientDTO dto = new PatientDTO();
		dto.setId(patient.getId());
		dto.setFirstName(patient.getFirstName());
		dto.setLastName(patient.getLastName());
		dto.setAddress(patient.getAddress());
		dto.setDiagnosis(patient.getDiagnosis());
		dto.setComingDate(patient.getComingDate());
		dto.setLeavingDate(patient.getLeavingDate());
		dto.setWard(patient.getWard().getId());
		return dto;
	}
}
