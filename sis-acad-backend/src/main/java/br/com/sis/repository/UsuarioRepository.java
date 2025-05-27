package br.com.sis.repository;

import java.util.Optional;

import org.bson.types.ObjectId;

import br.com.sis.entity.Usuario;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheMongoRepository<Usuario> {

    public Optional<Usuario> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }

    public Optional<Usuario> findById(String id) {
        try {
            ObjectId oid = new ObjectId(id);
            return findByIdOptional(oid);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}