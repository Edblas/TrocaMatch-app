package com.troca.habilidades.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Troca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ofertante_id", nullable = false)
    private Usuario ofertante;

    @ManyToOne
    @JoinColumn(name = "receptor_id", nullable = false)
    private Usuario receptor;

    @ManyToOne
    @JoinColumn(name = "servico_ofertado_id", nullable = false)
    private Servico servicoOfertado;

    @ManyToOne
    @JoinColumn(name = "servico_recebido_id", nullable = false)
    private Servico servicoRecebido;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp // Preenche automaticamente com a data atual no momento da criação
    private Date dataTroca;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTroca status = StatusTroca.PENDENTE; // Padrão: "pendente"
}
