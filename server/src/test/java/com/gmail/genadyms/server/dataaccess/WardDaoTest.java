package com.gmail.genadyms.server.dataaccess;

import org.junit.Assert;
import org.junit.Test;

import com.gmail.genadyms.server.datamodel.Ward;

public class WardDaoTest {
	private WardDao dao = new WardDao();
	
	@Test
	public void testSave() {
		Ward bedPlace = saveMockBedPlace(21, 21);
		Assert.assertNotNull(bedPlace.getId());
		dao.delete(bedPlace.getId());
	}
	
	@Test
	public void testDelete() {
		Ward bedPlace = saveMockBedPlace(22,22);
		dao.delete(bedPlace.getId());
		Ward delPlace = dao.get(bedPlace.getId());
		Assert.assertNull(delPlace);
	}
	
	protected Ward saveMockBedPlace(Integer nChamber, Integer nBed) {
		Ward mock = new Ward();
		mock.setNumber(nChamber);
		mock.setCountBeds(nBed);
		dao.insert(mock);
		return mock;
	}
	
	protected void clearBedPlace(Long id) {
		dao.delete(id);
	}
}
