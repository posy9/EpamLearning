package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
