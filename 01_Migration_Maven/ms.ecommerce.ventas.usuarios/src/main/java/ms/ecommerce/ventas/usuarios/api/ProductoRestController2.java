package ms.ecommerce.ventas.usuarios.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/public/usuario")
public class ProductoRestController2 {
	
	@GetMapping
	public ResponseEntity<ProductoRecord> getProducto(){
		return ResponseEntity.ok(new ProductoRecord(100, "Producto demo 1000"));		
	}

}
