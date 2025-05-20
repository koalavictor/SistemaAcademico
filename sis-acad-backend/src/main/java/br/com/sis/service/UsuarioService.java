package br.com.sis.service;

import br.com.sis.dto.UsuarioRequest;
import br.com.sis.dto.UsuarioResponse;
import br.com.sis.entity.Usuario;
import br.com.sis.enums.TipoUsuarioEnum;
import br.com.sis.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    public UsuarioResponse create(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.nome = request.nome;
        usuario.email = request.email;
        usuario.senha = BCrypt.hashpw(request.senha, BCrypt.gensalt());
        usuario.tipo = TipoUsuarioEnum.valueOf(request.tipo.toString());

        usuarioRepository.persist(usuario);

        return toResponse(usuario);
    }

    public List<UsuarioResponse> listAll() {
        return usuarioRepository.listAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioResponse> findById(String id) {
        return usuarioRepository.findById(id).map(this::toResponse);
    }

    public Optional<UsuarioResponse> update(String id, UsuarioRequest request) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        if (optUsuario.isEmpty()) {
            return Optional.empty();
        }

        Usuario usuario = optUsuario.get();
        usuario.nome = request.nome;
        usuario.email = request.email;
        if (request.senha != null && !request.senha.isBlank()) {
            usuario.senha = BCrypt.hashpw(request.senha, BCrypt.gensalt());
        }
        usuario.tipo = TipoUsuarioEnum.valueOf(request.tipo.toString());

        usuarioRepository.persist(usuario);
        return Optional.of(toResponse(usuario));
    }

    public boolean delete(String id) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        if (optUsuario.isEmpty()) {
            return false;
        }
        usuarioRepository.delete(optUsuario.get());
        return true;
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    private UsuarioResponse toResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.id = usuario.id != null ? usuario.id.toHexString() : null;
        response.nome = usuario.nome;
        response.email = usuario.email;
        response.tipo = usuario.tipo;
        return response;
    }
}
