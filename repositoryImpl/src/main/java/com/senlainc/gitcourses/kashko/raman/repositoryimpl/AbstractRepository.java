package com.senlainc.gitcourses.kashko.raman.repositoryimpl;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.repository.GenericRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractRepository<T, PK extends Serializable> implements GenericRepository<T, PK> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    public abstract Class<T> getEntityClass();

    public EntityManager getEntityManager() {
        return entityManager;
    }


    @Override
    public T getById(PK id) throws ObjectNotFoundException {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(getEntityClass());
        Root<T> root = criteriaQuery.from(getEntityClass());
        criteriaQuery.where(cb.equal(root.get("id"), id));
        List<T> result = entityManager.createQuery(criteriaQuery).getResultList();
        if (!result.isEmpty()) {
            return result.iterator().next();
        } else {
            throw new ObjectNotFoundException("no id");
        }
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(getEntityClass());
        Root<T> root = query.from(getEntityClass());
        query.select(root);
        TypedQuery<T> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public void save(T entity) {
        entityManager.persist(entity);
        LOGGER.info("Successfully save");
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void delete(T t) {
        entityManager.remove(t);
    }
}
