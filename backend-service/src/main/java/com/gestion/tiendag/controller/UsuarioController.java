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

import com.gestion.tiendag.service.UsuarioServiceClient;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioServiceClient usuarioServiceClient;

	@GetMapping("/listar")
	public List<?> verUsuarios() {
		return usuarioServiceClient.obtenerUsuarios();
	}

	@PostMapping("/guardar")
	public Object guardarUsuario(@RequestBody Map<String, Object> payload) {
		return usuarioServiceClient.guardarUsuario(payload);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscarUsuario(@PathVariable Long id) {
		Object usuario = usuarioServiceClient.obtenerUsuario(id);
		return ResponseEntity.ok(usuario);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarUsuario(@PathVariable Long id,
			@RequestBody Map<String, Object> payload) {
		Object actualizado = usuarioServiceClient.actualizarUsuario(id, payload);
		return ResponseEntity.ok(actualizado);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, Boolean>> eliminarUsuario(@PathVariable Long id) {
		usuarioServiceClient.eliminarUsuario(id);

		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado", Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}

}
