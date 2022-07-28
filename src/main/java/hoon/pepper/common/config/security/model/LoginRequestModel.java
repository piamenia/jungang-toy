package hoon.pepper.common.config.security.model;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class LoginRequestModel {

	@NotEmpty
	private String accountId;

	@NotEmpty
	private String password;

	private LocalDateTime loginAt;

	public boolean isExpired() {
		if (loginAt == null) {
			return true;
		}
		LocalDateTime expireTime = loginAt.plusHours(1);
		return expireTime.compareTo(LocalDateTime.now()) < 0;
	}
}
