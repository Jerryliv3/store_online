package pe.edu.galaxy.training.java.ms.ms.gestion.pedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenericDTO {

	@Default
	private String estado = "1";

}
