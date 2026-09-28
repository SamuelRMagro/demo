package com.example.demo.service.curso;

import com.example.demo.dto.curso.CursoCreateDTO;
import com.example.demo.dto.curso.CursoFilterRequest;
import com.example.demo.dto.curso.CursoResponseDTO;
import com.example.demo.exceptions.custom.RegistroNaoEncontradoCustomException;
import com.example.demo.mappers.CursoMapper;
import com.example.demo.model.Curso;
import com.example.demo.repository.curso.CursoRepository;
import com.example.demo.specifictions.curso.CursoSpecification;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;
    private final CursoMapper mapper;

    public CursoService(CursoRepository cursoRepository, CursoMapper mapper) {
        this.cursoRepository = cursoRepository;
        this.mapper = mapper;
    }

    public Page<CursoResponseDTO> listagemCursos(Pageable pageable, CursoFilterRequest request) {
        Specification<Curso> spec = Specification
                .where(CursoSpecification.nomeContem(request.nome()))
                .and(CursoSpecification.cargaHorariaAte(request.cargaHoraria()))
                .and(CursoSpecification.duracaoSemestreAte(request.duracaoSemestre()))
                .and(CursoSpecification.isAtivo(request.isAtivo()))
                .and(CursoSpecification.inicioDepoisDe(request.dataCadastroInicio()))
                .and(CursoSpecification.inicioAntesDe(request.dataCadastroFim()));

        Page<Curso> cursoPage = cursoRepository.findAll(spec, pageable);
        return cursoPage.map(mapper::fromEntityToDTO);
    }

    public CursoResponseDTO buscarOuFalhar(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoCustomException(String.format("Curso de id: %d não encontrado", id)));

        return mapper.fromEntityToDTO(curso);
    }

    public CursoResponseDTO cadastrarCurso(@Valid CursoCreateDTO createDTO) {
        Curso curso = mapper.fromCreateDTOtoEntity(createDTO);
        cursoRepository.save(curso);
        return mapper.fromEntityToDTO(curso);
    }
}
