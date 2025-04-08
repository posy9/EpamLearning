package by.bsu.detailstorage.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AbstractService<T> {

    T findById(long id);

    T createEntity(T entity);

    T updateEntity(long id, T entity);

    void deleteEntity(long id);

    List<T> findMultiple(Pageable pageable);
}
