package com.gestion.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.usuario.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
