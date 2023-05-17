package ms.ecommerce.ventas.personas.controller;

import static ms.ecommerce.ventas.personas.commons.GlobalMessages.IS_CORRECT_FALSE;
import static ms.ecommerce.ventas.personas.commons.GobalConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;
import ms.ecommerce.ventas.personas.dto.RegistroRolDTO;
import ms.ecommerce.ventas.personas.service.IRolService;
import ms.ecommerce.ventas.personas.controller.response.ResponseRequest;
import ms.ecommerce.ventas.personas.models.Response;

@Slf4j
@RestController
@RequestMapping(API_PERSON)
public class RolController {
	
	@Autowired
	private IRolService rolService;
	
	@PostMapping(API_SAVE_ROL)
	public ResponseEntity<ResponseRequest> save (@RequestBody RegistroRolDTO redistroRolDTO) {
		try {
			
			Response response = rolService.save(redistroRolDTO);
			
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
			
		} catch ( Exception e ) {
			log.error("Error interno del API: ", e.toString());
			return ResponseEntity.internalServerError().build();
		}
	}

}
