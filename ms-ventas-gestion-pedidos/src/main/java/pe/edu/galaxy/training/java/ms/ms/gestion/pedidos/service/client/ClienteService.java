package pe.edu.galaxy.training.java.ms.ms.gestion.pedidos.service.client;

public interface ClienteService {

	ClienteDTO findById(Long id) throws ClientException; 
}
