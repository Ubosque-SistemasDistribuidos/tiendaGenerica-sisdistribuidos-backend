package com.gestion.proveedor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proveedores")
public class Proveedor {
	
	@Id
	private long nitProveedor;
	
	@Column(name = "ciudad_proveedor")
	private String ciudadProveedor;
	
	@Column(name = "direccion_proveedor")
	private String direccionProveedor;
	
	@Column(name = "nombre_proveedor")
	private String nombreProveedor;
	
	@Column(name = "telefono_proveedor")
	private String telefonoProveedor;

	public long getNitProveedor() {
		return nitProveedor;
	}

	public void setNitProveedor(long nitProveedor) {
		this.nitProveedor = nitProveedor;
	}

	public String getCiudadProveedor() {
		return ciudadProveedor;
	}

	public void setCiudadProveedor(String ciudadProveedor) {
		this.ciudadProveedor = ciudadProveedor;
	}

	public String getDireccionProveedor() {
		return direccionProveedor;
	}

	public void setDireccionProveedor(String direccionProveedor) {
		this.direccionProveedor = direccionProveedor;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getTelefonoProveedor() {
		return telefonoProveedor;
	}

	public void setTelefonoProveedor(String telefonoProveedor) {
		this.telefonoProveedor = telefonoProveedor;
	}
}
