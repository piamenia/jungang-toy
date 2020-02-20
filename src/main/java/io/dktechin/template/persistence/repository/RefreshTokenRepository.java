package io.dktechin.template.persistence.repository;

import io.dktechin.common.config.security.redis.DefaultRedisRepository;
import io.dktechin.template.persistence.entity.RefreshTokenEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends DefaultRedisRepository<RefreshTokenEntity, String> {
}
