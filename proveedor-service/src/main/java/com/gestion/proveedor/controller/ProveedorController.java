package com.gestion.proveedor.controller;

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

import com.gestion.proveedor.exception.ResourceNotFoundException;
import com.gestion.proveedor.model.Proveedor;
import com.gestion.proveedor.repository.ProveedorRepository;

@RestController
@RequestMapping("proveedores")
public class ProveedorController {
	
	@Autowired
	private ProveedorRepository proveedorRepository;
	
	@GetMapping("/listar")
	public List<Proveedor> verProveedores(){
		return proveedorRepository.findAll();
	}
	
	@PostMapping("/guardar")
	public Proveedor guardarProveedor(@RequestBody Proveedor proveedor) {
		return proveedorRepository.save(proveedor);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Proveedor> buscarProveedor(@PathVariable Long id){
		Proveedor proveedor = proveedorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El proveedor no se encuentra con el id: " + id));
		return ResponseEntity.ok(proveedor);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedorR){
		Proveedor proveedor = proveedorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El proveedor no se encuentra con el id: " + id));
		
		proveedor.setNombreProveedor(proveedorR.getNombreProveedor());
		proveedor.setCiudadProveedor(proveedorR.getCiudadProveedor());
		proveedor.setDireccionProveedor(proveedorR.getDireccionProveedor());
		proveedor.setTelefonoProveedor(proveedorR.getTelefonoProveedor());
		
		Proveedor proveedorActualizado = proveedorRepository.save(proveedor);
		return ResponseEntity.ok(proveedorActualizado);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, Boolean>> eliminarProveedor(@PathVariable Long id){
		Proveedor proveedor = proveedorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El proveedor no se encuentra con el id: " + id));
		
		proveedorRepository.delete(proveedor);
		Map<String, Boolean> response = new HashMap<>();
		response.put("eliminado", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
