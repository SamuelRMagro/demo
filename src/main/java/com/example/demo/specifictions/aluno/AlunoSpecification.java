package com.example.demo.specifictions.aluno;

import com.example.demo.model.Aluno;
import org.springframework.data.jpa.domain.Specification;

public class AlunoSpecification {

    public static Specification<Aluno> nomeContem(String nome) {
        return (root, query, criteriaBuilder) ->
                nome == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Aluno> emailContem(String email) {
        return (root, query, criteriaBuilder) ->
                email == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    }

    public static Specification<Aluno> cpfContem(String cpf) {
        return (root, query, criteriaBuilder) ->
                cpf == null ? null : criteriaBuilder.like((root.get("cpf")), "%" + cpf + "%");
    }

    public static Specification<Aluno> isAtivo(Boolean ativo) {
        return (root, query, criteriaBuilder) ->
                ativo == null ? null : criteriaBuilder.equal(root.get("isAtivo"), ativo);
    }
}
