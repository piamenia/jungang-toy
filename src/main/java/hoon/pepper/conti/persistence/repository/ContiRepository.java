package hoon.pepper.conti.persistence.repository;

import hoon.pepper.common.config.jpa.DefaultJpaRepository;
import hoon.pepper.conti.persistence.entity.ContiEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ContiRepository extends DefaultJpaRepository<ContiEntity, Long>, ContiRepositoryCustom{
}
