package by.bsu.detailstorage.repository;

import jakarta.persistence.EntityManager;

import jakarta.persistence.PersistenceContext;


public abstract class CommonRepository<T> implements Repository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected static final String SELECT_FROM = "select %s from ";

}
