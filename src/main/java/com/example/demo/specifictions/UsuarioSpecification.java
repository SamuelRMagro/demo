package com.example.demo.specifictions;

import com.example.demo.model.Usuario;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UsuarioSpecification {

    public static Specification<Usuario> nomeContem(String nome) {
        return (root, query, criteriaBuilder) ->
                nome == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Usuario> emailContem(String email) {
        return (root, query, criteriaBuilder) ->
                email == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    }

    public static Specification<Usuario> isAtivo(Boolean ativo) {
        return (root, query, criteriaBuilder) ->
                ativo == null ? null : criteriaBuilder.equal(root.get("isAtivo"), ativo);
    }

    public static Specification<Usuario> cadastroEntre(LocalDate inicio, LocalDate fim) {
        return (root, query, cb) -> {
            if (inicio == null && fim == null) return null;

            LocalDateTime inicioDateTime = inicio != null ? inicio.atStartOfDay() : null;
            LocalDateTime fimExclusivo = fim != null ? fim.plusDays(1).atStartOfDay() : null;

            if (inicioDateTime != null && fimExclusivo != null) {
                return cb.and(
                        cb.greaterThanOrEqualTo(root.get("dataCadastro"), inicioDateTime),
                        cb.lessThan(root.get("dataCadastro"), fimExclusivo)
                );
            }
            if (inicioDateTime != null) {
                return cb.greaterThanOrEqualTo(root.get("dataCadastro"), inicioDateTime);
            }
            return cb.lessThan(root.get("dataCadastro"), fimExclusivo);
        };
    }
}
