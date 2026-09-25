package com.example.demo.repository;

import com.example.demo.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

    Page<Usuario> findAll(Pageable pageable);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

    Optional<Usuario> findByIdAndIsAtivoIsTrue(Long id);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByCpfAndIdNot(String cpf, Long id);
}
