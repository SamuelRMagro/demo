package com.example.demo.service.professor;

import com.example.demo.dto.professor.ProfessorFilterRequest;
import com.example.demo.dto.professor.ProfessorIdResponseDTO;
import com.example.demo.dto.professor.ProfessorResponseDTO;
import com.example.demo.dto.titulacao.TitulacaoResponseDTO;
import com.example.demo.exceptions.custom.RegistroNaoEncontradoCustomException;
import com.example.demo.mappers.ProfessorMapper;
import com.example.demo.model.Professor;
import com.example.demo.repository.professor.ProfessorRepository;
import com.example.demo.repository.titulacao.TitulacaoRepository;
import com.example.demo.specifictions.professor.ProfessorSpecification;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper mapper;
    private final TitulacaoRepository titulacaoRepository;

    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper mapper, TitulacaoRepository titulacaoRepository) {
        this.professorRepository = professorRepository;
        this.mapper = mapper;
        this.titulacaoRepository = titulacaoRepository;
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

    public ProfessorIdResponseDTO buscarOuFalhar(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoCustomException(String.format("Professor de id: %d não encontrado.", id)));
        List<TitulacaoResponseDTO> titulacoes = titulacaoRepository.buscaTitulacoesProfessorId(id);
        return mapper.fromEntityToTitulacoes(professor, titulacoes);
    }
}