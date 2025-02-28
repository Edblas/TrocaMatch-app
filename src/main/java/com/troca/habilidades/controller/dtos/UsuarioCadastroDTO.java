package com.troca.habilidades.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCadastroDTO {
    private String nome;
    private String email;
    private String senha;
    private String permissao; // Deve ser "usuario" ou "admin"
}
