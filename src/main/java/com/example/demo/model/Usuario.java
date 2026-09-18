package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_usuarios")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private int idade;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "is_ativo", nullable = false)
    @ColumnDefault("true")
    private Boolean isAtivo = true;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Generated(event = EventType.INSERT)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Column(name = "data_atualizacao", columnDefinition = "TIMESTAMP DEFAULT NULL")
    private LocalDateTime dataAtualizacao;

    @Column(name = "data_inativacao", columnDefinition = "TIMESTAMP DEFAULT NULL")
    private LocalDateTime dataInativacao = null;

    public String cpfMascarado() {
        if (this.cpf == null) return null;

        String limpo = this.cpf.replace("\\D", "");

        if (limpo.length() != 11){
            return null;
        }

        return "***.***.*" + limpo.substring(8, 9) + "-" + limpo.substring(9);
    }

    public boolean ativo() {

        if (!isAtivo) {
            return false;
        }
        return true;
    }

    public boolean inativo() {
        return !ativo();
    }
}
