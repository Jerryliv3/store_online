package ms.ecommerce.ventas.personas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsEcommerceVentasPersonasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEcommerceVentasPersonasApplication.class, args);
	}

}
