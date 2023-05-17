package ms.ecommerce.ventas.personas.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;

@Configuration
public class GlobalConfigutation {

	public GlobalConfigutation() {
		
	}
	
	@Bean
	public JsonMapper getJsonMapper() {
		return new JsonMapper();
	}
	
	@Bean
	public Gson getGson () {
		return new Gson(); 
	}

}
