package com.gestion.producto.model;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "productos")
public class Producto {
	
	@Id
	@Column(name = "codigo_producto")
	private long codigoProducto;
	
	@Column(name = "iva_compra")
	private Double ivaCompra;
	
	@ManyToOne
	@JoinColumn(name = "nit_proveedor")
	private Proveedor nitProveedor;
	
	@Column(name = "nombre_producto")
	private String nombreProducto;
	
	@Column(name = "precio_compra")
	private Double precioCompra;
	
	@Column(name = "precio_venta")
	private Double precioVenta;

}
