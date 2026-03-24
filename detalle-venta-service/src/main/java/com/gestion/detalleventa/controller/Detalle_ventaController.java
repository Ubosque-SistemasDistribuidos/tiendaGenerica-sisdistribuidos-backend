package com.gestion.detalleventa.controller;

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

import com.gestion.detalleventa.exception.ResourceNotFoundException;
import com.gestion.detalleventa.model.Detalle_venta;
import com.gestion.detalleventa.repository.Detalle_ventaRepository;

@RestController
@RequestMapping("detalle_ventas")
public class Detalle_ventaController {
	@Autowired
	private Detalle_ventaRepository detalleRepository;
	
	@GetMapping("/listar")
	public List<Detalle_venta> verClientes(){
		return detalleRepository.findAll();
	}
	
	@PostMapping("/guardar")
	public Detalle_venta guardarDetalle_venta(@RequestBody Detalle_venta detalle) {
		return detalleRepository.save(detalle);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Detalle_venta> buscarDetalle_venta(@PathVariable Long id){
		Detalle_venta detalle=detalleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El detalle de la venta no se encuentra con el id: "+id));
		return ResponseEntity.ok(detalle);
	}
	
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Detalle_venta> actualizarCliente(@PathVariable Long id, @RequestBody Detalle_venta detalleR){
		Detalle_venta detalle=detalleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El detalle de la venta no se encuentra con el id: "+id));
		
		detalle.setCantidad(detalleR.getCantidad());
		detalle.setCodigoProducto(detalleR.getCodigoProducto());
		detalle.setCodigoVenta(detalleR.getCodigoVenta());
		detalle.setValorTotal(detalleR.getValorTotal());
		detalle.setValorVenta(detalleR.getValorVenta());
		detalle.setValorIva(detalleR.getValorIva());
		
		Detalle_venta detalleActualizado = detalleRepository.save(detalle);
		return ResponseEntity.ok(detalleActualizado);
		
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarDetalle_venta(@PathVariable Long id){
		Detalle_venta detalle=detalleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El detalle de la venta no se encuentra con el id: "+id));
		detalleRepository.delete(detalle);
		Map<String,Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}
}
