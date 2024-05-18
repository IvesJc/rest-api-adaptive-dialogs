package org.salesforce.resouce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.salesforce.Main;
import org.salesforce.models.Recurso;
import org.salesforce.repositories.RecursoRepository;

import java.util.List;

@Path("recurso")
public class RecursoResource {
    RecursoRepository recursoRepository = new RecursoRepository();

    @GET
    public Response getRecurso(){
        List<Recurso> recursoList = recursoRepository.getRecurso();
        if (recursoList.isEmpty()){
            Main.LOGGER.info("404 - Recurso NOT FOUND");
            return Response.status(404).entity("Recurso not found").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(recursoList).build();
    }

    @GET
    @Path("{id}")
    public Response getRecursoById(@PathParam("id") int id){
        Recurso recurso = recursoRepository.getRecursoById(id);
        if (recurso == null){
            Main.LOGGER.info("404 - Recurso NOT FOUND");
            return Response.status(404).entity("Recurso não encontrado").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(recurso).build();
    }

    @POST
    public Response createRecurso(Recurso recurso){
        if (recurso == null){
            Main.LOGGER.info("404 - Recurso can not be null ");
            return Response.status(404).entity("Recurso não pode ser nula").build();
        }
        int result = recursoRepository.createRecurso(recurso);
        if (result == 0){
            return Response.status(400).entity("Falha ao criar Recurso").build();
        }
        Main.LOGGER.info("[POST] - 201 - OK");
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
            Main.LOGGER.info("[PUT] - 200 - OK");
            return Response.status(200).entity(recurso).build();
        }else {
            Main.LOGGER.info("404 - Recurso not found ");
            return Response.status(404).entity("Recurso não registrado").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteRecurso(@PathParam("id") int id){
        Recurso rec = recursoRepository.getRecursoById(id);
        if (rec != null){
            recursoRepository.deleteById(id);
            Main.LOGGER.info("[DELETE] - 204 - No content");
            return Response.status(204).entity(rec).build();
        }else {
            return Response.status(400).entity("Recurso não registrado").build();
        }
    }
}
