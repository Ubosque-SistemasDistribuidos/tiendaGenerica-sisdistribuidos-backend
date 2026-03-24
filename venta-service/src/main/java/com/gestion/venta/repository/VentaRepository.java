package com.gestion.venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.venta.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long>{

}
