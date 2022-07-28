package hoon.pepper.common.config.security.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * DefaultRedisRepository
 * <pre>
 * Describe here
 * </pre>
 *
 * <pre>
 * <b>History:</b>
 * april, 1.0, 26/12/2018 초기작성
 * </pre>
 *
 * @author april
 * @version 1.0
 */
@NoRepositoryBean
public interface DefaultRedisRepository<T, ID> extends CrudRepository<T, ID> {
}
