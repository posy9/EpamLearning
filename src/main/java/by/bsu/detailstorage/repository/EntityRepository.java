package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityRepository<T extends DataEntity, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
