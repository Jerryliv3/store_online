package ms.ecommerce.ventas.personas.api.controller.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Message {
	private Integer code;
	private String message;
	
		
}