package com.tienda.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.tienda.tienda.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String nombreUsuario);

    Usuario findByIdUsuario(Long id);
}
