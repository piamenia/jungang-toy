//package hoon.pepper.common.config.security.user;
//
//import hoon.pepper.common.config.security.CommonAuthEntryPoint;
//import hoon.pepper.common.config.security.jwt.AuthenticationProviderImpl;
//import hoon.pepper.common.config.security.jwt.JwtLoginFilter;
//import hoon.pepper.common.config.security.jwt.TokenAuthenticationService;
//import hoon.pepper.common.config.security.jwt.JwtAuthenticationFilter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
//import java.util.Arrays;
//
//@Order(2)
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class UserAuthSecurity extends WebSecurityConfigurerAdapter {
//
//	private final LogoutHandler logoutHandler;
//	private final TokenAuthenticationService tokenAuthenticationService;
//	private final AuthenticationProviderImpl authenticationProvider;
//
//	@Override
//	protected AuthenticationManager authenticationManager() throws Exception {
//		return new ProviderManager(Arrays.asList(authenticationProvider));
//	}
//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		final String[] GET_WHITELIST = new String[]{
//			"/user/**"
//		};
//
//		final String[] POST_WHITELIST = new String[]{
//			"/admin/auth/valid/password"
//		};
//		http
//			.authorizeRequests(a -> a
//				.antMatchers("/admin/auth/valid/password").permitAll()
//				.antMatchers(HttpMethod.GET).permitAll()
//				.anyRequest().authenticated()
//			)
////			.authorizeRequests()
////			.and()
////			.csrf().disable()
////			.httpBasic().disable()
////			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////			.and()
////			.exceptionHandling().authenticationEntryPoint(new CommonAuthEntryPoint())
////			.and()
////			.authorizeRequests()
////			.antMatchers(HttpMethod.GET, GET_WHITELIST).permitAll() // ?????? GET URL??? ?????? ??????
////			.antMatchers(HttpMethod.POST, POST_WHITELIST).permitAll() // ?????? POST URL??? ?????? ??????
////			.anyRequest().permitAll()
////			.and()
////			.addFilterBefore(new JwtLoginFilter("/login", authenticationManager(), tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
////			.addFilterBefore(new JwtAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
//			.logout()
//			.logoutUrl("/logout")
//			.addLogoutHandler(logoutHandler)
//			.logoutSuccessHandler(this.defaultLogoutSuccessHandler())
//			.invalidateHttpSession(true);
//	}
//
//	private LogoutSuccessHandler defaultLogoutSuccessHandler() {
//		return (request, response, authentication) ->
//			response.sendError(HttpStatus.UNAUTHORIZED.value(), "logout");
//	}
//}
