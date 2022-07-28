package hoon.pepper.template.persistence.repository;

import hoon.pepper.common.config.security.redis.DefaultRedisRepository;
import hoon.pepper.template.persistence.entity.RefreshTokenEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends DefaultRedisRepository<RefreshTokenEntity, String> {
}
