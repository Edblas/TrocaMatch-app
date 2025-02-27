package com.troca.habilidades.repository;

import com.troca.habilidades.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    List<Servico> findByUsuarioId(Long usuarioId);
}
