package com.gestion.tiendag.model;
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

}
