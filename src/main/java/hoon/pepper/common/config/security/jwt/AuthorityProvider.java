package hoon.pepper.common.config.security.jwt;

import hoon.pepper.conti.service.UserService;
import hoon.pepper.conti.service.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AuthorityProvider {
	private final UserService userService;

	public Set<GrantedAuthority> getAuthoritySet(String accountId) {
		User user = userService.get(accountId);
		return getAuthoritySet(user);
	}

	public Set<GrantedAuthority> getAuthoritySet(User user) {
		Set<GrantedAuthority> authoritySet = new HashSet<>();
		authoritySet.add(new SimpleGrantedAuthority("ROLE_USER"));
		authoritySet.add(new SimpleGrantedAuthority(user.getAuthority().name()));
		return authoritySet;
	}
}
