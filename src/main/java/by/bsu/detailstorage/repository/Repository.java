package by.bsu.detailstorage.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    T create(T entity);

    Optional<T> findById(Long id);

    T update(T entity);

    void delete(T entity);

    List<T> findAll();
}
