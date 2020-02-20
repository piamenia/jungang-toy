package io.dktechin.common.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.dktechin.common.config.security.model.LoginRequestModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author NGM
 */

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

	private final TokenAuthenticationService tokenAuthenticationService;

	public JwtLoginFilter(String url, AuthenticationManager authenticationManager, TokenAuthenticationService service) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);
		tokenAuthenticationService = service;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse hsr1) throws AuthenticationException, IOException, ServletException {
		LoginRequestModel credentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), LoginRequestModel.class);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getAccountId(), credentials.getPassword());
		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)
		throws IOException, ServletException {

		AuthenticationTokenImpl auth = (AuthenticationTokenImpl) authentication;
		tokenAuthenticationService.addAuthentication(response, auth);
	}
}

