package com.troca.habilidades.security;

import com.troca.habilidades.entity.Usuario;
import com.troca.habilidades.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        // Convertendo a permissão do enum para um formato compatível com Spring Security
        String role = "ROLE_" + usuario.getPermissao().name();
        GrantedAuthority authority = new SimpleGrantedAuthority(role);

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha()) // Senha deve estar criptografada
                .authorities(Collections.singletonList(authority))
                .build();
    }
}