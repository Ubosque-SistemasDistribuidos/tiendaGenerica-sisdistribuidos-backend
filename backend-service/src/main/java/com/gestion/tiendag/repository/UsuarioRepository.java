package com.gestion.tiendag.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.tiendag.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
