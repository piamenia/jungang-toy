package hoon.pepper.common.config.security.jwt;

import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.Collection;


@Log4j2
@ToString(callSuper = true)
public class AuthenticationTokenImpl extends AbstractAuthenticationToken {

	@Setter
	private String accountId;

	public AuthenticationTokenImpl(String principal, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.accountId = principal;
	}

	@Override
	public Object getCredentials() {
		return "";
	}

	@Override
	public Object getPrincipal() {
		return StringUtils.isEmpty(accountId) ? "" : accountId;
	}

}
