package hoon.pepper.common.config.security.jwt;

import hoon.pepper.common.util.JsonUtils;
import hoon.pepper.template.service.vo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
public class TokenProvider {
	@Value("${spring.token.key}")
	private String key;

	@Value("${spring.token.prefix}")
	private String tokenPrefix;

	private String secret;

	@PostConstruct
	public void init() {
		secret = Sha512DigestUtils.shaHex(key);
	}

	public String issueToken(User user, long expireDuration) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("user", JsonUtils.objectToJson(user));
		return Jwts.builder()
			.setSubject(user.getAccountId())
			.setClaims(claims)
			.setExpiration(new Date(System.currentTimeMillis() + expireDuration))
			.signWith(SignatureAlgorithm.HS512, secret)
			.compact();
	}

	public String getToken(HttpServletRequest request, String headerString) {
		String token = request.getHeader(headerString);
		return getToken(token);
	}

	public String getToken(String token) {
		token = StringUtils.replace(token, tokenPrefix, "");
		token = StringUtils.trim(token);
		if (StringUtils.isNotEmpty(token)) {
			return token;
		}

		return null;
	}

	public Claims parseJwt(String token) {
		try {
			return Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException expiredException) {
			log.info("Expired token.");
			return null;
		} catch (Exception e) {
			log.info("Token parse error.");
			return null;
		}
	}
}

