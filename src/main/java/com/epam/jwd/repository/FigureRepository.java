package com.epam.jwd.repository;

import com.epam.jwd.exception.FigureNotFoundException;
import com.epam.jwd.model.Figure;

public interface FigureRepository<T extends Figure> extends Repository<T>  {
    T create(T entity);

    T read(int id) throws FigureNotFoundException;

    T update(T entity) throws FigureNotFoundException;

    void delete(int id);
}
