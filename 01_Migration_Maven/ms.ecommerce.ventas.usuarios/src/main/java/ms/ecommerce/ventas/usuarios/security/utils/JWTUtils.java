package ms.ecommerce.ventas.usuarios.security.utils;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import org.springframework.util.StringUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import ms.ecommerce.ventas.usuarios.security.constants.Constants;

@Slf4j
@Component
public class JWTUtils {

	public String generateJwtToken(Authentication authentication) {

		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		
		Collection<? extends GrantedAuthority> authorities=userPrincipal.getAuthorities();
		Collection<?> authoritiesItems= authorities.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		
		String token = Jwts
						.builder()
						.setIssuedAt(new Date())
						.setIssuer(Constants.ISSUER_INFO)
						.setSubject(userPrincipal.getUsername())
						.setExpiration(new Date(System.currentTimeMillis() + Constants.TOKEN_EXPIRATION_TIME))
						.claim(Constants.AUTHORITIES, authoritiesItems)
						.signWith(SignatureAlgorithm.HS512, Constants.SUPER_SECRET_KEY)
						.compact();
		
		//log.info("token -> {}",token);
		
		return token;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(Constants.SUPER_SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		System.out.println("authToken "+authToken);
		try {
			Jwts.parser().setSigningKey(Constants.SUPER_SECRET_KEY).parseClaimsJws(authToken);
			return true;
		} catch (Exception e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
		}

		return false;
	}
	
	public String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}
}