package by.bsu.detailstorage.repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PageableEntitiesRepository<T> extends Repository<T> {

    List<T> readMultiple(Pageable pageable);
}
