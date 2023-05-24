package ms.ecommerce.ventas.personas.api.entity;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "PaginadoEntity")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaginadoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long numeroPagina;
	private Long dimensionPagina;
	private Object tipoEntidad;
	
}
