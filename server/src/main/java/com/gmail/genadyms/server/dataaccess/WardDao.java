package com.gmail.genadyms.server.dataaccess;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import com.gmail.genadyms.server.datamodel.Patient;
import com.gmail.genadyms.server.datamodel.Ward;

import java.util.List;

public class WardDao extends AbstractDao<Ward, Long> {
    private static final long serialVersionUID = 1L;

    private static final String QUERY_FREE_WARDS = "SELECT w " +
            "FROM " + Ward.class.getName() +
            " AS w WHERE " +
            "(SELECT COUNT(p.ward) FROM " + Patient.class.getName() +
            " AS p WHERE p.leavingDate IS NULL AND p.ward=w) < w.countBeds";

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

    public Ward findByNumberWard(Integer numberWard) {
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

    public List<Ward> findFreeWards() {
        EntityManager em = getEntityManager();
        Query q = em.createQuery(QUERY_FREE_WARDS);
        return q.getResultList();
    }

//    public List<Ward> findFreeWardsCriteria() {
//       EntityManager em = getEntityManager();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Ward> cq = cb.createQuery(Ward.class);
//        Root<Ward> from = cq.from(Ward.class);
//        ListJoin<Ward, Patient> patients = from.join(Ward_.patients);
//        from.fetch(Ward_.patients);
//        Predicate predNull = cb.isNull(patients.get(Patient_.leavingDate));
//        Predicate predEmtyWard = cb.isNull(patients);
//        cq.where(predNull);
//        cq.distinct(true);
//        TypedQuery<Ward> q = em.createQuery(cq);
//        List<Ward> allitems = q.getResultList();
//        return allitems;
//    }
}
