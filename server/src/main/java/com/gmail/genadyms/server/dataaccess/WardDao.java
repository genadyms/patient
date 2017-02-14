package com.gmail.genadyms.server.dataaccess;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.gmail.genadyms.server.dataaccess.filter.BedPlaceFilter;
import com.gmail.genadyms.server.datamodel.Ward;

public class WardDao extends AbstractDao<Ward, Long> {
	private static final long serialVersionUID = 1L;

	public WardDao() {
		super(Ward.class);
	}

	public Long count() {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Ward> from = cq.from(Ward.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	/*
	 * public List<BedPlace> find(BedPlaceFilter filter) { EntityManager em =
	 * getEntityManager(); CriteriaBuilder cb = em.getCriteriaBuilder();
	 * CriteriaQuery<BedPlace> cq = cb.createQuery(BedPlace.class);
	 * Root<BedPlace> from = cq.from(BedPlace.class);
	 * 
	 * cq.select(from);
	 * 
	 * if (filter.getNumberOfBed() !=0 && filter.getNumberOfChamber() != 0) {
	 * Predicate numberOfChamberEqualCondition =
	 * cb.equal(from.get(BedPlace_.numberOfChamber),
	 * filter.getNumberOfChamber()); Predicate numberOfBedEqualCondition =
	 * cb.equal(from.get(BedPlace_.numberOfBed), filter.getNumberOfBed());
	 * cq.where(cb.and(numberOfChamberEqualCondition,
	 * numberOfBedEqualCondition)); }else if (filter.getNumberOfBed() ==0 &&
	 * filter.getNumberOfChamber() != 0) { Predicate
	 * numberOfChamberEqualCondition =
	 * cb.equal(from.get(BedPlace_.numberOfChamber),
	 * filter.getNumberOfChamber()); cq.where(numberOfChamberEqualCondition); }
	 * 
	 * if (filter.getSortProperty() != null) { cq.orderBy(new
	 * OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder())); }
	 * 
	 * TypedQuery<BedPlace> q = em.createQuery(cq); setPaging(filter, q); return
	 * q.getResultList(); }
	 */

}
