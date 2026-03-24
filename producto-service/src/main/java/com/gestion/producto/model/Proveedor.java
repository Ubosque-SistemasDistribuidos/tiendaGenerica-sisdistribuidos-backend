package com.gestion.producto.model;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
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

}
