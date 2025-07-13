package com.parqueoseguro.adminApp.repository;

import com.parqueoseguro.adminApp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}