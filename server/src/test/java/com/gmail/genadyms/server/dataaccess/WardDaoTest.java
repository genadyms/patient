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
