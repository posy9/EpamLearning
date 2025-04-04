package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Detail;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public class DetailRepository extends CommonRepository<Detail> {

    @Override
    public Detail create(Detail detail) {
        entityManager.persist(detail);
        return detail;
    }

    @Override
    public Detail findById(Long id) {
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
    public List<Detail>  readMultiple(Pageable pageable) {
        String queryStr = "SELECT d FROM Detail d";
        TypedQuery<Detail> query = entityManager.createQuery(queryStr, Detail.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    public Detail findByName(String name) {
            List<Detail> foundedDetail = entityManager.createQuery("SELECT Detail FROM Detail WHERE Detail.name = :name", Detail.class)
                    .setParameter("name", name)
                    .getResultList();
            if (foundedDetail.isEmpty()) {
                return null;
            }
            else {
                return foundedDetail.getFirst();
            }
    }

}
