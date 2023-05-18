package ms.ecommerce.ventas.personas.security.service;

import org.springframework.stereotype.Service;

import ms.ecommerce.ventas.personas.security.dto.LogginDTO;
import ms.ecommerce.ventas.personas.security.service.service.GenericeService;

@Service
public interface ILogginService extends GenericeService<LogginDTO> {
	
}
