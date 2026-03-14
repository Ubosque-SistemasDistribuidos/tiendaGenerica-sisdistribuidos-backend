package com.gestion.tiendag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class ProveedorServiceClient {
    
    @Autowired
    private RestTemplate restTemplate;
    
    private static final String PROVEEDOR_SERVICE_URL = "http://localhost:8091/proveedores";
    
    public List<?> obtenerProveedores() {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(
                PROVEEDOR_SERVICE_URL + "/listar", 
                List.class
            );
            return (List<?>) response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al obtener proveedores: " + e.getMessage());
        }
    }
    
    public Object obtenerProveedor(Long id) {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(
                PROVEEDOR_SERVICE_URL + "/buscar/" + id, 
                Object.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al obtener proveedor con id " + id + ": " + e.getMessage());
        }
    }
    
    public Object guardarProveedor(Object proveedor) {
        try {
            return restTemplate.postForObject(
                PROVEEDOR_SERVICE_URL + "/guardar", 
                proveedor, 
                Object.class
            );
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al guardar proveedor: " + e.getMessage());
        }
    }
    
    public Object actualizarProveedor(Long id, Object proveedor) {
        try {
            restTemplate.put(
                PROVEEDOR_SERVICE_URL + "/actualizar/" + id, 
                proveedor
            );
            return obtenerProveedor(id);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al actualizar proveedor: " + e.getMessage());
        }
    }
    
    public void eliminarProveedor(Long id) {
        try {
            restTemplate.delete(PROVEEDOR_SERVICE_URL + "/eliminar/" + id);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al eliminar proveedor: " + e.getMessage());
        }
    }
}
