package com.example.demo.mappers;

import com.example.demo.dto.aluno.AlunoResponseDTO;
import com.example.demo.model.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlunoMapper {
    @Mapping(target = "cpf", expression = "java(getCpfMascarado(entity))")
    AlunoResponseDTO fromEntityToDTO(Aluno entity);

    default String getCpfMascarado(Aluno aluno) {
        return aluno.cpfMascarado();
    }
}
