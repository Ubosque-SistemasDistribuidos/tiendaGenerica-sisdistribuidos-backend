package com.gestion.tiendag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class DetalleVentaServiceClientImpl {
    
    @Autowired
    private RestTemplate restTemplate;
    
    private static final String DETALLE_VENTA_SERVICE_URL = "http://localhost:8094/detalle_ventas";
    
    public List<?> obtenerDetallesVenta() {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(
                DETALLE_VENTA_SERVICE_URL + "/listar", 
                List.class
            );
            return (List<?>) response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al obtener detalles de venta: " + e.getMessage());
        }
    }
    
    public Object obtenerDetalleVenta(Long id) {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(
                DETALLE_VENTA_SERVICE_URL + "/buscar/" + id, 
                Object.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al obtener detalle de venta con id " + id + ": " + e.getMessage());
        }
    }
    
    public Object guardarDetalleVenta(Object detalleVenta) {
        try {
            return restTemplate.postForObject(
                DETALLE_VENTA_SERVICE_URL + "/guardar", 
                detalleVenta, 
                Object.class
            );
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al guardar detalle de venta: " + e.getMessage());
        }
    }
    
    public Object actualizarDetalleVenta(Long id, Object detalleVenta) {
        try {
            restTemplate.put(
                DETALLE_VENTA_SERVICE_URL + "/actualizar/" + id, 
                detalleVenta
            );
            return obtenerDetalleVenta(id);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al actualizar detalle de venta: " + e.getMessage());
        }
    }
    
    public void eliminarDetalleVenta(Long id) {
        try {
            restTemplate.delete(DETALLE_VENTA_SERVICE_URL + "/eliminar/" + id);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al eliminar detalle de venta: " + e.getMessage());
        }
    }
}
