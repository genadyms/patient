package com.gmail.genadyms.server.dataaccess;

import org.junit.Assert;
import org.junit.Test;

import com.gmail.genadyms.server.datamodel.Ward;

public class WardDaoTest {
	private WardDao dao = new WardDao();
	
	@Test
	public void testSave() {
		Long totalStart = dao.count();
		Ward bedPlace = saveMockWard(21, 21);
		Long totalSave = dao.count()-1;
		Assert.assertEquals(totalStart, totalSave);
		Assert.assertNotNull(bedPlace.getId());
		dao.delete(bedPlace);
	}
	
	@Test
	public void testDelete() {
		Ward bedPlace = saveMockWard(22,22);
		dao.delete(bedPlace);
		Ward delPlace = dao.get(bedPlace.getId());
		Assert.assertNull(delPlace);
	}
	
	protected Ward saveMockWard(Integer nChamber, Integer nBed) {
		Ward mock = new Ward();
		mock.setNumber(nChamber);
		mock.setCountBeds(nBed);
		dao.insert(mock);
		return mock;
	}
	
	protected void clearMockWard(Ward ward) {
		dao.delete(ward);
	}
}
