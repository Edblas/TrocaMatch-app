package com.troca.habilidades.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Troca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ofertante_id")
    private Usuario ofertante;

    @ManyToOne
    @JoinColumn(name = "receptor_id")
    private Usuario receptor;

    @ManyToOne
    @JoinColumn(name = "servico_ofertado_id")
    private Servico servicoOfertado;

    @ManyToOne
    @JoinColumn(name = "servico_recebido_id")
    private Servico servicoRecebido;

    private Date dataTroca;
    private String status;  // 'pendente', 'concluída', 'cancelada'

    // Getters e Setters manualmente
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
