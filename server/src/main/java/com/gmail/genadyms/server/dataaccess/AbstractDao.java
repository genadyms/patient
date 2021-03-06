package com.gmail.genadyms.server.dataaccess;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import com.gmail.genadyms.server.dataaccess.util.EntityManagerFactoryBuilder;

public class AbstractDao<T, ID> implements Serializable {
    private static final long serialVersionUID = 1L;

    private EntityManager entityManager;

    private final Class<T> entityClass;

    protected AbstractDao(final Class<T> entityClass) {
        this.entityManager = EntityManagerFactoryBuilder.getEntityManagerFactory().createEntityManager();
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

    public void delete(T entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(entity);
        getEntityManager().getTransaction().commit();
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }


}