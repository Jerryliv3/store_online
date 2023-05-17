package ms.ecommerce.ventas.usuarios.security.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "LogginEntity")
@XmlAccessorType(XmlAccessType.FIELD)
public class LogginEntity {

	private String user;
	private String password;
	
}
