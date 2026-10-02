package com.example.demo.service.professor;

import com.example.demo.dto.professor.ProfessorFilterRequest;
import com.example.demo.dto.professor.ProfessorResponseDTO;
import com.example.demo.mappers.ProfessorMapper;
import com.example.demo.model.Professor;
import com.example.demo.repository.professor.ProfessorRepository;
import com.example.demo.specifictions.professor.ProfessorSpecification;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper mapper;

    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper mapper) {
        this.professorRepository = professorRepository;
        this.mapper = mapper;
    }


    public Page<ProfessorResponseDTO> listagemProfessores(Pageable pageable, @Valid ProfessorFilterRequest request) {
        Specification<Professor> spec = Specification
                .where(ProfessorSpecification.nomeContem(request.nome()))
                .and(ProfessorSpecification.emailContem(request.email()))
                .and(ProfessorSpecification.cpfContem(request.cpf()))
                .and(ProfessorSpecification.isAtivo(request.isAtivo()))
                .and(ProfessorSpecification.inicioAdmissaoDepoisDe(request.dataAdmissaoInicio()))
                .and(ProfessorSpecification.inicioAdmissaoAntesDe(request.dataAdmissaoFim()));
        Page<Professor> professorPage = professorRepository.findAll(spec, pageable);

        return professorPage.map(mapper::fromEntityToDTO);
    }
}