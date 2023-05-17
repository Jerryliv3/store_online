package ms.ecommerce.ventas.usuarios.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;

@Configuration
public class SecurityGlobalConfiguration {

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	@Bean
	JsonMapper getJsonMapper() {
		return new JsonMapper();
	}
	
	@Bean
	Gson getGson () {
		return new Gson(); 
	}


}
