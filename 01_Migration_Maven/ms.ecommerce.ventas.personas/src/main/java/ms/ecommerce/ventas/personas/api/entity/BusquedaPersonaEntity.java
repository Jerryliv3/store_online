package ms.ecommerce.ventas.personas.api.entity;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "BusquedaPersonaEntity")
@XmlAccessorType(XmlAccessType.FIELD)
public class BusquedaPersonaEntity implements Serializable {
	
	/**
	 * 
	 */
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
	private int numeroPagina;
	private int dimensionPagina;
	private String origen;

}
