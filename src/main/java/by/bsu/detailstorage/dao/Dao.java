package by.bsu.detailstorage.dao;

import java.util.List;

public interface Dao<T> {

    abstract T create(T entity);

    abstract T read(Long id);

    abstract T update(T entity);

    abstract void delete(T entity);

    abstract List<T> readMultiple(int limit, int offset);
}
