package com.troca.habilidades.controller;

import com.troca.habilidades.entity.Troca;
import com.troca.habilidades.entity.StatusTroca;
import com.troca.habilidades.repository.TrocaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrocaServico {

    @Autowired
    private TrocaRepository trocaRepository;

    public Troca registrarTroca(Troca troca) {
        if (troca.getOfertante() == null || troca.getReceptor() == null) {
            throw new IllegalArgumentException("A troca precisa de um ofertante e um receptor.");
        }
        if (troca.getServicoOfertado() == null || troca.getServicoRecebido() == null) {
            throw new IllegalArgumentException("A troca precisa conter serviços ofertados e recebidos.");
        }

        // Se um status for informado, só pode ser EM_ABERTO ou EM_ANDAMENTO
        if (troca.getStatus() == null || troca.getStatus() == StatusTroca.CONCLUIDA) {
            troca.setStatus(StatusTroca.EM_ABERTO);
        }

        return trocaRepository.save(troca);
    }

    public List<Troca> listarTrocas() {
        return trocaRepository.findAll();
    }

    public Optional<Troca> buscarTrocaPorId(Long id) {
        return trocaRepository.findById(id);
    }

    public List<Troca> listarTrocasPorUsuario(Long usuarioId) {
        return trocaRepository.findByOfertanteId(usuarioId);
    }

    public Troca atualizarStatusTroca(Long id, StatusTroca status) {
        Optional<Troca> trocaOptional = trocaRepository.findById(id);

        if (trocaOptional.isPresent()) {
            Troca troca = trocaOptional.get();

            // Regras para evitar atualizações inválidas
            if (troca.getStatus() == StatusTroca.CONCLUIDA) {
                throw new IllegalStateException("Uma troca concluída não pode ser alterada.");
            }

            if (status == StatusTroca.CONCLUIDA && troca.getStatus() != StatusTroca.EM_ANDAMENTO) {
                throw new IllegalStateException("A troca só pode ser concluída se estiver em andamento.");
            }

            troca.setStatus(status);
            return trocaRepository.save(troca);
        }

        throw new IllegalArgumentException("Troca não encontrada.");
    }
}