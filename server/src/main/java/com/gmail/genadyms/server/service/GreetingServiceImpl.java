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

	private Patient save(Patient patient) {
		this.dao.insert(patient);
		return patient;
	}

	private void testIt() {
		Long all = dao.count();
		if (null == all) {
			System.out.println("null!!!!!");

		} else {
			System.out.println(all);
		}
		Patient p = new Patient();
		p.setFirstName("fname12");
		p.setLastName("lname12");
		p.setMiddleName("mname2");
		p.setHomeAddress("my home address2");
		p.setDateOfBirth(new Date());

		save(p);
		Patient updtP = dao.get(19L);
		updtP.setMiddleName("isupdated");
		dao.update(updtP);

	}

	@Override
	public String greetServer(String name) throws IllegalArgumentException {
		testIt();
		return "hello world!";
	}
}