package io.dktechin.common.config.security.jwt;

import io.dktechin.common.exception.UnauthorizedException;
import io.dktechin.template.service.RefreshTokenService;
import io.dktechin.template.service.UserService;
import io.dktechin.template.service.vo.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Set;

@Log4j2
@Component
@RequiredArgsConstructor
public class AuthenticationProviderImpl implements AuthenticationProvider {

	private final RefreshTokenService refreshTokenService;
	private final UserService userService;
	private final AuthorityProvider authorityProvider;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			validRequestLogin(authentication);

			String accountId = authentication.getPrincipal().toString();
			String password = authentication.getCredentials().toString();

			// TODO
			if (accountId.equals(password)) {
				return successAuthenticate(accountId);
			} else {
				throw new UnauthorizedException("Failed Login.");
			}
		} catch (UnauthorizedException ue) {
			throw new BadCredentialsException(ue.getMessage());
		}
	}

	private Authentication successAuthenticate(String accountId) {
		User user = userService.get(accountId);
		return setAuthentication(user);
	}

	private AuthenticationTokenImpl setAuthentication(User user) {
		Set<GrantedAuthority> authoritySet = authorityProvider.getAuthoritySet(user);
		AuthenticationTokenImpl auth = new AuthenticationTokenImpl(user.getAccountId(), authoritySet);
		auth.setAuthenticated(true);
		auth.setDetails(user);
		return auth;
	}

	private void validRequestLogin(Authentication authentication) {
		if (StringUtils.isEmpty(authentication.getPrincipal()) || StringUtils.isEmpty(authentication.getCredentials())) {
			throw new UnauthorizedException("Failed Login.");
		}
	}

	@Override
	public boolean supports(Class<?> type) {
		return type.equals(UsernamePasswordAuthenticationToken.class);
	}

}
