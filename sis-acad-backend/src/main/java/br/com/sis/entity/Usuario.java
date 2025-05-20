package br.com.sis.entity;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import br.com.sis.enums.TipoUsuarioEnum;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@MongoEntity(collection = "usuarios")
public class Usuario {

    @BsonId
    public ObjectId id;

    @NotBlank
    @Size(max = 100)
    @BsonProperty("nome")
    public String nome;

    @NotBlank
    @Email
    @BsonProperty("email")
    public String email;

    @NotBlank
    @Size(min = 6, max = 100)
    @BsonProperty("senha")
    public String senha;

    @NotNull
    @BsonProperty("tipo")
    public TipoUsuarioEnum tipo;

    public Usuario() {}

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuarioEnum getTipo() {
        return tipo;
    }
    public void setTipo(TipoUsuarioEnum tipo) {
        this.tipo = tipo;
    }
}
