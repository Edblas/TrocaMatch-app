package com.troca.habilidades.service;

import com.troca.habilidades.entity.StatusTroca;
import com.troca.habilidades.entity.Troca;
import com.troca.habilidades.repository.TrocaRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class TrocaService {

    @Autowired
    private TrocaRepository trocaRepository;

    public Troca registrarTroca(Troca troca) {
        if (troca.getOfertante() == null || troca.getReceptor() == null) {
            throw new IllegalArgumentException("A troca precisa de um ofertante e um receptor.");
        }
        if (troca.getServicoOfertado() == null || troca.getServicoRecebido() == null) {
            throw new IllegalArgumentException("A troca precisa conter serviços ofertados e recebidos.");
        }

        troca.setStatus(StatusTroca.PENDENTE); // Usando enum diretamente
        return trocaRepository.save(troca);
    }

    public List<Troca> listarTrocas() {
        return trocaRepository.findAll(); // Não lança erro, retorna uma lista vazia se não houver trocas
    }
}

