package com.gestion.tiendag.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.tiendag.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
