package com.gmail.genadyms.server.service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.gmail.genadyms.server.dataaccess.PatientDao;
import com.gmail.genadyms.server.datamodel.Patient;
import com.gmail.genadyms.shared.FieldVerifier;
import com.gmail.genadyms.web.GreetingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")

public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	private PatientDao dao;

	public GreetingServiceImpl() {
		this.dao = new PatientDao();
	}

	public Patient save(Patient patient) {
		this.dao.insert(patient);
		return patient;
	}

	public void delete(Patient patient) {
		this.dao.delete(patient.getId());
	}

	public Patient getPatient(Long id) {
		return this.dao.get(id);
	}
	@Override
	public String greetServer(String name) throws IllegalArgumentException {
		return "hello world!";
	}
}