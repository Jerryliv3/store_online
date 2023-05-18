package ms.ecommerce.ventas.usuarios.security.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(value = Include.NON_NULL)
public class TokenResponse {

	@Default
	private String type = "Bearer";
	private String token;
	private String refreshToken;
	private String user;
	private List<String> authorities;
}
