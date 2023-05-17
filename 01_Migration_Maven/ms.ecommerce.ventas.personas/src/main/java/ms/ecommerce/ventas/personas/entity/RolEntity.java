package ms.ecommerce.ventas.personas.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@Entity
@XmlRootElement(name = "RolEntity")
@XmlAccessorType(XmlAccessType.FIELD)
public class RolEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long rolId;
	private Long usuarioId;
}
