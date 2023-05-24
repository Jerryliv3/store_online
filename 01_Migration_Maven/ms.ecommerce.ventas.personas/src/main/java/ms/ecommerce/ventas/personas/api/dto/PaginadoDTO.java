package ms.ecommerce.ventas.personas.api.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PaginadoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long numeroPagina;
	private Long dimensionPagina;
	private Object tipoEntidad;
	
}
