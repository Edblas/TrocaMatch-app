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
        if (servico.getTitulo() == null || servico.getTitulo().isBlank()) {
            throw new IllegalArgumentException("O título do serviço não pode estar vazio");
        }
        if (servico.getDescricao() == null || servico.getDescricao().isBlank()) {
            throw new IllegalArgumentException("A descrição do serviço não pode estar vazia");
        }

        return servicoRepository.save(servico);
    }

    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    public List<Servico> listarServicosPorUsuario(Long usuarioId) {
        List<Servico> servicos = servicoRepository.findByUsuarioId(usuarioId);
        if (servicos.isEmpty()) {
            throw new IllegalArgumentException("Nenhum serviço encontrado para o usuário com ID: " + usuarioId);
        }
        return servicos;
    }
}
