package com.gestion.tiendag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class UsuarioServiceClient {
    
    @Autowired
    private RestTemplate restTemplate;
    
    private static final String USUARIO_SERVICE_URL = "http://localhost:8090/usuarios";
    
    public List<?> obtenerUsuarios() {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(
                USUARIO_SERVICE_URL + "/listar", 
                List.class
            );
            return (List<?>) response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al obtener usuarios: " + e.getMessage());
        }
    }
    
    public Object obtenerUsuario(Long id) {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(
                USUARIO_SERVICE_URL + "/buscar/" + id, 
                Object.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al obtener usuario con id " + id + ": " + e.getMessage());
        }
    }
    
    public Object guardarUsuario(Object usuario) {
        try {
            return restTemplate.postForObject(
                USUARIO_SERVICE_URL + "/guardar", 
                usuario, 
                Object.class
            );
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al guardar usuario: " + e.getMessage());
        }
    }
    
    public Object actualizarUsuario(Long id, Object usuario) {
        try {
            restTemplate.put(
                USUARIO_SERVICE_URL + "/actualizar/" + id, 
                usuario
            );
            return obtenerUsuario(id);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al actualizar usuario: " + e.getMessage());
        }
    }
    
    public void eliminarUsuario(Long id) {
        try {
            restTemplate.delete(USUARIO_SERVICE_URL + "/eliminar/" + id);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al eliminar usuario: " + e.getMessage());
        }
    }
}
