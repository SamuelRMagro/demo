package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_professor_titulacoes", uniqueConstraints = @UniqueConstraint(columnNames = {"professor_id", "titulacao_id", "data_inicio"}))
@Getter
@Setter
@NoArgsConstructor
public class ProfessorTitulacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "titulacao_id")
    private Titulacao titulacao;

    @Column(nullable = false)
    private LocalDate dataInicio;

    private LocalDate dataTermino;
}
