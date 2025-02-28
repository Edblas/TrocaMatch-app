package com.troca.habilidades.service;

import com.troca.habilidades.controller.dtos.UsuarioCadastroDTO;
import com.troca.habilidades.controller.dtos.UsuarioRespostaDTO;
import com.troca.habilidades.entity.Permissao;
import com.troca.habilidades.entity.Usuario;
import com.troca.habilidades.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioRespostaDTO criarUsuario(UsuarioCadastroDTO usuarioCadastroDTO) {
        // Verifica se o e-mail já está cadastrado
        if (usuarioRepository.findByEmail(usuarioCadastroDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        // Criando o objeto Usuario a partir do DTO de cadastro
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioCadastroDTO.getNome());
        usuario.setEmail(usuarioCadastroDTO.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuarioCadastroDTO.getSenha())); // Criptografando a senha

        // Convertendo a String para Enum (se for inválida, define como USUARIO)
        try {
            usuario.setPermissao(Permissao.valueOf(usuarioCadastroDTO.getPermissao().toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException e) {
            usuario.setPermissao(Permissao.USUARIO);
        }

        // Salvando no banco e convertendo para DTO de resposta
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioRespostaDTO(usuarioSalvo.getId(), usuarioSalvo.getNome(), usuarioSalvo.getEmail(), usuarioSalvo.getPermissao());

    }

    public List<UsuarioRespostaDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> new UsuarioRespostaDTO(
                        usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getPermissao()))
                .collect(Collectors.toList());
    }

    public Optional<UsuarioRespostaDTO> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> new UsuarioRespostaDTO(
                        usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getPermissao()));
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
