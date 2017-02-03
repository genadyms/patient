package com.gmail.genadyms.server.dataaccess;

import org.junit.Test;

import com.gmail.genadyms.server.dataaccess.PatientDao;
import com.gmail.genadyms.server.datamodel.Ward;
import com.gmail.genadyms.server.datamodel.Patient;

import java.util.Date;

import org.junit.Assert;

public class PatientDaoTest extends BedPlaceDaoTest{
	private PatientDao dao = new PatientDao();

	@Test
	public void testSave() {
		Patient patient = saveMockPatient();
		Assert.assertNotNull(patient.getId());
		clearDb(patient);
	}

	private void clearDb(Patient patient) {
		Ward bp = patient.getWard();	
		dao.delete(patient.getId());
		clearBedPlace(bp.getId());
	}

	@Test
	public void testUpdate() {
		Patient patient = saveMockPatient();
		String changeFirstName = "change-firstname";
		patient.setFirstName(changeFirstName);
		dao.update(patient);
		Assert.assertEquals(changeFirstName, patient.getFirstName());
		clearDb(patient);
	}

	private Patient saveMockPatient() {
		Patient p = new Patient();
		p.setFirstName("first-name-" + String.valueOf(Math.random()));
		p.setLastName("last-name-" + String.valueOf(Math.random()));
		p.setAddress(("home-address-" + String.valueOf(Math.random())));
		p.setDiagnosis("diagnosis");
		p.setComingDate(new Date());
		Ward bedPlace = saveMockBedPlace(2, 2);
		p.setWard(bedPlace);
		dao.insert(p);
		return p;
	}
}
