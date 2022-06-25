package pe.edu.galaxy.training.java.ms.ms.gestion.pedidos.service.client;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@JsonPropertyOrder(value = {"id","razonSocial","ruc","direccion","telefono"/*,"correo"*/})
public class ClienteDTO{

	private Long id;
	
	private String razonSocial;
	
	private String ruc;

	private String direccion;

	private String telefono;
	
	//private String correo;
	
}
