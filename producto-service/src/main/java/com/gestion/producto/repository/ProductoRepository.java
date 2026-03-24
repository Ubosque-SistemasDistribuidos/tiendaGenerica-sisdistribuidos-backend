package com.gestion.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.producto.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
