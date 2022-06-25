package pe.edu.galaxy.training.java.ms.ms.gestion.pedidos.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "PEDIDO")
@Entity(name = "PedidoEntity")
public class PedidoEntity extends GenericEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPedido")
    @SequenceGenerator(sequenceName = "SEQ_PEDIDO", allocationSize = 1, name = "seqPedido")
	@Column(name = "ID_PEDIDO")
	private Long id;
	
	@Column(name = "ID_CLIENTE")
	private Long idCliente;
	
	@Column(name = "GLOSA")
	private String glosa;

	@Column(name = "FECHA_REGISTRO")
	private String fechaRegistro;

	@Column(name = "TOTAL")
	private Double total;

}
