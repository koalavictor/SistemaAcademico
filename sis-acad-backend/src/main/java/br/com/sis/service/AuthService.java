package br.com.sis.service;

import br.com.sis.dto.LoginDTO;
import br.com.sis.entity.Usuario;
import br.com.sis.repository.UsuarioRepository;
import br.com.sis.util.JwtUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    JwtUtil jwtUtil;

    public String login(LoginDTO login) {
        Usuario usuario = usuarioRepository.findByEmail(login.getEmail()).get();

        if (usuario == null || !usuario.getSenha().equals(login.getSenha())) {
              throw new IllegalArgumentException("Credenciais inválidas");
        }

        return jwtUtil.generateToken(usuario.getNome(), usuario.getEmail(), usuario.getTipo().name());
    }
}
