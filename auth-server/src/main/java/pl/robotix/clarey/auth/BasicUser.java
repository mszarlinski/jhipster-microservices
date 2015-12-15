package pl.robotix.clarey.auth;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class BasicUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String username;

	private String password;
	
	
	public BasicUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new GrantedAuthority() {
				private static final long serialVersionUID = 1L;
			
			@Override
			public String getAuthority() {
				return "ROLE_USER";
			}
		});
	}

}
