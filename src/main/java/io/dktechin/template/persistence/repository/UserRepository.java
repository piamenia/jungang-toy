package io.dktechin.template.persistence.repository;

import io.dktechin.common.config.jpa.DefaultJpaRepository;
import io.dktechin.template.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends DefaultJpaRepository<UserEntity, String> {
	UserEntity getByAccountId(String accountId);
}
