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
@Table(name = "ventas")
public class Venta {
	
	@Id
	@Column(name = "codigo_venta")
	private long codigoVenta;
	
	@ManyToOne
	@JoinColumn(name = "cedula_cliente")
	private Cliente cedulaCliente;
	
	@ManyToOne
	@JoinColumn(name = "cedula_usuario")
	private Usuario cedulaUsuario;
	
	@Column(name = "ivaventa")
	private Double ivaVenta;
	
	@Column(name = "total_venta")
	private Double totalVenta;
	
	@Column(name = "valor_venta")
	private Double valorVenta;

}
