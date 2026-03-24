package com.gestion.usuario.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import com.gestion.usuario.exception.ResourceNotFoundException;
import com.gestion.usuario.model.Usuario;
import com.gestion.usuario.repository.UsuarioRepository;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, Object> payload) {
		String usuario = getString(payload, "usuario", "email");
		String password = getString(payload, "password");
		
		if (usuario == null || password == null) {
			Map<String, String> error = new HashMap<>();
			error.put("mensaje", "Usuario y contraseña son requeridos");
			return ResponseEntity.badRequest().body(error);
		}
		
		// Buscar usuario por nombre de usuario o email
		List<Usuario> usuarios = usuarioRepository.findAll().stream()
			.filter(u -> u.getUsuario().equals(usuario) || u.getEmailUsuario().equals(usuario))
			.collect(Collectors.toList());
		
		if (usuarios.isEmpty()) {
			Map<String, String> error = new HashMap<>();
			error.put("mensaje", "Usuario o contraseña incorrectos");
			return ResponseEntity.status(401).body(error);
		}
		
		Usuario usuarioEncontrado = usuarios.get(0);
		
		// Validar contraseña
		if (!usuarioEncontrado.getPassword().equals(password)) {
			Map<String, String> error = new HashMap<>();
			error.put("mensaje", "Usuario o contraseña incorrectos");
			return ResponseEntity.status(401).body(error);
		}
		
		// Retornar datos del usuario sin la contraseña
		Map<String, Object> respuesta = toFrontendSinPassword(usuarioEncontrado);
		respuesta.put("autenticado", true);
		return ResponseEntity.ok(respuesta);
	}

	@GetMapping("/listar")
	public List<Map<String, Object>> verUsuarios() {
		return usuarioRepository.findAll().stream().map(this::toFrontend).collect(Collectors.toList());
	}

	@PostMapping("/guardar")
	public Map<String, Object> guardarUsuario(@RequestBody Map<String, Object> payload) {
		Usuario usuario = new Usuario();
		applyPayload(usuario, payload, true);
		Usuario guardado = usuarioRepository.save(usuario);
		System.out.println("Usuario guardado");
		return toFrontend(guardado);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<Map<String, Object>> buscarUsuario(@PathVariable Long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El usuario no se encuentra con el id: " + id));
		return ResponseEntity.ok(toFrontend(usuario));
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Map<String, Object>> actualizarUsuario(@PathVariable Long id,
			@RequestBody Map<String, Object> payload) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El usuario no se encuentra con el id: " + id));

		applyPayload(usuario, payload, false);
		Usuario actualizado = usuarioRepository.save(usuario);
		return ResponseEntity.ok(toFrontend(actualizado));
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, Boolean>> eliminarUsuario(@PathVariable Long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El usuario no se encuentra con el id: " + id));
		usuarioRepository.delete(usuario);

		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado", Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}

	private void applyPayload(Usuario usuario, Map<String, Object> payload, boolean includeCedula) {
		if (includeCedula) {
			usuario.setCedulaUsuario(getLong(payload, "cedulaUsuario", "cedula", "id"));
		}
		usuario.setUsuario(getString(payload, "usuario"));
		usuario.setNombreUsuario(getString(payload, "nombreUsuario", "nombreCompleto", "nombre"));
		usuario.setEmailUsuario(getString(payload, "emailUsuario", "email"));
		usuario.setPassword(getString(payload, "password"));
	}

	private Map<String, Object> toFrontend(Usuario usuario) {
		Map<String, Object> data = new HashMap<>();
		data.put("id", usuario.getCedulaUsuario());
		data.put("cedula", usuario.getCedulaUsuario());
		data.put("usuario", usuario.getUsuario());
		data.put("nombreCompleto", usuario.getNombreUsuario());
		data.put("email", usuario.getEmailUsuario());
		data.put("password", usuario.getPassword());
		return data;
	}

	private Map<String, Object> toFrontendSinPassword(Usuario usuario) {
		Map<String, Object> data = new HashMap<>();
		data.put("id", usuario.getCedulaUsuario());
		data.put("cedula", usuario.getCedulaUsuario());
		data.put("usuario", usuario.getUsuario());
		data.put("nombreCompleto", usuario.getNombreUsuario());
		data.put("email", usuario.getEmailUsuario());
		// No incluir password por seguridad
		return data;
	}

	private long getLong(Map<String, Object> payload, String... keys) {
		for (String key : keys) {
			Object value = payload.get(key);
			if (value == null) {
				continue;
			}
			if (value instanceof Number) {
				return ((Number) value).longValue();
			}

			String text = value.toString().trim();
			if (!text.isEmpty()) {
				return Long.parseLong(text);
			}
		}
		return 0L;
	}

	private String getString(Map<String, Object> payload, String... keys) {
		for (String key : keys) {
			Object value = payload.get(key);
			if (value != null) {
				return value.toString();
			}
		}
		return null;
	}

}
