package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String login;
    @Column(name = "is_ativo")
    private boolean isAtivo = true;

}
