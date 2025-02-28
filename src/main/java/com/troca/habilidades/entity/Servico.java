package com.troca.habilidades.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100) // Evita valores nulos e define tamanho máximo
    private String titulo;

    @Column(nullable = false, length = 500) // Define um limite para a descrição
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) // O serviço precisa ter um dono
    private Usuario usuario;
}
