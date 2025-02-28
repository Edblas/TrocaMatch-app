package com.troca.habilidades.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class JwtRespostaDTO {
    private String token;

    public JwtRespostaDTO(String token) {
        this.token = token;
    }
}
