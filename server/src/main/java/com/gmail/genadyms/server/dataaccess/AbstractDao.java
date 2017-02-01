package com.gmail.genadyms.server.dataaccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

public class AbstractDao<T, ID> {

    private EntityManager entityManager;

    private final Class<T> entityClass;

    protected AbstractDao(final Class<T> entityClass) {
        this.entityManager =
                Persistence.createEntityManagerFactory("gwt").createEntityManager();
        
        this.entityClass = entityClass;
    }

    public List<T> getAll() {
        final CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(getEntityClass());
        query.from(getEntityClass());
        final List<T> lst = entityManager.createQuery(query).getResultList();
        return lst;
    }

    public T get(final ID id) {
        return entityManager.find(getEntityClass(), id);
    }

    public T insert(final T entity) {
        getEntityManager().getTransaction().begin();
        entityManager.persist(entity);
        getEntityManager().getTransaction().commit();
        return entity;
    }

    public T update(T entity) {
    	getEntityManager().getTransaction().begin();
        entity = entityManager.merge(entity);
        getEntityManager().getTransaction().commit();
        return entity;
    }

    public void delete(ID id) {
        getEntityManager().getTransaction().begin();
        entityManager.createQuery(String.format("delete from %s e where e.id = :id", entityClass.getSimpleName())).setParameter("id", id).executeUpdate();
        getEntityManager().flush();
        getEntityManager().clear();
        getEntityManager().getTransaction().commit();
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
    /*
    protected void setPaging(AbstractFilter filter, TypedQuery<? extends AbstractModel> q) {
        if (filter.getOffset() != null && filter.getLimit() != null) {
            q.setFirstResult(filter.getOffset());
            q.setMaxResults(filter.getLimit());
        }
    }*/

}