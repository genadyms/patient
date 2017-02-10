package com.gmail.genadyms.server.service;

import com.gmail.genadyms.server.dataaccess.PatientDao;
import com.gmail.genadyms.server.dataaccess.WardDao;
import com.gmail.genadyms.server.datamodel.Patient;
import com.gmail.genadyms.shared.dto.PatientDTO;
import com.gmail.genadyms.web.service.PatientService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class PatientServiceImpl extends RemoteServiceServlet implements PatientService {
	private final PatientDao patientDao;
	private final WardDao wardDao;
//	private final List<PatientDTO> patientsData = new ArrayList<>();

	public PatientServiceImpl() {
		System.out.println("Constructor!!!");
		this.patientDao = new PatientDao();
		this.wardDao = new WardDao();
//		for (int i = 0; i < 10; i++) {
//            PatientDTO p = new PatientDTO();
//            p.setFirstName("firstName " + i);
//            p.setLastName("lastName " + i);
//            p.setDiagnosis("diagnosis " + i);
//            p.setComingDate(new Date());
//            p.setLeavingDate(new Date());
//            p.setWard(Long.valueOf(20 + i));
//            patientsData.add(p);
//        }
	}

	@Override
	public PatientDTO addPatient(PatientDTO patientDTO) {
		System.out.println(patientDTO);
		// Patient patient = toDAO(patientDTO);
		// patientDao.insert(patient);
		Patient db = patientDao.getAll().get(0);
		PatientDTO patient = toDTO(db);
		Patient p = new Patient();
		p.setAddress(patientDTO.getAddress());
		p.setFirstName(patientDTO.getFirstName());
		p.setLastName(patientDTO.getLastName());
		p.setComingDate(patientDTO.getComingDate());
		p.setDiagnosis(patientDTO.getDiagnosis());
		p.setWard(db.getWard());
		patientDao.insert(p);
		return patient;
	}

	@Override
	public List<PatientDTO> getPatients() {
		List<Patient> patienstDao = patientDao.getAll();
		List<PatientDTO> result = toDTO(patienstDao);
		patientDao.getEntityManager().clear();
		return result;
	}

    @Override
    public List<PatientDTO> getPatients(Integer start, Integer length) {
     System.out.println("Start is = "+start);
		System.out.println("Length is = "+length);
		List<Patient> res = patientDao.find(start, length);
		System.out.println("Length resuylt is = " + res.size()+res.toString());
		return toDTO(res);

//		return getPatients();
    }

    @Override
	public PatientDTO getPatient(Long id) {
		return toDTO(patientDao.get(id));
	}

	@Override
	public PatientDTO updatePatient(PatientDTO patientDTO) {
		Patient patient = toDAO(patientDTO);
		patientDao.update(patient);
		return toDTO(patient);
	}

	private List<PatientDTO> toDTO(List<Patient> patientsDao) {
		List<PatientDTO> resultDto = new ArrayList();
		for (Patient patient : patientsDao) {
			System.out.println("---- " + patient.getAddress());
			resultDto.add(toDTO(patient));
		}
		return resultDto;
	}

	private Patient toDAO(PatientDTO patientDTO) {
		Patient patient = new Patient();
		if (null != patientDTO.getId())
			patient.setId(patientDTO.getId());
		patient.setFirstName(patientDTO.getFirstName());
		patient.setLastName(patientDTO.getLastName());
		patient.setAddress(patientDTO.getAddress());
		patient.setDiagnosis(patientDTO.getDiagnosis());
		patient.setComingDate(patientDTO.getComingDate());
		patient.setLeavingDate(patientDTO.getLeavingDate());
		patient.setWard(wardDao.get(patientDTO.getWard()));
		return patient;
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
//		patientDao.getEntityManager().detach(patient);
		return dto;
	}
}
