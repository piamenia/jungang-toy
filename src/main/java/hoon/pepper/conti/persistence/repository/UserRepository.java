package hoon.pepper.conti.persistence.repository;

import hoon.pepper.common.config.jpa.DefaultJpaRepository;
import hoon.pepper.conti.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends DefaultJpaRepository<UserEntity, String> {
	UserEntity getByAccountId(String accountId);
}
