package by.bsu.detailstorage.repository;

import jakarta.persistence.EntityManager;

import jakarta.persistence.PersistenceContext;
import org.hibernate.exception.ConstraintViolationException;

public abstract class CommonRepository<T> implements Repository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected static final String SELECT_FROM = "select %s from %s %s";
    protected static final String WHERE = "where %s";
    protected static final String SPACE = " ";
    protected static final String COMMA = ",";
    protected static final String ORDER_BY = "ORDER BY";
    protected static final String DOT = ".";

    @Override
    public T create(T entity) throws ConstraintViolationException {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }
}
