package com.gestion.tiendag.model;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import javax.persistence.Id;



@AllArgsConstructor
@NoArgsConstructor
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

	public long getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public Double getIvaCompra() {
		return ivaCompra;
	}

	public void setIvaCompra(Double ivaCompra) {
		this.ivaCompra = ivaCompra;
	}

	public Proveedor getNitProveedor() {
		return nitProveedor;
	}

	public void setNitProveedor(Proveedor nitProveedor) {
		this.nitProveedor = nitProveedor;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}
	
	

}
