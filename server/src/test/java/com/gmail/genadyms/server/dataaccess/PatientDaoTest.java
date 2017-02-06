package com.gmail.genadyms.server.dataaccess;

import org.junit.Test;

import com.gmail.genadyms.server.dataaccess.PatientDao;
import com.gmail.genadyms.server.datamodel.Ward;
import com.gmail.genadyms.server.datamodel.Patient;

import java.util.Date;
import java.util.List;

import org.junit.Assert;

public class PatientDaoTest extends WardDaoTest{
	private PatientDao dao = new PatientDao();
	
	@Test
	public void testSave() {
		Patient patient = saveMockPatient();
		Assert.assertNotNull(patient.getId());
		clearDb(patient);
	}

	private void clearDb(Patient patient) {
		Ward bp = patient.getWard();	
		dao.delete(patient);
		clearBedPlace(bp);
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

//	@Test
//	public void testChangeAll(){
//		List<Patient> allList1 = dao.getAll();
//		System.out.println(allList1.size());
//		Patient p = null;
//		for (Patient cur : allList1) {
//			if(null==p) {
//				p=cur;
//			}
//			System.out.println(cur.getAddress());
//		}
//		p.setAddress("get all example");
//		System.out.println("----------------------------------------------");
//		dao.update(p);
//		List<Patient> allList2 = dao.getAll();
//		System.out.println(allList2.size());
//		for (Patient cur : allList2) {
//			System.out.println(cur.getAddress());
//		}
//		
//	}
	
	private Patient saveMockPatient() {
		Patient p = new Patient();
		p.setFirstName("first-name-" + String.valueOf(Math.random()));
		p.setLastName("last-name-" + String.valueOf(Math.random()));
		p.setAddress(("home-address-" + String.valueOf(Math.random())));
		p.setDiagnosis("diagnosis");
		p.setComingDate(new Date());
		Ward bedPlace = saveMockBedPlace(33, 2);
		p.setWard(bedPlace);
		dao.insert(p);
		return p;
	}
}
