package pl.robotix.clarey.auth;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class AuthorizationEndPointConfiguration extends
		WebSecurityConfigurerAdapter {

	@Autowired
	protected void registerAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
	}

	@Bean
	public UserDetailsService userDetailsService() {

		return new InMemoryUserDetailsManager(

		Arrays.asList(new UserDetails() {
			private static final long serialVersionUID = 1L;

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
				return "user";
			}

			@Override
			public String getPassword() {
				return "psw";
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
		})

		);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().anyRequest().permitAll();
	}
}
