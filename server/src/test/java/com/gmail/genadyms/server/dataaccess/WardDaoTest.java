package com.gmail.genadyms.server.dataaccess;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.gmail.genadyms.server.datamodel.Patient;
import com.gmail.genadyms.server.datamodel.Ward;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WardDaoTest {
	private WardDao daoWard = new WardDao();
	private PatientDao daoPatient = new PatientDao();

	@Test
	public void testSave() {
		Long totalStart = daoWard.count();
		Ward bedPlace = saveMockWard(21, 21);
		Long totalSave = daoWard.count() - 1;
		Assert.assertEquals(totalStart, totalSave);
		Assert.assertNotNull(bedPlace.getId());
		daoWard.delete(bedPlace);
	}

	@Test
	public void testDelete() {
		Ward bedPlace = saveMockWard(22, 22);
		daoWard.delete(bedPlace);
		Ward delPlace = daoWard.get(bedPlace.getId());
		Assert.assertNull(delPlace);
	}

	@Ignore
	@Test
	public void testGetFreeWards() {
		List<Patient> patientsActive = daoPatient.findActivePatients();
		Assert.assertNotNull(patientsActive);
		Map<Ward, Integer> wardsPatients = new HashMap();
		System.out.println("Total patients active is " + patientsActive.size());
		for (Patient patient : patientsActive) {
			Ward currentWard = patient.getWard();
			if (wardsPatients.containsKey(currentWard)) {
				Integer changeCountsPatients = wardsPatients.get(currentWard) + 1;
				wardsPatients.put(currentWard, changeCountsPatients);
				System.out.println(currentWard + "|" + changeCountsPatients + "|" + patient.getId());
			} else {
				wardsPatients.put(currentWard, 1);
				System.out.println(currentWard + "|" + patient.getId());
			}
		}
		Set<Ward> wardsFree = new HashSet();
		for (Ward ward : wardsPatients.keySet()) {
			System.out.println(ward.getCountBeds() + "|" + wardsPatients.get(ward));
			if (wardsPatients.get(ward) < ward.getCountBeds()) {
				wardsFree.add(ward);
				System.out.println(ward);
			}
		}
		Assert.assertNotNull(wardsFree);
	}

	protected Ward saveMockWard(Integer nChamber, Integer nBed) {
		Ward mock = new Ward();
		mock.setNumber(nChamber);
		mock.setCountBeds(nBed);
		daoWard.insert(mock);
		return mock;
	}

	protected void clearMockWard(Ward ward) {
		daoWard.delete(ward);
	}
}
