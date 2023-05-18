package ms.ecommerce.ventas.usuarios.security.utils;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import org.springframework.util.StringUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import static ms.ecommerce.ventas.usuarios.security.constants.Constants.*;

@Slf4j
@Component
public class JWTUtils {

	@Autowired
	private UserDetailsService userDetailsService;

	public String generateJwtToken(Authentication authentication, Boolean swRefreshToken) {

		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

		Collection<? extends GrantedAuthority> authorities = userPrincipal.getAuthorities();

		// System.out.println("Authorities -> "+ authorities);

		Long time = (!swRefreshToken) ? TOKEN_EXPIRATION_TIME_TOKEN : TOKEN_EXPIRATION_TIME_REFRESH_TOKEN;

		Collection<?> authoritiesItems = authorities.stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
				.setSubject(userPrincipal.getUsername()).setExpiration(new Date(System.currentTimeMillis() + time))
				.claim(AUTHORITIES, authoritiesItems).signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY).compact();

		System.out.println("token -> " + token);
		return token;
	}

	public String generateJwtFromTokenRefresh(String refreshToken) {
		String username = this.getUserNameFromJwtToken(refreshToken);
		log.info("username {}", username);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
				.setSubject(userDetails.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME_REFRESH_TOKEN))
				.claim(AUTHORITIES, userDetails.getAuthorities()).signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY)
				.compact();

		System.out.println("token -> " + token);
		return token;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(SUPER_SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) throws Exception {
		System.out.println("authToken " + authToken);
		try {
			Jwts.parser().setSigningKey(SUPER_SECRET_KEY).parseClaimsJws(authToken);
			return true;
		} catch (Exception e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
			throw new Exception();
		}

	}

	public String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader(HEADER_AUTHORIZACION_KEY);
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(TOKEN_BEARER_PREFIX)) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}
}