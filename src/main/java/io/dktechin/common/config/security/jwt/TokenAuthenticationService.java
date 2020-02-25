package io.dktechin.common.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dktechin.common.util.JsonUtils;
import io.dktechin.template.service.RefreshTokenService;
import io.dktechin.template.service.UserService;
import io.dktechin.template.service.vo.User;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenAuthenticationService {

	@Value("${spring.token.prefix}")
	private String tokenPrefix;

	@Value("${spring.token.jwt.expire}")
	private long jwtExpireDuration;

	@Value("${spring.token.refresh.expire}")
	private long refreshExpireDuration;

	@Value("${spring.token.jwt.header}")
	private String jwtHeaderString;

	@Value("${spring.token.refresh.header}")
	private String refreshHeaderString;

	private final UserService userService;
	private final RefreshTokenService refreshTokenService;
	private final AuthorityProvider authorityProvider;
	private final TokenProvider tokenProvider;
	private ObjectMapper objectMapper = new ObjectMapper();

	public void addAuthentication(HttpServletResponse response, AuthenticationTokenImpl auth) {
		String accountId = auth.getPrincipal().toString();
		User user = userService.get(accountId);

		Map<String, Object> claims = new HashMap<>();
		claims.put("user", JsonUtils.objectToJson(user));

		String jwt = tokenProvider.issueToken(user, jwtExpireDuration);
		response.addHeader(jwtHeaderString, tokenPrefix + " " + jwt);

		String refreshToken = tokenProvider.issueToken(user, refreshExpireDuration);
		log.info("RefreshToken: " + refreshToken);
		refreshTokenService.save(accountId, refreshToken);
		response.addHeader(refreshHeaderString, tokenPrefix + " " + refreshToken);
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String token = tokenProvider.getToken(request, jwtHeaderString);
		Claims claims = tokenProvider.parseJwt(token);
		if (claims != null) {
			try {
				String userJson = claims.get("user").toString();
				User user = objectMapper.readValue(userJson, User.class);
				Set<GrantedAuthority> authoritySet = authorityProvider.getAuthoritySet(user);
				AuthenticationTokenImpl auth = new AuthenticationTokenImpl(user.getAccountId(), authoritySet);
				auth.setAuthenticated(true);
				auth.setDetails(user);
				return auth;
			} catch (Exception e) {
				return new UsernamePasswordAuthenticationToken(null, null);
			}
		} else {
			return new UsernamePasswordAuthenticationToken(null, null);
		}
	}
}
