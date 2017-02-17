package com.gmail.genadyms.server.service;

import com.gmail.genadyms.server.dataaccess.PatientDao;
import com.gmail.genadyms.server.dataaccess.WardDao;
import com.gmail.genadyms.server.datamodel.Patient;
import com.gmail.genadyms.server.datamodel.Ward;
import com.gmail.genadyms.shared.dto.WardDTO;
import com.gmail.genadyms.web.service.WardService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WardServiceImpl extends RemoteServiceServlet implements WardService {

	private final WardDao daoWard;
	private final PatientDao daoPatient;

	public WardServiceImpl() {
		daoPatient = new PatientDao();
		daoWard = new WardDao();
	}

	@Override
	public Set<WardDTO> getFreeWards() {
		List<Patient> patientsActive = daoPatient.findActivePatients();
		Map<Ward, Integer> wardsPatients = new HashMap();
		for (Patient patient : patientsActive) {
			Ward currentWard = patient.getWard();
			if (wardsPatients.containsKey(currentWard)) {
				Integer changeCountsPatients = wardsPatients.get(currentWard) + 1;
				wardsPatients.put(currentWard, changeCountsPatients);
		} else {
				wardsPatients.put(currentWard, 1);
			}
		}
		Set<WardDTO> wardsFree = new HashSet();
		for (Ward ward : wardsPatients.keySet()) {
			if (wardsPatients.get(ward) < ward.getCountBeds()) {
				WardDTO dtoWard = new WardDTO();
				dtoWard.setNumberWard(ward.getNumber());
				wardsFree.add(dtoWard);
			}
		}
		return wardsFree;
	}
}
