package com.troca.habilidades.controller;

import com.troca.habilidades.controller.dtos.UsuarioCadastroDTO;
import com.troca.habilidades.controller.dtos.UsuarioRespostaDTO;
import com.troca.habilidades.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/criar")
    public UsuarioRespostaDTO criarUsuario(@RequestBody UsuarioCadastroDTO usuarioCadastroDTO) {
        return usuarioService.criarUsuario(usuarioCadastroDTO);
    }

    @GetMapping
    public List<UsuarioRespostaDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioRespostaDTO buscarUsuario(@PathVariable Long id) {
        return usuarioService.buscarUsuarioPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }
}
