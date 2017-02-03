package com.gmail.genadyms.server.dataaccess;

import org.junit.Test;

import com.gmail.genadyms.server.dataaccess.PatientDao;
import com.gmail.genadyms.server.datamodel.Patient;

import java.util.Date;

import org.junit.Assert;

public class PatientDaoTest {
	private PatientDao dao = new PatientDao();

	@Test
	public void testSave() {
		Patient patient = createMockPatient();
		dao.insert(patient);
		Assert.assertNotNull(patient.getId());
		dao.delete(patient.getId());
	}

	@Test
	public void testDelete() {
		Patient patient = createMockPatient();
		dao.insert(patient);
		Long id = patient.getId();
		Assert.assertNotNull(id);
		dao.delete(id);
		Patient delPatient = dao.get(patient.getId());
		Assert.assertNull(delPatient);
	}

	@Test
	public void testUpdate() {
		Patient patient = createMockPatient();
		dao.insert(patient);
		String changeFirstName = "change-firstname";
		patient.setFirstName(changeFirstName);
		dao.update(patient);
		Assert.assertEquals(changeFirstName, patient.getFirstName());
	}

	private Patient createMockPatient() {
		Patient p = new Patient();
		p.setDateOfBirth(new Date());
		p.setFirstName("first-name-" + String.valueOf(Math.random()));
		p.setLastName("last-name-" + String.valueOf(Math.random()));
		p.setMiddleName(("middle-name-" + String.valueOf(Math.random())));
		p.setHomeAddress(("home-address-" + String.valueOf(Math.random())));
		return p;
	}
}
