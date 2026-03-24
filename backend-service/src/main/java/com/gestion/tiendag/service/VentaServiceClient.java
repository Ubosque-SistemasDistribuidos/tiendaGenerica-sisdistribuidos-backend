package com.gestion.tiendag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class VentaServiceClient {
    
    @Autowired
    private RestTemplate restTemplate;
    
    private static final String VENTA_SERVICE_URL = "http://localhost:8093/ventas";
    
    public List<?> obtenerVentas() {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(
                VENTA_SERVICE_URL + "/listar", 
                List.class
            );
            return (List<?>) response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al obtener ventas: " + e.getMessage());
        }
    }
    
    public Object obtenerVenta(Long id) {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(
                VENTA_SERVICE_URL + "/buscar/" + id, 
                Object.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al obtener venta con id " + id + ": " + e.getMessage());
        }
    }
    
    public Object guardarVenta(Object venta) {
        try {
            return restTemplate.postForObject(
                VENTA_SERVICE_URL + "/guardar", 
                venta, 
                Object.class
            );
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al guardar venta: " + e.getMessage());
        }
    }
    
    public Object actualizarVenta(Long id, Object venta) {
        try {
            restTemplate.put(
                VENTA_SERVICE_URL + "/actualizar/" + id, 
                venta
            );
            return obtenerVenta(id);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al actualizar venta: " + e.getMessage());
        }
    }
    
    public void eliminarVenta(Long id) {
        try {
            restTemplate.delete(VENTA_SERVICE_URL + "/eliminar/" + id);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al eliminar venta: " + e.getMessage());
        }
    }
}
