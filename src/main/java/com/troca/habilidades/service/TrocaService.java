package com.troca.habilidades.service;

import com.troca.habilidades.entity.Troca;;
import com.troca.habilidades.repository.TrocaRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Setter
public class TrocaService {

    @Autowired
    private TrocaRepository trocaRepository;

    public Troca registrarTroca(Troca troca) {
         troca.setStatus("pendente");
        return trocaRepository.save(troca);
    }

    public List<Troca> listarTrocas() {
        return trocaRepository.findAll();
    }
}
