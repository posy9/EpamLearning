package by.bsu.detailstorage.repository;

import jakarta.persistence.EntityManager;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonRepository<T> implements AbstractRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public T create(T entity) throws ConstraintViolationException {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    protected void addOrderBy(Pageable pageable, Root<T> root, CriteriaBuilder cb, CriteriaQuery<T> cq, List<String> fields) {
        if (pageable.getSort().isSorted()) {
            List<Order> orders = new ArrayList<>();
            for (Sort.Order sortOrder : pageable.getSort()) {
                if (fields.contains(sortOrder.getProperty().toLowerCase())) {
                    Path<Object> path = root.get(sortOrder.getProperty());
                    Order order = sortOrder.isAscending()
                            ? cb.asc(path)
                            : cb.desc(path);
                    orders.add(order);}

            }
            cq.orderBy(orders);
        }
    }
}
