//package hoon.pepper.common.config.security.jwt;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//	private final TokenAuthenticationService service;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//		throws ServletException, IOException {
//		Authentication authentication = service.getAuthentication((HttpServletRequest) request);
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		filterChain.doFilter(request, response);
//	}
//}
