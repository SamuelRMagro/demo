package com.example.demo.service.curso;

import com.example.demo.dto.curso.CursoFilterRequest;
import com.example.demo.dto.curso.CursoResponseDTO;
import com.example.demo.model.Curso;
import com.example.demo.repository.curso.CursoRepository;
import com.example.demo.specifictions.curso.CursoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Page<CursoResponseDTO> listagemCursos(Pageable pageable, CursoFilterRequest request) {
        Specification<Curso> spec = Specification
                .where(CursoSpecification.nomeContem(request.nome()))
                .and(CursoSpecification.cargaHorariaAte(request.cargaHoraria()))
                .and(CursoSpecification.duracaoSemestreAte(request.duracaoSemestre()))
                .and(CursoSpecification.isAtivo(request.isAtivo()))
                .and(CursoSpecification.inicioDepoisDe(request.dataCadastroInicio()))
                .and(CursoSpecification.inicioAntesDe(request.dataCadastroFim()));

        return converteDadosPaginados(cursoRepository.findAll(spec, pageable));
    }

    private Page<CursoResponseDTO> converteDadosPaginados(Page<Curso> cursoPage){
        return cursoPage.map(curso -> new CursoResponseDTO(
                curso.getId(),
                curso.getNome(),
                curso.getCargaHoraria(),
                curso.getDuracaoSemestre(),
                curso.getIsAtivo(),
                curso.getDataCriacao(),
                curso.getDataAtualizacao()
        ));
    }
}
