package pl.robotix.clarey.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends
		AuthorizationServerConfigurerAdapter {

	private static final int FIVE_MINUTES = 300;
	

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
			throws Exception {

		endpoints.tokenStore(tokenStore()).authenticationManager(
				authenticationManager);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer)
			throws Exception {
		oauthServer.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients)
			throws Exception {
		clients.inMemory()
				.withClient("dupa")
				.scopes("read", "write")
				.authorities(AuthoritiesConstants.ADMIN,
						AuthoritiesConstants.USER)
				.authorizedGrantTypes("password", "refresh_token",
						"authorization_code", "implicit")
				.secret("hockiklocki")
				.accessTokenValiditySeconds(FIVE_MINUTES);
	}
	
}
