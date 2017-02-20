package com.gmail.genadyms.server.dataaccess;

import com.gmail.genadyms.server.datamodel.Patient;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class PatientDao extends AbstractDao<Patient, Long> {

    private static final long serialVersionUID = 1L;

    public PatientDao() {
        super(Patient.class);
    }

    public Long count() {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Patient> from = cq.from(Patient.class);
        cq.select(cb.count(from));
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult();
    }

    public List<Patient> find(Integer start, Integer limit) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Patient> cq = cb.createQuery(Patient.class);
        Root<Patient> from = cq.from(Patient.class);
        cq.select(from);
        TypedQuery<Patient> q = em.createQuery(cq);
        q.setFirstResult(start);
        q.setMaxResults(limit);
        return q.getResultList();
    }
}
