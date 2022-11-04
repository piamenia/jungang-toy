package hoon.pepper.conti.persistence.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash(value = "jwt", timeToLive = 86400) // 1 일 - 60 * 60 * 24
public class RefreshTokenEntity {
	@Id
	private String accountId;

	private String refreshToken;
}
