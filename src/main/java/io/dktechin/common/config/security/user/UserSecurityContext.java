package io.dktechin.common.config.security.user;

import io.dktechin.common.code.Authority;
import io.dktechin.common.config.security.jwt.AuthenticationTokenImpl;
import io.dktechin.common.exception.ForbiddenException;
import io.dktechin.template.service.UserService;
import io.dktechin.template.service.vo.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
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
