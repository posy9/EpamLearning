package by.bsu.detailstorage.dao;

import jakarta.persistence.EntityManager;

import jakarta.persistence.PersistenceContext;

import java.util.List;

public abstract class CommonDao<T> implements Dao<T> {

    @PersistenceContext
    protected EntityManager entityManager;




}
