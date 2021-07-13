package edu.ucacue.factura.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.ucacue.factura.infraestructura.repositorio.ProductoRepository;
import edu.ucacue.factura.modelo.Producto;

@CrossOrigin //No olvidar de implementar en sus proyectos
@RestController
@RequestMapping("/api")
public class ProductoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	ProductoRepository productoRepository; // Repositorio es el encargado de Manipular los datos

	@GetMapping("/productos")
	@ResponseStatus(HttpStatus.OK)
	public List<Producto> getProductos() {
		List<Producto> productos = new ArrayList<Producto>();
		productos = productoRepository.findAll();
		return productos;
	}
	
	@GetMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Producto getProductosById(@PathVariable int id) {
		
		return productoRepository.findById(id).get();

	}


	@PostMapping("/producto")
	public ResponseEntity<?> guardarProducto(@RequestBody Producto producto, BindingResult result )
	{
	
		Map<String,Object> respuesta = new HashMap<>();
		
		if(result.hasErrors())
		{
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			respuesta.put("Error de enlance con el backend", errors);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}
		
		try {
		productoRepository.save(producto);
		}catch(DataAccessException e)
		{
			respuesta.put("mensaje", "Error al realizar el insert en la base de datos");
			respuesta.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		respuesta.put("mensaje", "El producto ha sido creado con éxito!");
		respuesta.put("producto", producto);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
		
		

	}

}
