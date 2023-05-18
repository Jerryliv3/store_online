package ms.ecommerce.ventas.personas.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;

@Configuration
public class GlobalConfigutation {

	public GlobalConfigutation() {
		
	}
	
	/*    -- Se comentan porque estos dos ya se encuentran en el paquete de Security
	@Bean
	JsonMapper getJsonMapper() {
		return new JsonMapper();
	}
	
	@Bean
	Gson getGson () {
		return new Gson(); 
	}
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	*/
}
