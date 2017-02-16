package com.gmail.genadyms.server.dataaccess;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import com.gmail.genadyms.server.dataaccess.filter.BedPlaceFilter;
import com.gmail.genadyms.server.datamodel.Patient;
import com.gmail.genadyms.server.datamodel.Ward;

import java.util.List;

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

	public Ward findByNumberWard(Integer numberWard){
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ward> cq = cb.createQuery(Ward.class);
		Root<Ward> from = cq.from(Ward.class);
		cq.where(cb.equal(from.get("number"), numberWard));
		TypedQuery<Ward> q = em.createQuery(cq);
		List<Ward> allitems = q.getResultList();

		if (allitems.isEmpty()) {
			return null;
		} else if (allitems.size() == 1) {
			return allitems.get(0);
		} else {
			throw new IllegalArgumentException("more than 1 ward found ");
		}
	}

	public List<Ward> getFreeWard() {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ward> cq = cb.createQuery(Ward.class);
		Root<Ward> from = cq.from(Ward.class);
//		cq.where(cb.isNull(from.get("patients").get("leavingDate")));
//		Ward_
//		Predicate pred = cb.ge(from.get(Ward_.), y)
//		cq.where()
		TypedQuery<Ward> q = em.createQuery(cq);
		return q.getResultList();
	}
}
