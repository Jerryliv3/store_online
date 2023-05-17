package ms.ecommerce.ventas.usuarios.security.service;

import org.springframework.stereotype.Service;

import ms.ecommerce.ventas.usuarios.security.dto.LogginDTO;
import ms.ecommerce.ventas.usuarios.security.service.service.GenericeService;

@Service
public interface ILogginService extends GenericeService<LogginDTO> {
	
}
