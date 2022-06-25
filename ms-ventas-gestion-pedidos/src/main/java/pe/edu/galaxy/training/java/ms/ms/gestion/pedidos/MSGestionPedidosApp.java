package pe.edu.galaxy.training.java.ms.ms.gestion.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@RefreshScope
@SpringBootApplication
public class MSGestionPedidosApp {

	public static void main(String[] args) {
		SpringApplication.run(MSGestionPedidosApp.class, args);
	}

}
