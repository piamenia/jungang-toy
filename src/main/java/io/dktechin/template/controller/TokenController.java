package io.dktechin.template.controller;

import io.dktechin.common.config.security.jwt.TokenProvider;
import io.dktechin.common.wrapper.ResultResponse;
import io.dktechin.template.controller.model.EmptyResultModel;
import io.dktechin.template.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
@Api(tags = "토큰 API")
public class TokenController {

	@Value("${spring.token.jwt.header}")
	private String jwtHeaderString;

	@Value("${spring.token.refresh.header}")
	private String refreshHeaderString;

	@Value("${spring.token.prefix}")
	private String tokenPrefix;

	private final TokenProvider tokenProvider;
	private final TokenService tokenService;

	@ApiOperation(value = "Access Token 재발급")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "RefreshToken", value = "RefreshToken Header", required = true,
			dataType= "string", paramType = "header", defaultValue = "Bearer REFRESH_TOKEN")
	})
	@PostMapping("/reissuance")
	public ResultResponse<EmptyResultModel> reIssuance(HttpServletRequest request, HttpServletResponse response) {
		String refreshToken = tokenProvider.getToken(request, refreshHeaderString);
		String newAccessToken = tokenService.reIssuance(refreshToken);
		response.addHeader(jwtHeaderString, tokenPrefix + " " + newAccessToken);

		return new ResultResponse<>(new EmptyResultModel());
	}
}
