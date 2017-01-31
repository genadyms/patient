package com.gmail.genadyms.server.dataaccess;

import com.gmail.genadyms.server.datamodel.PatientCard;

public class PatientCardDao extends AbstractDao<PatientCard, Long> {

    protected PatientCardDao() {
        super(PatientCard.class);
    }

    /*public Long count(PatientCardFilter filter) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PatientCard> from = cq.from(PatientCard.class);
        cq.select(cb.count(from));
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult();
    }

    public List<PatientCard> find(PatientCardFilter filter) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PatientCard> cq = cb.createQuery(PatientCard.class);
        Root<PatientCard> from = cq.from(PatientCard.class);
        cq.select(from);

        if (filter.getId() != null) {
            Predicate idEqualCondition = cb.equal(from.get(PatientCard_.id), filter.getId());
            cq.where(idEqualCondition);
        } else if (filter.getPatient() != null) {
            if (filter.isPatientCardActive()) {
                cq.where(cb.and(dateOfIssueCondition(cb, from), patientEqualCondition(filter, cb, from)));
            } else {
                cq.where(patientEqualCondition(filter, cb, from));
            }
        } else if (filter.getBedPlace() != null) {
            if (filter.isPatientCardActive()) {
                cq.where(cb.and(dateOfIssueCondition(cb, from), bedPlaceEqualCondition(filter, cb, from)));
            } else {
                cq.where(bedPlaceEqualCondition(filter, cb, from));
            }
        } else if (filter.getHospitalStaff() != null) {
            if (filter.isPatientCardActive()) {
                cq.where(cb.and(dateOfIssueCondition(cb, from), hospitalStaffEqualCondition(filter, cb, from)));
            } else {
                cq.where(hospitalStaffEqualCondition(filter, cb, from));
            }
        } else if (filter.isPatientCardActive()) {
            cq.where(dateOfIssueCondition(cb, from));
        }

        if (filter.getSortProperty() != null) {
            cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
        }
        setFetch(filter, from);
        TypedQuery<PatientCard> q = em.createQuery(cq);
        setPaging(filter, q);
        return q.getResultList();
    }

    private Predicate bedPlaceEqualCondition(PatientCardFilter filter, CriteriaBuilder cb, Root<PatientCard> from) {
        return cb.equal(from.get(PatientCard_.bedPlace), filter.getBedPlace());
    }

    private Predicate hospitalStaffEqualCondition(PatientCardFilter filter, CriteriaBuilder cb, Root<PatientCard> from) {
        return cb.equal(from.get(PatientCard_.hospitalStaff), filter.getHospitalStaff());
    }

    private Predicate dateOfIssueCondition(CriteriaBuilder cb, Root<PatientCard> from) {
        return cb.isNull(from.get(PatientCard_.dateOfIssue));
    }

    private Predicate patientEqualCondition(PatientCardFilter filter, CriteriaBuilder cb, Root<PatientCard> from) {
        return cb.equal(from.get(PatientCard_.patient), filter.getPatient());
    }

    private void setFetch(PatientCardFilter filter, Root<PatientCard> from) {
        if (filter.isFetchBedPlaces() && filter.getBedPlace() == null) {
            from.fetch(PatientCard_.bedPlace, JoinType.LEFT);
        } else if (filter.isFetchBedPlaces() && filter.getBedPlace() != null) {
            from.fetch(PatientCard_.bedPlace, JoinType.INNER);
        }

        if (filter.isFetchHospitallStaffs() && filter.getHospitalStaff() == null) {
            from.fetch(PatientCard_.hospitalStaff, JoinType.LEFT);
        } else if (filter.isFetchHospitallStaffs() && filter.getHospitalStaff() != null) {
            from.fetch(PatientCard_.hospitalStaff, JoinType.INNER);
        }

        if (filter.isFetchPatients() && filter.getPatient() == null) {
            from.fetch(PatientCard_.patient, JoinType.LEFT);
        } else if (filter.isFetchPatients() && filter.getPatient() != null) {
            from.fetch(PatientCard_.patient, JoinType.INNER);
        }
    }*/

}
