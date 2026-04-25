package com.gestion.venta.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.venta.exception.ResourceNotFoundException;
import com.gestion.venta.model.Cliente;
import com.gestion.venta.model.Usuario;
import com.gestion.venta.model.Venta;
import com.gestion.venta.repository.VentaRepository;

@RestController
@RequestMapping("ventas")
public class VentaController {

	@Autowired
	private VentaRepository ventaRepository;
	
	@GetMapping("/listar")
	public List<Venta> verVentas(){
		return ventaRepository.findAll();
	}
	
	@PostMapping("/guardar")
	public Venta guardarVenta(@RequestBody Map<String, Object> payload) {
    	Venta venta = new Venta();

    	Object cod = payload.get("codigoVenta");
    	if (cod != null) venta.setCodigoVenta(Long.parseLong(cod.toString()));

    	// El frontend envía cliente como objeto anidado: { cedula: 123 }
   		Object clienteObj = payload.get("cliente");
    	if (clienteObj instanceof Map) {
        	@SuppressWarnings("unchecked")
        	Map<String, Object> clienteMap = (Map<String, Object>) clienteObj;
        	Object cedula = clienteMap.get("cedula");
        	if (cedula != null) {
            	Cliente cliente = new Cliente();
            	cliente.setCedula(Long.parseLong(cedula.toString()));
            	venta.setCedulaCliente(cliente);
        	}
    	}

	    // cedulaUsuario directo o anidado
	    Object cedUsr = payload.get("cedulaUsuario");
	    if (cedUsr != null) {
	        Usuario usuario = new Usuario();
	        usuario.setCedulaUsuario(Long.parseLong(cedUsr.toString()));
	        venta.setCedulaUsuario(usuario);
	    }

	    Object iva = payload.get("ivaVenta");
	    if (iva != null) venta.setIvaVenta(Double.parseDouble(iva.toString()));

	    Object total = payload.get("totalVenta");
	    if (total != null) venta.setTotalVenta(Double.parseDouble(total.toString()));

    	Object valor = payload.get("valorVenta");
    	if (valor != null) venta.setValorVenta(Double.parseDouble(valor.toString()));

    	return ventaRepository.save(venta);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Venta> buscarVenta(@PathVariable @NonNull Long id){
		Venta venta=ventaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("La venta no se encuentra con el id: "+id));
		return ResponseEntity.ok(venta);
	}
	
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Venta> actualizarVenta(@PathVariable Long id, @RequestBody Venta ventaR){
		Venta venta=ventaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("La venta no se encuentra con el id: "+id));
		
		venta.setCedulaCliente(ventaR.getCedulaCliente());
		venta.setCedulaUsuario(ventaR.getCedulaUsuario());
		venta.setIvaVenta(ventaR.getIvaVenta());
		venta.setTotalVenta(ventaR.getTotalVenta());
		venta.setValorVenta(ventaR.getValorVenta());
		
		Venta ventaActualizada = ventaRepository.save(venta);
		return ResponseEntity.ok(ventaActualizada);
		
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarVenta(@PathVariable Long id){
		Venta venta=ventaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("La venta no se encuentra con el id: "+id));
		ventaRepository.delete(venta);
		Map<String,Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}
}
