package com.gestion.tiendag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class ProductoServiceClient {
    
    @Autowired
    private RestTemplate restTemplate;
    
    private static final String PRODUCTO_SERVICE_URL = "http://localhost:8092/productos";
    
    public List<?> obtenerProductos() {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(
                PRODUCTO_SERVICE_URL + "/listar", 
                List.class
            );
            return (List<?>) response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al obtener productos: " + e.getMessage());
        }
    }
    
    public Object obtenerProducto(Long id) {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(
                PRODUCTO_SERVICE_URL + "/buscar/" + id, 
                Object.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al obtener producto con id " + id + ": " + e.getMessage());
        }
    }
    
    public Object guardarProducto(Object producto) {
        try {
            return restTemplate.postForObject(
                PRODUCTO_SERVICE_URL + "/guardar", 
                producto, 
                Object.class
            );
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al guardar producto: " + e.getMessage());
        }
    }
    
    public Object actualizarProducto(Long id, Object producto) {
        try {
            restTemplate.put(
                PRODUCTO_SERVICE_URL + "/actualizar/" + id, 
                producto
            );
            return obtenerProducto(id);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al actualizar producto: " + e.getMessage());
        }
    }
    
    public void eliminarProducto(Long id) {
        try {
            restTemplate.delete(PRODUCTO_SERVICE_URL + "/eliminar/" + id);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al eliminar producto: " + e.getMessage());
        }
    }
}
