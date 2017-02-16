package com.gmail.genadyms.server.service;

import com.gmail.genadyms.server.dataaccess.PatientDao;
import com.gmail.genadyms.server.dataaccess.WardDao;
import com.gmail.genadyms.server.datamodel.Patient;
import com.gmail.genadyms.server.datamodel.Ward;
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

    public PatientServiceImpl() {
        this.patientDao = new PatientDao();
        this.wardDao = new WardDao();
    }

    @Override
    public PatientDTO addPatient(PatientDTO patientDTO) {
        Patient p = new Patient();
        p.setAddress(patientDTO.getAddress());
        p.setFirstName(patientDTO.getFirstName());
        p.setLastName(patientDTO.getLastName());
        p.setComingDate(patientDTO.getComingDate());
        if (null != patientDTO.getLeavingDate()) {
            p.setLeavingDate(patientDTO.getLeavingDate());
        }
        p.setDiagnosis(patientDTO.getDiagnosis());
//        p.setWard(wardDao.findByNumberWard(patientDTO..getNumberWard()));
        patientDao.insert(p);
        return toDTO(p);
    }

    @Override
    public List<PatientDTO> getPatients() {
        List<Patient> patienstDao = patientDao.getAll();
        List<PatientDTO> result = toDTO(patienstDao);
        patientDao.getEntityManager().clear();
        return result;
    }

    @Override
    public Long getCountPatients() {
        return patientDao.count();
    }

    @Override
    public List<PatientDTO> getPatients(Integer start, Integer length) {
        List<Patient> res = patientDao.find(start, length);
        return toDTO(res);
    }

    @Override
    public PatientDTO getPatient(Long id) {
        return toDTO(patientDao.get(id));
    }

    @Override
    public PatientDTO updatePatient(PatientDTO patientDTO) {
        Patient patientEntity = toDAO(patientDTO);
        if (null == patientDTO.getId()) {
            patientDao.insert(patientEntity);
        } else {
            patientDao.update(patientEntity);
        }
        return toDTO(patientEntity);
    }

    private List<PatientDTO> toDTO(List<Patient> patientsDao) {
        List<PatientDTO> resultDto = new ArrayList();
        for (Patient patient : patientsDao) {
            resultDto.add(toDTO(patient));
        }
        return resultDto;
    }

    private Patient toDAO(PatientDTO patientDTO) {
        Patient patient = (null == patientDTO.getId()) ? new Patient() : patientDao.get(patientDTO.getId());
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setAddress(patientDTO.getAddress());
        patient.setDiagnosis(patientDTO.getDiagnosis());
        patient.setComingDate(patientDTO.getComingDate());
        patient.setLeavingDate(patientDTO.getLeavingDate());
        Ward ward = wardDao.findByNumberWard(patientDTO.getNumberWard());
        patient.setWard(ward);
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
        dto.setNumberWard(patient.getWard().getNumber());
        return dto;
    }
}
