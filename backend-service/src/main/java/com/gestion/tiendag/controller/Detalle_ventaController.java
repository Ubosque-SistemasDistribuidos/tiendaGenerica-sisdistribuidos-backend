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

import com.gestion.tiendag.service.DetalleVentaServiceClientImpl;

@RestController
@RequestMapping("detalle_ventas")
public class Detalle_ventaController {
	@Autowired
	private DetalleVentaServiceClientImpl detalleVentaServiceClient;
	
	@GetMapping("/listar")
	public List<?> verClientes(){
		return detalleVentaServiceClient.obtenerDetallesVenta();
	}
	
	@PostMapping("/guardar")
	public Object guardarDetalle_venta(@RequestBody Object detalle) {
		return detalleVentaServiceClient.guardarDetalleVenta(detalle);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscarDetalle_venta(@PathVariable Long id){
		Object detalle = detalleVentaServiceClient.obtenerDetalleVenta(id);
		return ResponseEntity.ok(detalle);
	}
	
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarCliente(@PathVariable Long id, @RequestBody Object detalleR){
		Object detalleActualizado = detalleVentaServiceClient.actualizarDetalleVenta(id, detalleR);
		return ResponseEntity.ok(detalleActualizado);
		
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarDetalle_venta(@PathVariable Long id){
		detalleVentaServiceClient.eliminarDetalleVenta(id);
		Map<String,Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}
}
