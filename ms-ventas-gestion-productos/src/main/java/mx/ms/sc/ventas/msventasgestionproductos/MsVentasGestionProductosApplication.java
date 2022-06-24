package mx.ms.sc.ventas.msventasgestionproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsVentasGestionProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsVentasGestionProductosApplication.class, args);
	}

}
