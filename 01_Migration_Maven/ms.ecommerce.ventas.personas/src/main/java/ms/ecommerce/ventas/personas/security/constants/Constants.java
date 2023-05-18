package ms.ecommerce.ventas.personas.security.constants;

public class Constants {
  
	//Spring Security

	 public static final String LOGIN_URL = 									"/login";
	 public static final String REFRESH_TOKEN_URL =			 		"/refreshtoken";
	 
	 public static final String HEADER_AUTHORIZACION_KEY 	= 	"Authorization";
	 public static final String HEADER_REFRESH_TOKEN_KEY 	= 	"RefreshToken";
	 public static final String TOKEN_BEARER_PREFIX 		= 			"Bearer ";
	 public static final String AUTHORITIES					= 				"authorities";

	 // JWT

	 public static final String ISSUER_INFO = 									"usuario";
	 
	 //https://www.allkeysgenerator.com/ Encryption key 512-bit
	 
	 public static final String SUPER_SECRET_KEY = 						"q3t6w9z$C&F)J@NcRfTjWnZr4u7x!A%D*G-KaPdSgVkXp2s5v8y/B?E(H+MbQeTh"; //123
	 
	 public static final long 	TOKEN_EXPIRATION_TIME_TOKEN = 1_900_000; // 1 day  86_400_000

	 public static final long 	TOKEN_EXPIRATION_TIME_REFRESH_TOKEN = 900_000; // 1 day  86_400_000


}
