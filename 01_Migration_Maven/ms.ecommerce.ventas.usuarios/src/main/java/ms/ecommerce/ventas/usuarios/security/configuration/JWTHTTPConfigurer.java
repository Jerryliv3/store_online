package ms.ecommerce.ventas.usuarios.security.configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ms.ecommerce.ventas.usuarios.security.constants.Constants;
import ms.ecommerce.ventas.usuarios.security.filter.JWTAuthenticationFilter;
import ms.ecommerce.ventas.usuarios.security.utils.JWTUtils;

@RequiredArgsConstructor
public class JWTHTTPConfigurer extends AbstractHttpConfigurer<JWTHTTPConfigurer, HttpSecurity> {

	private final JWTUtils jwtUtils;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		final AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
		http.addFilter(new JWTAuthenticationFilter(authenticationManager, jwtUtils));
	}

}
