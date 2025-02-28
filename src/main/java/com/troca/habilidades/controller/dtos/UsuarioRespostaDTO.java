package com.troca.habilidades.controller.dtos;

import com.troca.habilidades.entity.Permissao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRespostaDTO {
    private Long id;
    private String nome;
    private String email;
    private Permissao permissao; // Adicionando o campo de permissão
}