package ms.ecommerce.ventas.personas.security.controller.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ResponseRequest {
	
	private Object data;
	private String message;
	@Default
	private String type = "Bearer";
	private String token;
	private String refreshToken;
	private String isCorrect;
	private String isBreakOperation;

	private int code;

}
