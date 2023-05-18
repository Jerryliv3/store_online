package ms.ecommerce.ventas.usuarios.security.filter;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import ms.ecommerce.ventas.usuarios.security.utils.JWTUtils;
import static ms.ecommerce.ventas.usuarios.security.constants.Constants.*;

@Slf4j
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("doFilterInternal...");
		try {
			String jwt = jwtUtils.parseJwt(request);
			//log.info("jwt  {}", jwt);
			
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {

				String username = jwtUtils.getUserNameFromJwtToken(jwt);
				//log.info("username {}", username);
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, userDetails.getPassword(), userDetails.getAuthorities());

				String token = jwtUtils.generateJwtToken(authentication, false);

				log.info("token doFilterInternal " + token);

				response.addHeader("Access-Control-Expose-Headers", "Authorization");

				response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + " " + token);
				//log.info("authentication {}", authentication);
				 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			log.error("Cannot set user authentication: {}", e);
			throw new ServletException();
		}

		filterChain.doFilter(request, response);
	}

	
}