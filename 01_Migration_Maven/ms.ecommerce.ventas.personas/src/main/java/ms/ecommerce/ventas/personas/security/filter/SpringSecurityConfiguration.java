package ms.ecommerce.ventas.personas.security.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
	
	
	private final UserDetailsService userDetailsService; // Esto extrae el objeto usuario con roles
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder; // Esto extraé el mecanismo de cifrado
	

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable(); // Solo para uso en desarrollo
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				
		http.authorizeHttpRequests(
				authorize ->	authorize.requestMatchers("/public/**").permitAll());
		
		http.authorizeHttpRequests(
				authorize ->	authorize
								 		.requestMatchers("/v1/**", "/images/**").hasAnyRole("ADMINISTRADOR","USUARIO")
								 		.anyRequest()
								 		.authenticated()
		);
		
		http.authenticationProvider(authenticationProvider());
		
		http.addFilterBefore(jWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
	
	@Bean
	JWTAuthorizationFilter jWTAuthorizationFilter() {
		return new JWTAuthorizationFilter();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() { // Este metodo decide si autentifica o no el usuario
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(bCryptPasswordEncoder);
		return authProvider; 
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				//.allowedOrigins("*")
				.allowedOrigins("http://localhost:4200", "http://localhost:8086")
				//.allowedMethods("*");
				.allowedMethods("GET", "POST", "PUT", /*"DELETE"*/"HEAD", "OPTIONS")
				.allowedHeaders("Access-Control-Allow-Headers", "Authorization",
				"Access-Control-Allow-Origin", "Cache-Control", "Content-Type")
				.exposedHeaders("Access-Control-Allow-Headers", "Authorization",
				"Access-Control-Allow-Origin", "Cache-Control", "Content-Type");
			}
		};
	}
	
}
