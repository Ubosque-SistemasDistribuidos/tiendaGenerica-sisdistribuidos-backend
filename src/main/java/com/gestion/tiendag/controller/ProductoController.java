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

import com.gestion.tiendag.exception.ResourceNotFoundException;
import com.gestion.tiendag.model.Producto;
import com.gestion.tiendag.model.Proveedor;
import com.gestion.tiendag.repository.ProductoRepository;

@RestController
@RequestMapping("productos")
public class ProductoController {

	@Autowired
	private ProductoRepository productoRepository;

	@GetMapping("/listar")
	public List<Producto> verProductos() {
		return productoRepository.findAll();
	}

	@PostMapping("/guardar")
	public Producto guardarProducto(@RequestBody Map<String, Object> payload) {
		Producto producto = new Producto();
		applyPayload(producto, payload, true);
		return productoRepository.save(producto);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<Producto> buscarProducto(@PathVariable Long id) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El producto no se encuentra con el id: " + id));
		return ResponseEntity.ok(producto);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El producto no se encuentra con el id: " + id));

		applyPayload(producto, payload, false);
		Producto actualizado = productoRepository.save(producto);
		return ResponseEntity.ok(actualizado);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable Long id) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El producto no se encuentra con el id: " + id));
		productoRepository.delete(producto);

		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado", Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}

	private void applyPayload(Producto producto, Map<String, Object> payload, boolean includeCodigo) {
		if (includeCodigo) {
			producto.setCodigoProducto(getLong(payload, "codigoProducto", "id"));
		}

		producto.setNombreProducto(getString(payload, "nombreProducto"));
		producto.setIvaCompra(getDouble(payload, "ivaCompra"));
		producto.setPrecioCompra(getDouble(payload, "precioCompra"));
		producto.setPrecioVenta(getDouble(payload, "precioVenta"));

		long nit = getLong(payload, "nitProveedor");
		if (nit > 0) {
			Proveedor proveedor = new Proveedor();
			proveedor.setNitProveedor(nit);
			producto.setNitProveedor(proveedor);
		}
	}

	private long getLong(Map<String, Object> payload, String... keys) {
		for (String key : keys) {
			Object value = payload.get(key);
			if (value == null) {
				continue;
			}
			if (value instanceof Number number) {
				return number.longValue();
			}

			String text = value.toString().trim();
			if (!text.isEmpty()) {
				return Long.parseLong(text);
			}
		}
		return 0L;
	}

	private Double getDouble(Map<String, Object> payload, String key) {
		Object value = payload.get(key);
		if (value == null) {
			return null;
		}
		if (value instanceof Number number) {
			return number.doubleValue();
		}

		String text = value.toString().trim();
		if (text.isEmpty()) {
			return null;
		}
		return Double.parseDouble(text);
	}

	private String getString(Map<String, Object> payload, String key) {
		Object value = payload.get(key);
		return value == null ? null : value.toString();
	}

}
