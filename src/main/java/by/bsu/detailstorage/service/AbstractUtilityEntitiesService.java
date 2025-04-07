package by.bsu.detailstorage.service;

import java.util.List;

public interface AbstractUtilityEntitiesService<T> extends AbstractService<T> {

    List<T> findAll();
}
