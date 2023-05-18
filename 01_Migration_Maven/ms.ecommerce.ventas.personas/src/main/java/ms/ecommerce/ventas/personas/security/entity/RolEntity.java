package ms.ecommerce.ventas.personas.security.entity;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "RolEntity")
@XmlAccessorType(XmlAccessType.FIELD)
public class RolEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long rolId;
	private Long usuarioId;
	private String rol;
}
