package com.gmail.genadyms.server.dataaccess;

import org.junit.Assert;
import org.junit.Test;

import com.gmail.genadyms.server.datamodel.BedPlace;

public class BedPlaceDaoTest {
	private BedPlaceDao dao = new BedPlaceDao();
	
	@Test
	public void testSave() {
		BedPlace bedPlace = saveMockBedPlace(21, 21);
		Assert.assertNotNull(bedPlace.getId());
		dao.delete(bedPlace.getId());
	}
	
	@Test
	public void testDelete() {
		BedPlace bedPlace = saveMockBedPlace(22,22);
		dao.delete(bedPlace.getId());
		BedPlace delPlace = dao.get(bedPlace.getId());
		Assert.assertNull(delPlace);
	}
	
	protected BedPlace saveMockBedPlace(Integer nChamber, Integer nBed) {
		BedPlace mock = new BedPlace();
		mock.setNumberOfChamber(nChamber);
		mock.setNumberOfBed(nBed);
		dao.insert(mock);
		return mock;
	}
	
	protected void clearBedPlace(Long id) {
		dao.delete(id);
	}
}
