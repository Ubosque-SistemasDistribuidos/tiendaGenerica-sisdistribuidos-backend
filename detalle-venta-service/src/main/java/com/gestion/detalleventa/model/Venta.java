package com.gestion.detalleventa.model;

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
@Table(name = "ventas")
public class Venta {
	
	@Id
	@Column(name = "codigo_venta")
	private long codigoVenta;
	
	@Column(name = "ivaventa")
	private Double ivaVenta;
	
	@Column(name = "total_venta")
	private Double totalVenta;
	
	@Column(name = "valor_venta")
	private Double valorVenta;

}
