package ms.ecommerce.ventas.usuarios.security.controller;

import static ms.ecommerce.ventas.usuarios.security.commons.GlobalMessages.IS_CORRECT_FALSE;
import static ms.ecommerce.ventas.usuarios.security.commons.GobalConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ms.ecommerce.ventas.usuarios.security.controller.response.ResponseRequest;
import ms.ecommerce.ventas.usuarios.security.dto.LogginDTO;
import ms.ecommerce.ventas.usuarios.security.models.Response;
import ms.ecommerce.ventas.usuarios.security.service.ILogginService;


@Slf4j
@RestController
@RequestMapping(API_USUARIO)
public class LogginController {
	
	@Autowired
	private ILogginService logginService;
	
	@PostMapping(API_USUARIO_LOGGIN)
	public ResponseEntity <ResponseRequest> singUp (@RequestBody LogginDTO logginDTO) {
		
		try {
			Response response = logginService.findUser(logginDTO);
			
			return ResponseEntity.ok(
						ResponseRequest
						.builder()
						.data(response.getRowsEntitites())
						.isCorrect(response.getIsCorrect())
						.isBreakOperation(response.getIsBreakOperation())
						.message(response.getMessage())
						.code(response.getIsCorrect().equals(IS_CORRECT_FALSE) ? 0 : 1)
						.build()
					);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

}
