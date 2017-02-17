package com.gmail.genadyms.server.dataaccess;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import com.gmail.genadyms.server.dataaccess.filter.BedPlaceFilter;
import com.gmail.genadyms.server.datamodel.Patient;
import com.gmail.genadyms.server.datamodel.Patient_;
import com.gmail.genadyms.server.datamodel.Ward;
import com.gmail.genadyms.server.datamodel.Ward_;

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

	public List<Ward> findFreeWards(){
		//https://www.ibm.com/developerworks/ru/library/j-typesafejpa/
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ward> cq = cb.createQuery(Ward.class);
		Root<Ward> from = cq.from(Ward.class);
		ListJoin<Ward, Patient> patients = from.join(Ward_.patients);
		from.fetch(Ward_.patients);
//		Expression<Long> expr = cb.count(cb.isNull(patients.get(Patient_.leavingDate)));
//		Predicate p1 = cb.isNull(from.get(Ward_.patients));
		Predicate predNull = cb.isNull(patients.get(Patient_.leavingDate));
		Predicate predEmtyWard = cb.isNull(patients);
		cq.where(predNull);//cb.lt(expr,from.get(Ward_.countBeds)));
//		TypedQuery<Ward> q = em.createQuery(cq);
//		return q.getResultList();
		cq.distinct(true);
		TypedQuery<Ward> q = em.createQuery(cq);
		List<Ward> allitems = q.getResultList();
		return allitems;
	}
}
