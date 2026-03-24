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

import com.gestion.tiendag.service.VentaServiceClient;

@RestController
@RequestMapping("ventas")
public class VentaController {

	@Autowired
	private VentaServiceClient ventaServiceClient;
	
	@GetMapping("/listar")
	public List<?> verVentas(){
		return ventaServiceClient.obtenerVentas();
	}
	
	@PostMapping("/guardar")
	public Object guardarVenta(@RequestBody Map<String, Object> payload) {
		return ventaServiceClient.guardarVenta(payload);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscarVenta(@PathVariable Long id){
		Object venta = ventaServiceClient.obtenerVenta(id);
		return ResponseEntity.ok(venta);
	}
	
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarVenta(@PathVariable Long id, @RequestBody Object ventaR){
		Object ventaActualizada = ventaServiceClient.actualizarVenta(id, ventaR);
		return ResponseEntity.ok(ventaActualizada);
		
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarVenta(@PathVariable Long id){
		ventaServiceClient.eliminarVenta(id);
		Map<String,Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}
}
