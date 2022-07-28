package hoon.pepper.template.persistence.repository;

import hoon.pepper.common.config.jpa.DefaultJpaRepository;
import hoon.pepper.template.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends DefaultJpaRepository<UserEntity, String> {
	UserEntity getByAccountId(String accountId);
}
