package by.bsu.detailstorage.repository;


import org.springframework.data.domain.Pageable;

import java.util.List;


public interface Repository<T> {


    T create(T entity);


    T findById(Long id);


    T update(T entity);


    void delete(T entity);


    List<T> readMultiple(Pageable pageable);
}
