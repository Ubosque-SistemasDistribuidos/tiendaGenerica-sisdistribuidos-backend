package com.gestion.tiendag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class ClienteServiceClient {
    
    @Autowired
    private RestTemplate restTemplate;
    
    private static final String CLIENTE_SERVICE_URL = "http://localhost:8089/cliente";
    
    public List<?> obtenerClientes() {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(
                CLIENTE_SERVICE_URL + "/listar", 
                List.class
            );
            return (List<?>) response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al obtener clientes: " + e.getMessage());
        }
    }
    
    public Object obtenerCliente(Long id) {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(
                CLIENTE_SERVICE_URL + "/buscar/" + id, 
                Object.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al obtener cliente con id " + id + ": " + e.getMessage());
        }
    }
    
    public Object guardarCliente(Object cliente) {
        try {
            return restTemplate.postForObject(
                CLIENTE_SERVICE_URL + "/guardar", 
                cliente, 
                Object.class
            );
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al guardar cliente: " + e.getMessage());
        }
    }
    
    public Object actualizarCliente(Long id, Object cliente) {
        try {
            restTemplate.put(
                CLIENTE_SERVICE_URL + "/actualizar/" + id, 
                cliente
            );
            return obtenerCliente(id);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al actualizar cliente: " + e.getMessage());
        }
    }
    
    public void eliminarCliente(Long id) {
        try {
            restTemplate.delete(CLIENTE_SERVICE_URL + "/eliminar/" + id);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al eliminar cliente: " + e.getMessage());
        }
    }
}
