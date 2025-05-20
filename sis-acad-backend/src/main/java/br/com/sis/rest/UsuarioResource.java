package br.com.sis.rest;

import java.util.List;

import br.com.sis.dto.UsuarioRequest;
import br.com.sis.dto.UsuarioResponse;
import br.com.sis.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
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
    UsuarioService service;

    @GET
    public List<UsuarioResponse> listar() {
        return service.listAll();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") String id) {
        return service.findById(id)
                .map(usuario -> Response.ok(usuario).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response criar(@Valid UsuarioRequest request) {
        UsuarioResponse usuario = service.create(request);
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") String id, @Valid UsuarioRequest request) {
        return service.update(id, request)
                .map(usuario -> Response.ok(usuario).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") String id) {
        if (service.delete(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
