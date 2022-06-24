package mx.ms.sc.ventas.msventasgestionproductos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.json.JsonMapper;

import mx.ms.sc.ventas.msventasgestionproductos.dto.ClienteDTO;
import mx.ms.sc.ventas.msventasgestionproductos.entity.ClienteEntity;
import mx.ms.sc.ventas.msventasgestionproductos.repository.ClienteRepository;
import mx.ms.sc.ventas.msventasgestionproductos.service.exception.ServiceException;

@Service
public class ClienteServiceImpl implements ClienteService{
 
	private ClienteRepository clienteRepository;
	private JsonMapper jsonMapper;
	
	public ClienteServiceImpl(	ClienteRepository productoRepository,
								JsonMapper jsonMapper
									) {
		super();
		this.clienteRepository = productoRepository;
		this.jsonMapper=jsonMapper;
	}

	@Override
	public List<ClienteDTO> findByLike(ClienteDTO t) throws ServiceException {
		try {
			List<ClienteEntity> lstProductoEntity= clienteRepository.findAll();
			return lstProductoEntity.stream().map(e -> this.getClienteDTO(e))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public Optional<ClienteDTO> findById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	// Mappers
	private ClienteDTO getClienteDTO(ClienteEntity e) {
		return jsonMapper.convertValue(e, ClienteDTO.class);
	}
	
	private ClienteEntity getClienteEntity(ClienteDTO d) {
		return jsonMapper.convertValue(d, ClienteEntity.class);
	}

	@Override
	public ClienteDTO save(ClienteDTO t) throws ServiceException {
		
		return this.getClienteDTO(this.clienteRepository.save(this.getClienteEntity(t)));
	}
	
}
