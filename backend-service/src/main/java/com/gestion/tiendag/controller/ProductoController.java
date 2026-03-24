package com.gestion.tiendag.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.tiendag.service.ProductoServiceClient;

@RestController
@RequestMapping("productos")
public class ProductoController {

	@Autowired
	private ProductoServiceClient productoServiceClient;

	@GetMapping("/listar")
	public List<?> verProductos() {
		return productoServiceClient.obtenerProductos();
	}

	@PostMapping("/guardar")
	public Object guardarProducto(@RequestBody Map<String, Object> payload) {
		return productoServiceClient.guardarProducto(payload);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscarProducto(@PathVariable Long id) {
		Object producto = productoServiceClient.obtenerProducto(id);
		return ResponseEntity.ok(producto);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
		Object actualizado = productoServiceClient.actualizarProducto(id, payload);
		return ResponseEntity.ok(actualizado);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable Long id) {
		productoServiceClient.eliminarProducto(id);

		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado", Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}

}
