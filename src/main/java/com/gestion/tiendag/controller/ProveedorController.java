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

import com.gestion.tiendag.exception.ResourceNotFoundException;
import com.gestion.tiendag.model.Proveedor;
import com.gestion.tiendag.repository.ProveedorRepository;

@RestController
@RequestMapping("proveedores")
public class ProveedorController {
	@Autowired
	private ProveedorRepository ProveedorRepository;
	
	@GetMapping("/listar")
	public List<Proveedor> verProveedores(){
		return ProveedorRepository.findAll();
	}
	
	@PostMapping("/guardar")
	public Proveedor guardarProveedor(@RequestBody Proveedor Proveedor) {
		return ProveedorRepository.save(Proveedor);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Proveedor> buscarProveedor(@PathVariable Long id){
		Proveedor Proveedor=ProveedorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El proveedor no se encuentra con el id: "+id));
		return ResponseEntity.ok(Proveedor);
	}
	
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedorR){
		Proveedor proveedor=ProveedorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El proveedor no se encuentra con el id: "+id));
		
		proveedor.setCiudadProveedor(proveedorR.getCiudadProveedor());
		proveedor.setDireccionProveedor(proveedorR.getDireccionProveedor());
		proveedor.setNombreProveedor(proveedorR.getNombreProveedor());
		proveedor.setTelefonoProveedor(proveedorR.getTelefonoProveedor());
		
		Proveedor ProveedorActualizado = ProveedorRepository.save(proveedor);
		return ResponseEntity.ok(ProveedorActualizado);
		
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarProveedor(@PathVariable Long id){
		Proveedor Proveedor=ProveedorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El Proveedor no se encuentra con el id: "+id));
		ProveedorRepository.delete(Proveedor);
		Map<String,Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}
}
