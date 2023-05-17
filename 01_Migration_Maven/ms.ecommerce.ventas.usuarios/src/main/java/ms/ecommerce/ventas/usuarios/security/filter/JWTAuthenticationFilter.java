package ms.ecommerce.ventas.usuarios.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import ms.ecommerce.ventas.usuarios.security.constants.Constants;
import ms.ecommerce.ventas.usuarios.security.dto.LogginDTO;
import ms.ecommerce.ventas.usuarios.security.utils.JWTUtils;


@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private JWTUtils jwtUtils;

	private AuthenticationManager authenticationManager;
	

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.jwtUtils=jwtUtils;
	}

	@Override // Login
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		log.info("attemptAuthentication...");
		try {

			log.info("usuario json {}",request.getInputStream());

			LogginDTO usuario = new ObjectMapper().readValue(request.getInputStream(), LogginDTO.class);

			System.out.println("usuario " + usuario);

			UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(usuario.getUser(),
					usuario.getPassword(), new ArrayList<>());

			Authentication aut = authenticationManager.authenticate(upat);

			return aut;

		} catch (IOException e) {
			System.out.println("attemptAuthentication " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		
		String token = jwtUtils.generateJwtToken(auth);

		System.out.println("token " + token);

		response.addHeader("Access-Control-Expose-Headers", Constants.HEADER_AUTHORIZACION_KEY);

		response.addHeader(Constants.HEADER_AUTHORIZACION_KEY, Constants.TOKEN_BEARER_PREFIX + " " + token);

	}

}
