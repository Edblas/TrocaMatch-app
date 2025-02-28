package com.troca.habilidades.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios", uniqueConstraints = { @UniqueConstraint(columnNames = "email") }) // Garante que o email seja único
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Nome não pode ser nulo
    private String nome;

    @Column(nullable = false, unique = true) // Email único
    private String email;

    @Column(nullable = false) // Senha não pode ser nula
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) // Permissao não pode ser nula
    private Permissao permissao ;

    // Se precisar armazenar os serviços oferecidos pelo usuário
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Servico> servicos;
}
