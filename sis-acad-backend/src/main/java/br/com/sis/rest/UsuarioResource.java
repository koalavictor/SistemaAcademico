package br.com.sis.rest;

import java.util.List;

import br.com.sis.dto.LoginDTO;
import br.com.sis.dto.TokenResponseDto;
import br.com.sis.entity.Usuario;
import br.com.sis.service.AuthService;
import br.com.sis.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;

    @Inject
    AuthService authService;

    @POST
    @Path("/login")
    public Response login(LoginDTO login) {
        try {
            String token = authService.login(login);
            return Response.ok(new TokenResponseDto(token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @GET
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GET
    @Path("/{email}")
    public Response buscarPorId(@PathParam("id") String email) {
        Usuario response = usuarioService.buscarPorEmail(email);
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @POST
    public Response criar(Usuario usuario) {
        usuarioService.cadastrar(usuario);

        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/{id}")
    public Usuario atualizar(@PathParam("id") String email, Usuario usuario) {
        return usuarioService.atualizar(email, usuario);
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") String email) {
        if (usuarioService.deletar(email)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}