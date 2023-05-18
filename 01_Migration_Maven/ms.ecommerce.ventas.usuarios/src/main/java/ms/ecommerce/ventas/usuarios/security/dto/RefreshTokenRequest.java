package ms.ecommerce.ventas.usuarios.security.dto;

import lombok.Data;

@Data
public class RefreshTokenRequest {
	private String token;
}
