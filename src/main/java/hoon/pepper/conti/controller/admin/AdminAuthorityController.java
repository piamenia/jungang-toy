package hoon.pepper.conti.controller.admin;

import hoon.pepper.common.code.Authority;
import hoon.pepper.common.config.security.jwt.TokenProvider;
import hoon.pepper.common.wrapper.ResultResponse;
import hoon.pepper.conti.controller.model.LoginResultModel;
import hoon.pepper.conti.service.AuthorityService;
import hoon.pepper.conti.service.RefreshTokenService;
import hoon.pepper.conti.service.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/admin/auth")
@RequiredArgsConstructor
@Api(tags = "관리자 인증 컨트롤러")
public class AdminAuthorityController {
	private final AuthorityService authorityService;
	private final TokenProvider tokenProvider;
	private final RefreshTokenService refreshTokenService;
	@Value("${spring.token.jwt.expire}")
	private long jwtExpireDuration;
	@Value("${spring.token.refresh.expire}")
	private long refreshExpireDuration;

	@PostMapping("/valid/password")
	@ApiOperation(value="패스워드 체크", notes="")
	public ResultResponse<LoginResultModel> validPassword(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> body) {
		boolean result = authorityService.validAdminPassword(body.get("password").toString());
		LoginResultModel loginResultModel = new LoginResultModel();
		loginResultModel.setLoginResult(result);
		if (result) {
			User user = new User(request.getRemoteHost(), Authority.ADMIN, LocalDateTime.now());
			String accessToken = tokenProvider.issueToken(user, jwtExpireDuration);
			String refreshToken = tokenProvider.issueToken(user, refreshExpireDuration);
			refreshTokenService.save(user.getAccountId(), refreshToken);
//			Cookie accessTokenCookie = makeCookie("accessToken", accessToken, "/", 60);
//			Cookie refreshTokenCookie = makeCookie("refreshToken", accessToken, "/", 60);
//			response.addCookie(accessTokenCookie);
//			response.addCookie(refreshTokenCookie);
			loginResultModel.setAccessToken(accessToken);
			loginResultModel.setRefreshToken(refreshToken);
		}
		return new ResultResponse<>(loginResultModel);
	}

	private Cookie makeCookie(String fieldName, String value, String path, int maxAge) {
		Cookie cookie = new Cookie(fieldName, value);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		cookie.setDomain("kakaowork.com");
		return cookie;
	}
}
