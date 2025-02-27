package com.troca.habilidades.service;

import com.troca.habilidades.entity.Servico;
import com.troca.habilidades.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public Servico criarServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    public List<Servico> listarServicosPorUsuario(Long usuarioId) {
        return servicoRepository.findByUsuarioId(usuarioId);
    }
}
