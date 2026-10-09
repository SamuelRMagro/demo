package com.example.demo.repository.professor;

import com.example.demo.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long>, JpaSpecificationExecutor<Professor> {

    Optional<Professor> findByIdAndUsuario_IsAtivo(Long id, Boolean usuarioIsAtivo);
}