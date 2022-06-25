package pe.edu.galaxy.training.java.ms.ms.gestion.pedidos.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pe.edu.galaxy.training.java.ms.ms.gestion.pedidos.service.client.ClienteDTO;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@JsonPropertyOrder(value = {"id","idCliente","glosa","fechaRegistro","total"})
public class PedidoDTO extends GenericDTO{

	private Long id;
	
	private Long idCliente;
	
	private String glosa;

	private String fechaRegistro;

	private Double total;
	
	private ClienteDTO cliente;
	
}
