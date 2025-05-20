package br.com.sis.dto;

import br.com.sis.enums.TipoUsuarioEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank
    @Size(max = 100)
    public String nome;

    @NotBlank
    @Email
    public String email;

    @NotBlank
    @Size(min = 6, max = 100)
    public String senha;

    @NotNull
    public TipoUsuarioEnum tipo;
}
