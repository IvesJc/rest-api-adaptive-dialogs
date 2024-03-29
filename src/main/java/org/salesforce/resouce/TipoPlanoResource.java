package org.salesforce.resouce;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.salesforce.models.Cliente;
import org.salesforce.models.TipoPlano;
import org.salesforce.repositories.ClienteRepository;
import org.salesforce.repositories.TipoPlanoRepository;

import java.util.List;

@Path("tipo_plano")
public class TipoPlanoResource {

    TipoPlanoRepository tipoPlanoRepository = new TipoPlanoRepository();

    @GET
    public List<TipoPlano> getTipoPlano(){
        return tipoPlanoRepository.getTipoPlano();
    }

    @GET
    @Path("{id}")
    public Response getTipoPlanoById(@PathParam("id") int id){
        TipoPlano tipoPlano = tipoPlanoRepository.getTipoPlanoById(id);
        if (tipoPlano == null){
            return Response.status(404).entity("Tipo Plano não encontrado").build();
        }
        return Response.status(200).entity(tipoPlano).build();
    }

    @POST
    public Response createTipoPlano(TipoPlano tipoPlano){
        if (tipoPlano == null){
            return Response.status(400).entity("Tipo Plano não pode ser nulo").build();
        }
        tipoPlanoRepository.createTipoPlano(tipoPlano);
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
            return Response.status(200).entity(tipoPlano).build();
        }else {
            return Response.status(400).entity("Tipo Plano não registrado").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteTipoPlano(@PathParam("id") int id){
        TipoPlano tp = tipoPlanoRepository.getTipoPlanoById(id);
        if (tp != null){
            tipoPlanoRepository.deleteById(id);
            return Response.status(200).entity(tp).build();
        }else {
            return Response.status(400).entity("Tipo Plano não registrado").build();
        }
    }

}
