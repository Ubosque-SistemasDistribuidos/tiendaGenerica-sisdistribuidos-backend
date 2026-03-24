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

import com.gestion.tiendag.service.ClienteServiceClient;


@RestController
@RequestMapping("cliente")
public class ClienteController {
	
	@Autowired
	private ClienteServiceClient clienteServiceClient;
	
	@GetMapping("/listar")
	public List<?> verClientes(){
		return clienteServiceClient.obtenerClientes();
	}
	
	@PostMapping("/guardar")
	public Object guardarCliente(@RequestBody Object cliente) {
		return clienteServiceClient.guardarCliente(cliente);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscarCliente(@PathVariable Long id){
		Object cliente = clienteServiceClient.obtenerCliente(id);
		return ResponseEntity.ok(cliente);
	}
	
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarCliente(@PathVariable Long id, @RequestBody Object clienteR){
		Object clienteActualizado = clienteServiceClient.actualizarCliente(id, clienteR);
		return ResponseEntity.ok(clienteActualizado);
		
	}
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarCliente(@PathVariable Long id){
		clienteServiceClient.eliminarCliente(id);
		Map<String,Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}
	
}
