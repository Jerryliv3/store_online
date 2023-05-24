package ms.ecommerce.ventas.personas.api.entity;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import lombok.Data;
import ms.ecommerce.ventas.personas.api.dto.PaginadoDTO;

@Data
@XmlRootElement(name = "PersonaEntity")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonaEntity implements Serializable{

	private static final long serialVersionUID = -2170897015344177815L; 

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
