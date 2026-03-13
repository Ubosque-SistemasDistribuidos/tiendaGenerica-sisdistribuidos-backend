package com.gestion.tiendag.model;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.Id;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id
	private long cedulaUsuario;
	
	@Column(name = "email_usuario")
	private String emailUsuario;
	
	@Column(name = "nombre_usuario")
	private String nombreUsuario;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "usuario")
	private String usuario;

}
