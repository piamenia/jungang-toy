package hoon.pepper.conti.persistence.repository;

import hoon.pepper.common.config.security.redis.DefaultRedisRepository;
import hoon.pepper.conti.persistence.entity.RefreshTokenEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends DefaultRedisRepository<RefreshTokenEntity, String> {
}
