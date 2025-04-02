package by.bsu.detailstorage.dao;

import by.bsu.detailstorage.model.Detail;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class DetailDao extends CommonDao<Detail> {

    @Override
    public Detail create(Detail detail) {
        entityManager.persist(detail);
        return null;
    }

    @Override
    public Detail read(Long id) {
        return entityManager.find(Detail.class, id);
    }

    @Override
    public Detail update(Detail detail) {
        return entityManager.merge(detail);
    }

    @Override
    public void delete(Detail detail) {
        entityManager.remove(detail);
    }

    @Override
    public List<Detail> readMultiple(int limit, int offset) {
        return List.of();
    }
}
