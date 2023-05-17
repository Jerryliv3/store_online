package ms.ecommerce.ventas.usuarios.security.controller.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseRequest {
	private String isCorrect;
	private String isBreakOperation;
	private String message;
	private int code;
	private Object data;
}
