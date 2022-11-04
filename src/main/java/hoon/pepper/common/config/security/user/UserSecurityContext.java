package hoon.pepper.common.config.security.user;

import hoon.pepper.common.code.Authority;
import hoon.pepper.common.config.security.jwt.AuthenticationTokenImpl;
import hoon.pepper.common.exception.ForbiddenException;
import hoon.pepper.conti.service.UserService;
import hoon.pepper.conti.service.vo.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserSecurityContext {

	private UserService userService;

	public String getAccountId() {
		return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public User getLoginUser() {
		return (User)((AuthenticationTokenImpl) SecurityContextHolder.getContext().getAuthentication()).getDetails();
	}

	public User getUser() {
		return userService.get(getLoginUser().getAccountId());
	}

	public boolean isAdmin() {
		if(getAuthority() != Authority.ADMIN) {
			throw new ForbiddenException();
		}
		return true;
	}

	public Authority getAuthority() {
		return getUser().getAuthority();
	}
}
