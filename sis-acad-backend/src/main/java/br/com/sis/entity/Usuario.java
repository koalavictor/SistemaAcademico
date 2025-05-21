package br.com.sis.entity;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import br.com.sis.enums.TipoUsuarioEnum;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@MongoEntity(collection = "usuarios")
public class Usuario {

    @BsonProperty("_id")
    private ObjectId id;

    @BsonProperty("nome")
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100)
    private String nome;

    @BsonProperty("email")
    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @BsonProperty("senha")
    @NotBlank(message = "Senha é obrigatória")
    private String senha;

    @BsonProperty("tipo")
    private TipoUsuarioEnum tipo;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

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