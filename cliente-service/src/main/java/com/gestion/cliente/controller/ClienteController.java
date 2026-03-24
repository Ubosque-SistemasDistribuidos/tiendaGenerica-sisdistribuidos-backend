package com.gestion.cliente.controller;

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

import com.gestion.cliente.exception.ResourceNotFoundException;
import com.gestion.cliente.model.Cliente;
import com.gestion.cliente.repository.ClienteRepository;


@RestController
@RequestMapping("cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/listar")
	public List<Cliente> verClientes(){
		return clienteRepository.findAll();
	}
	
	@PostMapping("/guardar")
	public Cliente guardarCliente(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id){
		Cliente cliente=clienteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El cliente no se encuentra con el id: "+id));
		return ResponseEntity.ok(cliente);
	}
	
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteR){
		Cliente cliente=clienteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El cliente no se encuentra con el id: "+id));
		
		cliente.setNombre(clienteR.getNombre());
		cliente.setDireccion(clienteR.getDireccion());
		cliente.setEmail(clienteR.getEmail());
		cliente.setTelefono(clienteR.getTelefono());
		
		Cliente clienteActualizado = clienteRepository.save(cliente);
		return ResponseEntity.ok(clienteActualizado);
		
	}
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarCliente(@PathVariable Long id){
		Cliente cliente=clienteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El cliente no se encuentra con el id: "+id));
		clienteRepository.delete(cliente);
		Map<String,Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}
	
}
