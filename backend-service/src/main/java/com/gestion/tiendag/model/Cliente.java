package com.gestion.tiendag.model;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;



@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {
	
	@Id
	@Column(name = "cedula_cliente")
	private Long cedula;
	
	@Column(name = "nombre_cliente")
	private String nombre;
	
	@Column(name = "direccion_cliente")
	private String direccion;
	
	@Column(name = "email_cliente")
	private String email;
	
	@Column(name = "telefono_cliente")
	private String telefono;

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	


}
