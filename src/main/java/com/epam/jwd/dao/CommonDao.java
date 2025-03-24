package com.epam.jwd.dao;

import java.util.List;
import java.util.Optional;

public abstract class CommonDao<T> {

    abstract Optional<T> create(T t) throws InterruptedException;

    abstract List<T> Read();
}
