package io.dktechin.template.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dktechin.common.config.security.jwt.TokenProvider;
import io.dktechin.common.exception.UnauthorizedException;
import io.dktechin.template.service.vo.User;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class TokenService {

	@Value("${spring.token.jwt.expire}")
	private long jwtExpireDuration;
	private ObjectMapper objectMapper = new ObjectMapper();

	private final RefreshTokenService refreshTokenService;
	private final TokenProvider tokenProvider;


	public String reIssuance(String refreshToken) {
		Claims claims = tokenProvider.parseJwt(refreshToken);
		if (claims != null) {
			try {
				String userJson = claims.get("user").toString();
				User user = objectMapper.readValue(userJson, User.class);
				if (validRefreshToken(user.getAccountId(), refreshToken)) {
					return tokenProvider.issueToken(user, jwtExpireDuration);
				}
			} catch (Exception e) {
				throw new UnauthorizedException("Invalid Refresh Token");
			}
		}
		throw new UnauthorizedException("Invalid Refresh Token");
	}

	private boolean validRefreshToken(String accountId, String refreshToken) {
		String oriRefreshToken = refreshTokenService.getRefreshToken(accountId);
		return StringUtils.isNotEmpty(oriRefreshToken) && oriRefreshToken.equals(refreshToken);
	}
}
