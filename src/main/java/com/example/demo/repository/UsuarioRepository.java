package com.example.demo.repository;

import com.example.demo.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findAll(Pageable pageable);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);
}
