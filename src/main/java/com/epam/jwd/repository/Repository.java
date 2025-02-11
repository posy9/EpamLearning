package com.epam.jwd.repository;

import com.epam.jwd.exception.FigureNotFoundException;

public interface Repository<T>{

    T create(T entity);

    T read(int id);

    T update(T entity);

    void delete(int id);
}
