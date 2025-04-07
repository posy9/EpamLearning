package by.bsu.detailstorage.service;

import jakarta.transaction.Transactional;

public interface AbstractService<T> {

    T findById(long id);

    T createEntity(T entity);

    T updateEntity(long id, T entity);

    void deleteEntity(long id);
}
