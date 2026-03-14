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

import com.gestion.tiendag.model.Proveedor;
import com.gestion.tiendag.service.ProveedorServiceClient;

@RestController
@RequestMapping("proveedores")
public class ProveedorController {
	@Autowired
	private ProveedorServiceClient proveedorServiceClient;
	
	@GetMapping("/listar")
	public List<?> verProveedores(){
		return proveedorServiceClient.obtenerProveedores();
	}
	
	@PostMapping("/guardar")
	public Object guardarProveedor(@RequestBody Proveedor Proveedor) {
		return proveedorServiceClient.guardarProveedor(Proveedor);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscarProveedor(@PathVariable Long id){
		Object proveedor = proveedorServiceClient.obtenerProveedor(id);
		return ResponseEntity.ok(proveedor);
	}
	
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedorR){
		Object proveedorActualizado = proveedorServiceClient.actualizarProveedor(id, proveedorR);
		return ResponseEntity.ok(proveedorActualizado);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarProveedor(@PathVariable Long id){
		proveedorServiceClient.eliminarProveedor(id);
		Map<String,Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}
}
