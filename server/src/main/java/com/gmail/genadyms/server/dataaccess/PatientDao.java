package com.gmail.genadyms.server.dataaccess;

import com.gmail.genadyms.server.datamodel.Patient;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class PatientDao extends AbstractDao<Patient, Long> {

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

/*    @Override
    public List<Patient> find(PatientFilter filter) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Patient> cq = cb.createQuery(Patient.class);
        Root<Patient> from = cq.from(Patient.class);
        cq.select(from);
        
        Predicate firstNameEqualCondition = cb.equal(from.get(Patient_.firstName), filter.getFirstName());
        Predicate lastNameEqualCondition = cb.equal(from.get(Patient_.lastName), filter.getLastName());
        Predicate middleNameEqualCondition = cb.equal(from.get(Patient_.middleName), filter.getMiddleName());
        
        if (filter.getFirstName() != null && filter.getLastName() != null && filter.getMiddleName() != null) {
        	cq.where(cb.and(firstNameEqualCondition,lastNameEqualCondition,middleNameEqualCondition));
		}else if (filter.getFirstName() != null && filter.getLastName() != null && filter.getMiddleName() == null) {
			cq.where(cb.and(firstNameEqualCondition,lastNameEqualCondition));
		}else if (filter.getFirstName() != null && filter.getLastName() == null && filter.getMiddleName() == null) {
			cq.where(firstNameEqualCondition);
		}else if (filter.getFirstName() != null && filter.getLastName() == null && filter.getMiddleName() != null) {
			cq.where(cb.and(firstNameEqualCondition,middleNameEqualCondition));
		}else if (filter.getFirstName() == null && filter.getLastName() == null && filter.getMiddleName() != null) {
			cq.where(middleNameEqualCondition);
		}else if (filter.getFirstName() == null && filter.getLastName() != null && filter.getMiddleName() != null) {
			cq.where(cb.and(lastNameEqualCondition,middleNameEqualCondition));
		}else if (filter.getFirstName() == null && filter.getLastName() != null && filter.getMiddleName() == null) {
			cq.where(lastNameEqualCondition);
		}
        
        if (filter.getSortProperty() != null) {
            cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
        }

        TypedQuery<Patient> q = em.createQuery(cq);
        setPaging(filter, q);
        return q.getResultList();
    }
*/

}
