package ms.ecommerce.ventas.personas.api.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PersonaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private String segundoNombre;
	private String apellidoPaterno;  
	private String apellidoMaterno;  
	private String rfc;
	private String fechaNacimiento; 
	private String fechaRegistro;
	private int sexoId;
	private int estadoCivilId;
	private int estatusId;
	private int tipoPersonaId;
	private PaginadoDTO paginado;

}
