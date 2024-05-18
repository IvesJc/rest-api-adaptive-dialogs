package org.salesforce.resouce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.salesforce.Main;
import org.salesforce.models.TipoPlano;
import org.salesforce.repositories.TipoPlanoRepository;

import java.util.List;

@Path("tipo-plano")
public class TipoPlanoResource {

    TipoPlanoRepository tipoPlanoRepository = new TipoPlanoRepository();

    @GET
    public Response getTipoPlano(){
        List<TipoPlano> tipoPlanoList = tipoPlanoRepository.getTipoPlano();
        if (tipoPlanoList.isEmpty()){
            Main.LOGGER.info("404 - Tipo Plano NOT FOUND");
            return Response.status(404).entity("Tipo Plano not found").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(tipoPlanoList).build();
    }

    @GET
    @Path("{id}")
    public Response getTipoPlanoById(@PathParam("id") int id){
        TipoPlano tipoPlano = tipoPlanoRepository.getTipoPlanoById(id);
        if (tipoPlano == null){
            Main.LOGGER.info("404 - Tipo Plano NOT FOUND");
            return Response.status(404).entity("Tipo Plano não encontrado").build();
        }
        Main.LOGGER.info("[GET] - 200 - OK");
        return Response.status(200).entity(tipoPlano).build();
    }



    @POST
    public Response createTipoPlano(TipoPlano tipoPlano){
        if (tipoPlano == null){
            Main.LOGGER.info("404 - Tipo Plano can not be null ");
            return Response.status(404).entity("Tipo Plano não pode ser nula").build();
        }
        int result = tipoPlanoRepository.createTipoPlano(tipoPlano);
        if (result == 0){
            return Response.status(400).entity("Falha ao criar Tipo Plano").build();
        }
        Main.LOGGER.info("[POST] - 201 - OK");
        return Response.status(201).entity(tipoPlano).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTipoPlano(@PathParam("id") int id, TipoPlano tipoPlano){
        tipoPlano.setId(id);

        TipoPlano tp = tipoPlanoRepository.getTipoPlanoById(tipoPlano.getId());
        if (tp != null){
            tipoPlanoRepository.updateTipoPlano(tipoPlano);
            Main.LOGGER.info("[PUT] - 200 - OK");
            return Response.status(200).entity(tipoPlano).build();
        }else {
            Main.LOGGER.info("404 - Tipo Plano not found ");
            return Response.status(404).entity("Tipo Plano não registrado").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteTipoPlano(@PathParam("id") int id){
        TipoPlano tp = tipoPlanoRepository.getTipoPlanoById(id);
        if (tp != null){
            tipoPlanoRepository.deleteById(id);
            Main.LOGGER.info("[DELETE] - 204 - No content");
            return Response.status(204).entity(tp).build();
        }else {
            return Response.status(400).entity("Tipo Plano não registrado").build();
        }
    }
}
