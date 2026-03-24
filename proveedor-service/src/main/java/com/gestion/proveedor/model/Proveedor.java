package com.gestion.proveedor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
