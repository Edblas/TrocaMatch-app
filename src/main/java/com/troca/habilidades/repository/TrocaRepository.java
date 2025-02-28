package com.troca.habilidades.repository;

import com.troca.habilidades.entity.Troca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrocaRepository extends JpaRepository<Troca, Long> {
    List<Troca> findByOfertanteId(Long ofertanteId);
    List<Troca> findByReceptorId(Long receptorId);
    List<Troca> findByStatus(String status);
}
