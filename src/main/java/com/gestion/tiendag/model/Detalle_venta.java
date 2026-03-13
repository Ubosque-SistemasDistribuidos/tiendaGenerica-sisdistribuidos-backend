package com.gestion.tiendag.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.Id;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "detalle_ventas")
public class Detalle_venta {
	
	@Id
	@Column(name = "codigo_detalle_venta")
	private long codigoDetalle;
	
	@Column(name = "cantidad_producto")
	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name = "codigo_producto")
	private Producto codigoProducto;
	
	@ManyToOne
	@JoinColumn(name = "codigo_venta")
	private Venta codigoVenta;
	
	@Column(name = "valor_total")
	private Double valorTotal;
	
	@Column(name = "valor_venta")
	private Double valorVenta;
	
	@Column(name = "valoriva")
	private Double valorIva;
	
}
