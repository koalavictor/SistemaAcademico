package br.com.sis.service;

import java.util.List;

import br.com.sis.entity.Usuario;
import br.com.sis.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    public void cadastrar(Usuario usuario) {
        usuarioRepository.persist(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.listAll();
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).get();
    }

    public boolean deletar(String email) {
        Usuario user = usuarioRepository.findByEmail(email).get();
        return usuarioRepository.deleteById(user.getId());
    }

    public Usuario atualizar(String email, Usuario dadosAtualizados) {
        Usuario usuarioExistente = usuarioRepository.findByEmail(email).get();
        if (usuarioExistente == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        usuarioExistente.setNome(dadosAtualizados.getNome());
        usuarioExistente.setEmail(dadosAtualizados.getEmail());
        usuarioExistente.setSenha(dadosAtualizados.getSenha());
        usuarioExistente.setTipo(dadosAtualizados.getTipo());

        usuarioRepository.update(usuarioExistente);
        return usuarioExistente;
    }
}
