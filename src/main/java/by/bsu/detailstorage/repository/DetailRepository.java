package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Brand;
import by.bsu.detailstorage.model.Detail;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.DETAIL;

@Repository
public final class DetailRepository extends CommonRepository<Detail> implements PageableEntitiesRepository<Detail> {

    private static final String DETAIL_ALIAS = "d";
    private static final String SELECT_DETAIL_STATEMENT = String.format(SELECT_FROM,DETAIL_ALIAS,
            DETAIL.getEntityName(), DETAIL_ALIAS);

    @Override
    public Optional<Detail> findById(Long id) {
        return Optional.of(entityManager.find(Detail.class, id));
    }

    @Override
    public List<Detail>  readMultiple(Pageable pageable) {
        String resultStatementPrepared = getResultStatement(pageable);
        TypedQuery<Detail> query = entityManager.createQuery(resultStatementPrepared, Detail.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    private String getResultStatement(Pageable pageable) {
        if(pageable.getSort().isSorted()){
            return makeStatementWithOrderBy(pageable);
        }
        else{
            return SELECT_DETAIL_STATEMENT;
        }
    }

    private String makeStatementWithOrderBy(Pageable pageable) {
        List<String> orders = new ArrayList<>();
        for (Sort.Order sort : pageable.getSort()) {
            orders.add(DETAIL_ALIAS + DOT + sort.getProperty() + SPACE + sort.getDirection());
        }
        return SELECT_DETAIL_STATEMENT + SPACE + ORDER_BY + SPACE + String.join(COMMA + SPACE, orders);
    }

    @Override
    public List<Detail> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Detail> cq = cb.createQuery(Detail.class);
        Root<Detail> detail = cq.from(Detail.class);
        CriteriaQuery<Detail> select = cq.select(detail);
        TypedQuery<Detail> query = entityManager.createQuery(select) ;
        return query.getResultList();
    }
}
