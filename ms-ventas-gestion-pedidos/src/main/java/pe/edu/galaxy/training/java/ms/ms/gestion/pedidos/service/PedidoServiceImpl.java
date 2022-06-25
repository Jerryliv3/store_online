package pe.edu.galaxy.training.java.ms.ms.gestion.pedidos.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.ms.ms.gestion.pedidos.dto.PedidoDTO;
import pe.edu.galaxy.training.java.ms.ms.gestion.pedidos.entity.PedidoEntity;
import pe.edu.galaxy.training.java.ms.ms.gestion.pedidos.repository.PedidoRepository;
import pe.edu.galaxy.training.java.ms.ms.gestion.pedidos.service.client.ClienteService;
import pe.edu.galaxy.training.java.ms.ms.gestion.pedidos.service.exception.ServiceException;

@Slf4j
@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private JsonMapper 			jsonMapper;

	@Autowired
	private PedidoRepository 	pedidoRepository;
	
	@Autowired
	private ClienteService	 	clienteService;

	@Override
	public List<PedidoDTO> findLike(PedidoDTO pedidoDTO) throws ServiceException {
		try {
			return this.pedidoRepository.getAllActivos().stream().map(e -> this.getPedidoDTO(e))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<PedidoDTO> findById(PedidoDTO pedidoDTO) throws ServiceException {
		try {
			//log.info("id "+pedidoDTO.getId());
			Optional<PedidoEntity> pedidoEntity= this.pedidoRepository.findById(pedidoDTO.getId());
			if (pedidoEntity.isPresent()) {
				//log.info("isPresent... "+pedidoEntity.get());
				
				PedidoDTO oPedidoDTO=this.getPedidoDTO(pedidoEntity.get());
				if (!Objects.isNull(oPedidoDTO)) {
					oPedidoDTO.setCliente(clienteService.findById(oPedidoDTO.getIdCliente()));
				}

				 return Optional.of(oPedidoDTO);
			}
			//log.info("empty... ");
			return Optional.empty();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public PedidoDTO save(PedidoDTO pedidoDTO) throws ServiceException {
		return null;
	}

	// Mappers

	private PedidoDTO getPedidoDTO(PedidoEntity pedidoEntity) {
		return jsonMapper.convertValue(pedidoEntity, PedidoDTO.class);
	}

}
