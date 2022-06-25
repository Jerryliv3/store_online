package pe.edu.galaxy.training.java.ms.ms.gestion.pedidos.service.client;

import java.util.List;
import java.util.Objects;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClienteServiceImpl implements ClienteService{

	
	private DiscoveryClient discoveryClient;
	
	private RestTemplate 	restTemplate;
	
	private String url="ms-gestion-clientes";
	
	public ClienteServiceImpl (RestTemplate restTemplate,DiscoveryClient discoveryClient) {
		this.discoveryClient=discoveryClient;
		this.restTemplate = restTemplate;
		//log.info("this.getURI() "+this.getURI());
		
	}

	@Override
	public ClienteDTO findById(Long id) throws ClientException {
		//log.info("this.getURI() "+this.getURI());
		
		ResponseEntity<ClienteDTO> rEClienteDTO=restTemplate.getForEntity(this.getURI()+"/v1/clientes/"+id, ClienteDTO.class);
	
		if (!Objects.isNull(rEClienteDTO)) {
			return rEClienteDTO.getBody();
		}
		return null;
	}
	
	
	private String getURI() {
		if (Objects.isNull(discoveryClient)) {
			//log.info("discoveryClient is null");
			return "";
		}
		List<ServiceInstance> instances = discoveryClient.getInstances(url);

		if (Objects.isNull(instances) || instances.isEmpty()) return "not found";
		System.out.println(instances.get(0).getHost());
		String uri=instances.get(0).getUri().toString();		
		//log.info("uri" +uri);
		return uri;
	}

}
