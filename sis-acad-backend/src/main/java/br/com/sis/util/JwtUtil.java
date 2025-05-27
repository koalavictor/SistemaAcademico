package br.com.sis.util;

import java.time.Duration;
import java.util.Set;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtUtil {

    public String generateToken(String nome, String email, String tipoUsuario) {
        JwtClaimsBuilder claims = Jwt.claims()
            .issuer("https://sis-acad")
            .subject(email)
            .upn(email)
            .preferredUserName(nome)
            .groups(Set.of(tipoUsuario)) // Ex: ADMIN, PROFESSOR, ALUNO
            .expiresIn(Duration.ofHours(2));

        return claims.sign(); // Usa a chave privada automaticamente via config
    }
}