package com.generation.BlogPessoal.repository;

import java.util.Optional;

import com.generation.BlogPessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public Optional<Usuario> findByUsuario(String usuario);

}