package com.example.demo.repository.curso;

import com.example.demo.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CursoRepository extends JpaRepository<Curso, Long>, JpaSpecificationExecutor<Curso> {
    Page<Curso> findAll(Pageable pageable);
}
