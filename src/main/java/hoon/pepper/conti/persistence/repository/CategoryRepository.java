package hoon.pepper.conti.persistence.repository;

import hoon.pepper.common.config.jpa.DefaultJpaRepository;
import hoon.pepper.conti.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends DefaultJpaRepository<CategoryEntity, String> {
}
