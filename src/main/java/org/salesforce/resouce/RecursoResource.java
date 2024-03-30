package org.salesforce.resouce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.salesforce.models.Recurso;
import org.salesforce.repositories.RecursoRepository;

import java.util.List;

@Path("recurso")
public class RecursoResource {
    RecursoRepository recursoRepository = new RecursoRepository();

    @GET
    public List<Recurso> getRecursos(){
        return recursoRepository.getRecursos();
    }

    @GET
    @Path("{id}")
    public Response getRecursoById(@PathParam("id") int id){
        Recurso recurso = recursoRepository.getRecursoById(id);
        if (recurso == null){
            return Response.status(404).entity("Recurso não encontrado").build();
        }
        return Response.status(200).entity(recurso).build();
    }

    @POST
    public Response createRecurso(Recurso recurso){
        if (recurso == null){
            return Response.status(400).entity("Recurso não pode ser nulo").build();
        }
        recursoRepository.createRecurso(recurso);
        return Response.status(201).entity(recurso).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRecurso(@PathParam("id") int id, Recurso recurso){
        recurso.setId(id);
        Recurso rec = recursoRepository.getRecursoById(recurso.getId());
        if (rec != null){
            recursoRepository.updateRecurso(recurso);
            return Response.status(200).entity(recurso).build();
        }else {
            return Response.status(400).entity("Recurso não registrado").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteRecurso(@PathParam("id") int id){
        Recurso rec = recursoRepository.getRecursoById(id);
        if (rec != null){
            recursoRepository.deleteById(id);
            return Response.status(200).entity(rec).build();
        }else {
            return Response.status(400).entity("Recurso não registrado").build();
        }
    }

}
