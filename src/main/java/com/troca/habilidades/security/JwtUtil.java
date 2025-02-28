package com.troca.habilidades.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtUtil {

    // Chave secreta usada para assinar e verificar tokens JWT (deve ter pelo menos 32 caracteres)
    private static final String SECRET_KEY = "sua-chave-secreta-super-segura-para-token-jwt-32chars";

    // Conversão da chave em um objeto Key do JJWT
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    // Gera um token JWT com o nome de usuário e validade de 1 hora
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Define o nome de usuário como o assunto do token
                .setIssuedAt(new Date()) // Define a data de emissão do token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Expira em 1 hora
                .signWith(key, SignatureAlgorithm.HS256) // Assina com a chave segura
                .compact(); // Gera o token
    }

    // Extrai as claims (dados) do token
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // Define a chave para verificar a assinatura
                .build()
                .parseClaimsJws(token) // Analisa e valida o token
                .getBody(); // Retorna as informações contidas no token
    }

    // Obtém o nome de usuário do token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Verifica se o token está expirado
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Valida o token comparando nome de usuário e verificando se não expirou
    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }
}
